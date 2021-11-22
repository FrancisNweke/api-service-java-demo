package com.asix.demo.student;

import com.asix.demo.apidata.ApiResponse;
import com.asix.demo.exception.CustomBadRequestException;
import com.asix.demo.exception.CustomInternalServerException;
import com.asix.demo.exception.EmailValidatorException;
import com.asix.demo.exception.ResourceNotFoundException;
import com.asix.demo.student.models.Student;
import com.asix.demo.student.viewmodels.ViewStudent;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/student", produces = "application/json")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("getStudents")
    @ApiOperation("Get All Students")
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public List<Student> getStudents(){
        var response = studentService.getStudents();
        if(response.getResponseCode().equals("01")){
            throw new CustomInternalServerException("Server can't be reached.");
        }

        var student = response.getResponseResult();
        return ((List<Student>) student);
    }

    @GetMapping("getStudentById")
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ApiResponse getStudentById(@RequestParam(name = "id") Long id){
        var response = studentService.getStudentById(id);
        if(response.getResponseCode() == "01")
            throw  new CustomInternalServerException("Oops.. An internal server error occurred.");
        else if(response.getResponseCode().equals("404"))
            throw  new ResourceNotFoundException(response.getResponseMessage());

        return response;
    }

    @PutMapping("updateStudent")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ApiResponse updateStudent(@RequestBody Student existingStudent){
        var response = studentService.updateStudent(existingStudent);
        if(response.getResponseCode().equals("01"))
            throw new CustomInternalServerException(response.getResponseResult().toString());

        return response;
    }

    @DeleteMapping("deleteStudent")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ApiResponse deleteStudent(@RequestParam Long id){
        var response = studentService.deleteStudent(id);
        if(response.getResponseCode() == "404")
            throw new ResourceNotFoundException(response.getResponseMessage());
        else if(response.getResponseCode() == "01")
            throw new CustomInternalServerException(response.getResponseResult().toString());
        else
            return response;
    }

    @PostMapping("addNewStudent")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ApiResponse addNewStudent(@Valid @RequestBody ViewStudent newStudent){
        var response = studentService.addNewStudent(newStudent);
        if(response.getResponseCode().equals("01")){
            throw new CustomBadRequestException("Can't save product.");
        } else if(response.getResponseCode().equals("010")){
            throw new EmailValidatorException(response.getResponseMessage());
        }

        return response;
    }
}
