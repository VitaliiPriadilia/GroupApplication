package com.employee.Group.services;

import com.employee.Group.dto.GroupDTO;

import java.util.List;

public interface GroupService {
    List<GroupDTO> getAllUsersGroup(String username);
    void createGroup(GroupDTO groupDTO, String username);
    void joinUserToGroup(GroupDTO groupDTO, String username);
    void leaveUserFromGroup(Long id, String username);
}
