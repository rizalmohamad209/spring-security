package com.springsecurity.springsecurity.services;

import com.springsecurity.springsecurity.entity.RoleEntity;
import com.springsecurity.springsecurity.entity.UsersEntity;
import com.springsecurity.springsecurity.exception.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import javax.management.relation.Role;
import java.util.List;

@Component
public interface UsersServices {
    UsersEntity signUp(UsersEntity body);
    RoleEntity post(RoleEntity body);
    void addRoleToUser(String username, String roleName) throws ResourceNotFoundException;
    UsersEntity getUserByUsername(String username) throws ResourceNotFoundException ;
    List<UsersEntity> getAllUsers();

    List<RoleEntity> getAllRole();

}
