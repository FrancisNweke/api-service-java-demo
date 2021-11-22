package com.asix.demo.authentication.service.implementation;

import com.asix.demo.authentication.model.User;
import com.asix.demo.authentication.repository.IUserRepository;
import com.asix.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private final IUserRepository userRepository;

    @Autowired
    public AppUserDetailsService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws ResourceNotFoundException {
        var optionalUser = userRepository.findByUsername(username);

        User user= optionalUser.get();

        if(user == null) {
            throw new ResourceNotFoundException(String.format("The username %s doesn't exist", username));
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        });

        UserDetails userDetails = new org.springframework.security.core.userdetails.
                User(user.getUsername(), user.getPassword(), authorities);

        return userDetails;
    }
}
