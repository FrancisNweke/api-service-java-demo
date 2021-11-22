package com.asix.demo.authentication.viewmodel;

import lombok.*;

@Getter
@Setter
public class ViewRole {

    private String roleName;

    private String description;

    public ViewRole(String roleName, String description) {
        this.roleName = roleName;
        this.description = description;
    }
}
