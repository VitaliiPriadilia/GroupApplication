package com.employee.Group.services.impl;

import com.employee.Group.dto.GroupDTO;
import com.employee.Group.exceptions.GroupNotFoundException;
import com.employee.Group.exceptions.IllegalGroupDataException;
import com.employee.Group.models.AppGroup;
import com.employee.Group.models.AppUser;
import com.employee.Group.repositories.GroupRepository;
import com.employee.Group.repositories.UserRepository;
import com.employee.Group.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository,
                            UserRepository userRepository){
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<GroupDTO> getAllUsersGroup(String username) {
        Optional<AppUser> userOptional = this.userRepository.findAppUserByUsername(username);
        if (userOptional.isPresent()) {
            List<AppGroup> appGroups = this.groupRepository.findAllByOwner(userOptional.get());
            if(appGroups.isEmpty()){
                throw new GroupNotFoundException("User " + username + " does not have any groups");
            }
            return appGroups.stream().map((group) -> {
                GroupDTO groupDTO = new GroupDTO();
                groupDTO.setId(group.getId());
                groupDTO.setName(group.getName());
                groupDTO.setToken(null);
                return groupDTO;
            }).toList();
        } else {
            throw new UsernameNotFoundException("User with username " + username + " not found");
        }
    }

    @Override
    public void createGroup(GroupDTO groupDTO, String username) {
        Optional<AppUser> userOptional = this.userRepository.findAppUserByUsername(username);
        if(userOptional.isPresent()){
            if(this.groupRepository.existsAppGroupByToken(groupDTO.getToken())){
                throw new IllegalGroupDataException(Collections.singletonList("This token already busy."));
            }
            AppGroup appGroup = new AppGroup();
            appGroup.setOwner(userOptional.get());
            appGroup.setName(groupDTO.getName());
            appGroup.setToken(groupDTO.getToken());
            this.groupRepository.save(appGroup);
        }else{
            throw new UsernameNotFoundException("User with username " + username + " not found");
        }
    }

    @Override
    public void joinUserToGroup(GroupDTO groupDTO, String username) {
        Optional<AppUser> userOptional = this.userRepository.findAppUserByUsername(username);
        if(userOptional.isPresent()){
            if(!this.groupRepository.existsAppGroupByNameAndToken(groupDTO.getToken(), groupDTO.getName())){
                throw new GroupNotFoundException("Groups not found");
            }
            userOptional.get().setInGroup(this.groupRepository.findAppGroupByTokenAndName(groupDTO.getToken(), groupDTO.getName()));
            this.userRepository.save(userOptional.get());
        }else{
            throw new UsernameNotFoundException("User with username " + username + " not found");
        }
    }

    @Override
    public void leaveUserFromGroup(Long id, String username) {
        Optional<AppUser> userOptional = this.userRepository.findAppUserByUsername(username);
        if(userOptional.isPresent()){
            if(!this.groupRepository.existsAppGroupById(id)){
                throw new GroupNotFoundException("Group not found");
            }
            if(!userOptional.get().getInGroup().getId().equals(id)){
                throw new IllegalGroupDataException(Collections.singletonList("User not in this group"));
            }
            userOptional.get().setInGroup(null);
            this.userRepository.save(userOptional.get());
        }else{
            throw new UsernameNotFoundException("User with username " + username + " not found");
        }
    }
}
