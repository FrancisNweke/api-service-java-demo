package com.asix.demo.student.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.Period;

import static javax.persistence.GenerationType.SEQUENCE;

@Table(name = "Students",
        uniqueConstraints = { @UniqueConstraint(name = "student_email_unique", columnNames = "Email")}
        )
@Entity(name = "Student")
public class Student {

    @Id
    @SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "student_sequence")
    @Column(name = "Id")
    private Long id;

    @Column(name = "Name", nullable = false, columnDefinition = "NVARCHAR(150)")
    private String name;

    @Column(name = "Email", nullable = false, columnDefinition = "NVARCHAR(150)")
    private String email;

    @Column(name = "DateOfBirth", nullable = false)
    private LocalDate dob;

    @Column(name = "Program", nullable = false, columnDefinition = "NVARCHAR(150)")
    private String programEnrolled;
    @Transient
    private Integer age;

    public Student() {
    }

    public Student(String name, String email, LocalDate dob, String programEnrolled) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.programEnrolled = programEnrolled;
    }

    public Student(Long id, String name, String email, LocalDate dob, String programEnrolled) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.programEnrolled = programEnrolled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getProgramEnrolled() {
        return programEnrolled;
    }

    public void setProgramEnrolled(String programEnrolled) {
        this.programEnrolled = programEnrolled;
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                ", programEnrolled='" + programEnrolled + '\'' +
                '}';
    }
}
