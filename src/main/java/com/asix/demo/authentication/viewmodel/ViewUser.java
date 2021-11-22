package com.asix.demo.authentication.viewmodel;

import com.asix.demo.authentication.model.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ViewUser {

    @Size(min = 2, message = "", max = 150)
    private String username;

    @NotEmpty
    @Size(min = 7, message = "", max = 150)
    private String password;

    @Size(min = 3, message = "", max = 150)
    private String firstName;

    @Size(min = 3, message = "", max = 150)
    private String lastName;

    @Email
    private String email;

    private LocalDateTime dateTimeCreated;

    @Size(min = 2, message = "", max = 150)
    private String createdBy;

    @NotNull
    private List<Role> roles;

    public ViewUser(String username, String password, String firstName, String lastName, String email, String createdBy, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.createdBy = createdBy;
        this.dateTimeCreated = LocalDateTime.now();
        this.roles = roles;
    }
}
