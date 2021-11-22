package com.asix.demo.authentication.controller;

import com.asix.demo.apidata.ApiResponse;
import com.asix.demo.authentication.model.User;
import com.asix.demo.authentication.service.IGenericService;
import com.asix.demo.authentication.viewmodel.ViewUser;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/auth", produces = "application/json")
@ApiOperation("User Authentication and Authorization")
public class UserAndRoleController {

    @Autowired
    private IGenericService genericService;

    //region User
    @GetMapping
    @RequestMapping("getUsers")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public List<User> getUsers(){
        return genericService.findAllUsers();
    }

    @PostMapping("createUser")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity<ApiResponse> createUser(@Valid @RequestBody ViewUser newUser){
        var response = genericService.createUser(newUser);
        if (response.getResponseCode().equals("00"))
            return  new ResponseEntity<>(response, HttpStatus.OK);
        else
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("deleteUser/{id}")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity<ApiResponse> deleteUser(@RequestParam Long id){
        var response = genericService.deleteUser(id);
        if (response.getResponseCode().equals("00"))
            return  new ResponseEntity<>(response, HttpStatus.OK);
        else
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    //endregion

    //region Role
    @GetMapping
    @RequestMapping("getRoles")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity<ApiResponse> getRoles(){
        var response = genericService.getAllRoles();
        if(response.getResponseCode().equals("01")){
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    //endregion
}
