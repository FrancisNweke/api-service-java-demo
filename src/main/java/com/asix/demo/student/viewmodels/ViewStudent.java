package com.asix.demo.student.viewmodels;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class ViewStudent {

    @Size(min = 3, max = 150, message = "Name must be between 3 and 150 characters.")
    private String name;

    @Email
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    @Size(max = 150, message = "Must not exceed 150 characters.")
    private String programEnrolled;

    public ViewStudent(String name, String email, LocalDate dob, String programEnrolled) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.programEnrolled = programEnrolled;
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

    @Override
    public String toString() {
        return "ViewStudent{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", programEnrolled='" + programEnrolled + '\'' +
                '}';
    }
}
