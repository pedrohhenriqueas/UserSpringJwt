package com.example.CrudJavaJwt.security.services;

import com.example.CrudJavaJwt.model.Users;
import com.example.CrudJavaJwt.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Users users = userRepository.findByName(name)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + name));

        return UserDetailsImpl.build(users);
    }
}