package com.asix.demo.authentication.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "AppRoles")
@Getter
@Setter
public class Role {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="role_name", nullable = false, columnDefinition = "NVARCHAR(255)")
    private String roleName;

    @Column(name="description", columnDefinition = "NVARCHAR(255)")
    private String description;
}
