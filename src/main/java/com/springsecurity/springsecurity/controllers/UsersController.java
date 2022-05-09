package com.springsecurity.springsecurity.controllers;

import com.springsecurity.springsecurity.entity.RoleEntity;

import com.springsecurity.springsecurity.entity.UsersEntity;
import com.springsecurity.springsecurity.exception.ResourceNotFoundException;
import com.springsecurity.springsecurity.services.UsersServices;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController @RequiredArgsConstructor
public class UsersController {
    private final UsersServices usersServices;
    @GetMapping("/users")
    public ResponseEntity<List<UsersEntity>> getAllUsers(){
        return ResponseEntity.ok().body(usersServices.getAllUsers());
    }

    @PostMapping("/signup")
    public ResponseEntity<UsersEntity>Post(@RequestBody UsersEntity body){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users").toUriString());
        return ResponseEntity.created(uri).body(usersServices.signUp(body));
    }

    @GetMapping("/usersbyusername")
    public ResponseEntity<UsersEntity> GetuserById(@RequestBody UsersEntity body) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(usersServices.getUserByUsername(body.getUsername()));
    }

    @PostMapping("/role")
    public ResponseEntity<RoleEntity>Post(@RequestBody RoleEntity body){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users").toUriString());

        return  ResponseEntity.created(uri).body(usersServices.post(body));
    }

    @GetMapping("/role")
    public ResponseEntity<List<RoleEntity>> Get(){
        return ResponseEntity.ok().body(usersServices.getAllRole());
    }


    @PostMapping("/role/addtouser")
    public ResponseEntity<?>PostRoleToUser(@RequestBody RoleToUserForm form) throws ResourceNotFoundException{
        usersServices.addRoleToUser(form.getUsername(),form.getRoleName());
        return ResponseEntity.ok().build();
    }




}

@Data
class RoleToUserForm{
    private String username;
    private String roleName;
}
