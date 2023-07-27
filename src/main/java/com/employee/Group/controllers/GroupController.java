package com.employee.Group.controllers;

import com.employee.Group.dto.GroupDTO;
import com.employee.Group.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/group")
public class GroupController {
    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService){
        this.groupService = groupService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<GroupDTO>> getAllUsersGroup(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUsername = authentication.getName();
        return new ResponseEntity<>(this.groupService.getAllUsersGroup(authenticatedUsername),HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> createGroup(@RequestBody GroupDTO groupDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUsername = authentication.getName();
        this.groupService.createGroup(groupDTO, authenticatedUsername);
        return new ResponseEntity<>("The group was created.",HttpStatus.OK);
    }

    @PostMapping("/join")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> joinToGroup(@RequestBody GroupDTO groupDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUsername = authentication.getName();
        //TODO: MAKE DATA CHECK
        this.groupService.joinUserToGroup(groupDTO, authenticatedUsername);
        return new ResponseEntity<>("Joined to group", HttpStatus.OK);
    }

    @PostMapping("/leave/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> leaveFromGroup(@PathVariable Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUsername = authentication.getName();
        //TODO: MAKE DATA CHECK

        return new ResponseEntity<>("Joined to group", HttpStatus.OK);
    }
}
