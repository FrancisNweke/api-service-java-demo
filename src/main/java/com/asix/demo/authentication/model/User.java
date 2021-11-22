package com.asix.demo.authentication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", nullable = false, columnDefinition = "NVARCHAR(255)")
    private String username;

    @Column(name = "password", nullable = false, columnDefinition = "NVARCHAR(255)")
    @JsonIgnore
    private String password;

    @Column(name = "first_name", nullable = false, columnDefinition = "NVARCHAR(255)")
    private String firstName;

    @Column(name = "last_name", nullable = false, columnDefinition = "NVARCHAR(255)")
    private String lastName;

    @Column(name = "email", nullable = false, columnDefinition = "NVARCHAR(255)")
    private String email;

    @Column(name = "DateTimeCreated", nullable = false, updatable = false)
    private LocalDateTime dateTimeCreated;

    @Column(name = "CreatedBy", nullable = false, updatable = false, columnDefinition = "NVARCHAR(255)")
    private String createdBy;

    /**
     * Roles are being eagerly loaded here because
     * they are a fairly small collection of items for this example.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns
            = @JoinColumn(name = "user_id",
            referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",
                    referencedColumnName = "id"))
    private List<Role> roles;
}
