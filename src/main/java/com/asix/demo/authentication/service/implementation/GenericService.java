package com.asix.demo.authentication.service.implementation;

import com.asix.demo.apidata.ApiResponse;
import com.asix.demo.authentication.model.Role;
import com.asix.demo.authentication.model.User;
import com.asix.demo.authentication.repository.IRoleRepository;
import com.asix.demo.authentication.repository.IUserRepository;
import com.asix.demo.authentication.service.IGenericService;
import com.asix.demo.authentication.viewmodel.ViewRole;
import com.asix.demo.authentication.viewmodel.ViewUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenericService implements IGenericService {

    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public GenericService(IUserRepository userRepository, IRoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    //region User
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }

    @Override
    public List<User> findAllUsers() {
        return (List<User>)userRepository.findAll();
    }

    @Override
    public ApiResponse createUser(ViewUser newUser) {
        try{
            var createdByUser = userRepository.checkCreatedBy(newUser.getUsername());
            if(userRepository.findByUsername(newUser.getUsername()).isPresent() == false){
                if(createdByUser.isPresent()){
                    User user = new User();
                    user.setUsername(newUser.getUsername());
                    user.setFirstName(newUser.getFirstName());
                    user.setLastName(newUser.getLastName());
                    user.setPassword(passwordEncoder.encode(newUser.getPassword()));
                    user.setEmail(newUser.getEmail());
                    user.setCreatedBy(newUser.getCreatedBy());
                    user.setRoles(newUser.getRoles());

                    var response = userRepository.save(user);

                    return new ApiResponse("00", "User Created Successfully");
                } else{
                    return new ApiResponse("11", String.format("Failed: CreatedByUser-%s doesn't exist.", newUser.getCreatedBy()));
                }
            }else {
                return new ApiResponse("11", String.format("Failed: Username-%s already exist.", newUser.getUsername()));
            }

        } catch(Exception ex){
            return new ApiResponse("01", "Failure", ex.getMessage());
        }
    }

    @Override
    public ApiResponse deleteUser(Long id) {
        try{
            if(userRepository.existsById(id))
                userRepository.deleteById(id);

            return new ApiResponse("00", "Successfully deleted.");
        } catch(Exception ex){
            return new ApiResponse("01", "Failure", ex.getMessage());
        }
    }
    //endregion

    //region Role
    @Override
    public ApiResponse getAllRoles() {
        try{
            var roles = roleRepository.findAll();

            return new ApiResponse("00", "Successful", roles);
        } catch(Exception ex){
            return new ApiResponse("01", "Failure", ex.getMessage());
        }
    }

    @Override
    public ApiResponse createRole(ViewRole newRole) {
        try{
            if(roleRepository.findByRoleName(newRole.getRoleName()).isPresent() == false){
                Role role = new Role();
                role.setRoleName(newRole.getRoleName());
                role.setDescription(newRole.getDescription());

                roleRepository.save(role);

                return new ApiResponse("00", "Role Created Successfully");
            }else {
                return new ApiResponse("11", String.format("Failed: RoleName-%s already exist.", newRole.getRoleName()));
            }
        }catch(Exception ex){
            return new ApiResponse("01", "Failure", ex.getMessage());
        }
    }
    //endregion
}
