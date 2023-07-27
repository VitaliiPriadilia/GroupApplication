package com.employee.Group.services;

import com.employee.Group.models.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> getRoleByName(String roleName);
}
