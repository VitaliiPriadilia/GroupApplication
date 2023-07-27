package com.employee.Group.services.impl;

import com.employee.Group.dto.RegisterDTO;
import com.employee.Group.models.AppUser;
import com.employee.Group.repositories.UserRepository;
import com.employee.Group.security.PasswordEncoder;
import com.employee.Group.services.RoleService;
import com.employee.Group.services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleService roleService,
                           PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findAppUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

    }
    @Override
    public boolean userExistByUsername(String username) {
        return this.userRepository.existsAppUserByUsername(username);
    }

    @Override
    public void saveUserByInfo(RegisterDTO registerDTO) {
        AppUser appUser = new AppUser();
        appUser.setEmail(registerDTO.getEmail());
        appUser.setPassword(this.passwordEncoder.bCryptPasswordEncoder().encode(registerDTO.getPassword()));
        appUser.setUsername(registerDTO.getUsername());
        appUser.setRole(this.roleService.getRoleByName("ROLE_" + registerDTO.getRole()).get());
        appUser.setLastChanges(new Date());
        this.userRepository.save(appUser);
    }
}
