package com.asix.demo.authentication.service;

import com.asix.demo.apidata.ApiResponse;
import com.asix.demo.authentication.model.Role;
import com.asix.demo.authentication.model.User;
import com.asix.demo.authentication.viewmodel.ViewRole;
import com.asix.demo.authentication.viewmodel.ViewUser;

import java.util.List;

public interface IGenericService {

    //region User
    User findByUsername(String username);

    List<User> findAllUsers();

    ApiResponse createUser(ViewUser newUser);

    ApiResponse deleteUser(Long id);
    //endregion

    //region Role
    ApiResponse createRole(ViewRole newRole);
    ApiResponse getAllRoles();
    //endregion

}
