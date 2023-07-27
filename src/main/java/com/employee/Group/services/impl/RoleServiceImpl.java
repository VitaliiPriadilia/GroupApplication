package com.employee.Group.services.impl;

import com.employee.Group.exceptions.RoleNotFoundException;
import com.employee.Group.models.Role;
import com.employee.Group.repositories.RoleRepository;
import com.employee.Group.services.RoleService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }
    @Override
    public Optional<Role> getRoleByName(String roleName) {
        return Optional.ofNullable(this.roleRepository.findByRoleName(roleName).
                orElseThrow(() -> new RoleNotFoundException("Role with name " + roleName + " not found")));
    }
}
