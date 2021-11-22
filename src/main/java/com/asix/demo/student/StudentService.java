package com.asix.demo.student;

import com.asix.demo.apidata.ApiResponse;
import com.asix.demo.exception.ResourceNotFoundException;
import com.asix.demo.student.models.Student;
import com.asix.demo.student.viewmodels.ViewStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public ApiResponse getStudents() {
        try{
            var students = studentRepository.findAll();

            return new ApiResponse("00", "Successful", students);
        }
        catch(Exception ex){
            return new ApiResponse("01", "Failure", ex.getMessage());
        }
    }

    public ApiResponse addNewStudent(ViewStudent newStudent) {
        try{
            var studentByEmail = studentRepository.findStudentByEmail(newStudent.getEmail());
            if(studentByEmail.isPresent())
                return new ApiResponse("010", "Email is already taken.");

            var studentEntity = new Student();
            studentEntity.setName(newStudent.getName());
            studentEntity.setEmail(newStudent.getEmail());
            studentEntity.setDob(newStudent.getDob());
            studentEntity.setProgramEnrolled(newStudent.getProgramEnrolled());
            studentRepository.save(studentEntity);

            return new ApiResponse("00", "Successfully added");
        }catch(Exception ex) {
            return new ApiResponse("01", "Failure", ex.getMessage());
        }
    }

    public ApiResponse getStudentById(Long id) {
        try{
            var student = studentRepository.findById(id);

            if (studentRepository.existsById(id))
                return new ApiResponse("00", "Successful", student.get());
            else
                return new ApiResponse("404", String.format("Student with id: %s not found.", id));
        }catch(Exception ex) {
            return new ApiResponse("01", "Failure", ex.getMessage());
        }
    }

    public ApiResponse deleteStudent(Long id) {

        try{
            if (studentRepository.existsById(id)) {

                studentRepository.deleteById(id);

                return new ApiResponse("00", "Successfully deleted");
            } else {
                return new ApiResponse("404", String.format("Student with id: %s not found.", id));
            }
        } catch(Exception ex){
            return new ApiResponse("01", "Failure", ex.getMessage());
        }
    }

    @Transactional
    public ApiResponse updateStudent(Student student) {

        try{
            var modifiedStudent = studentRepository.findById(student.getId()).orElseThrow(
                    () -> new ResourceNotFoundException("student with " + student.getId() + " not found.")
            );

            if(student.getName() != null && student.getName().length() > 2
                    && !Objects.equals(student.getName(), modifiedStudent.getName())) {
                modifiedStudent.setName(student.getName());
            }

            if(student.getEmail() != null && student.getEmail().length() > 3
                    && !Objects.equals(student.getEmail(), modifiedStudent.getEmail())) {
                modifiedStudent.setEmail(student.getEmail());
            }

            if(student.getDob() != null && !Objects.equals(student.getDob(), modifiedStudent.getDob())) {
                modifiedStudent.setDob(student.getDob());
            }

            if(student.getProgramEnrolled() != null && student.getProgramEnrolled().length() > 3
                    && !Objects.equals(student.getProgramEnrolled(), modifiedStudent.getProgramEnrolled())) {
                modifiedStudent.setProgramEnrolled(student.getProgramEnrolled());
            }

            return new ApiResponse("00", "Successfully updated", student);
        }catch(Exception ex){
            return new ApiResponse("01", "Failure", ex.getMessage());
        }
    }
}
