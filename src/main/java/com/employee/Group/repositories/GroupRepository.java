package com.employee.Group.repositories;

import com.employee.Group.models.AppGroup;
import com.employee.Group.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<AppGroup, Long> {
    List<AppGroup> findAllByOwner(AppUser owner);
    Boolean existsAppGroupByToken(String owner);
    Boolean existsAppGroupById(Long id);
    Boolean existsAppGroupByNameAndToken(String name, String token);
    AppGroup findAppGroupByTokenAndName(String token, String name);
    AppGroup findAppGroupById(Long id);
}
