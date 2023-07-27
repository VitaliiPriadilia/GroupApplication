package com.employee.Group.services;

import com.employee.Group.dto.RegisterDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    boolean userExistByUsername(String username);
    void saveUserByInfo(RegisterDTO appUser);
}
