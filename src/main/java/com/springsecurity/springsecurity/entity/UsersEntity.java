package com.springsecurity.springsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class UsersEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    @Column(length = 255, unique = true,nullable = false)
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<RoleEntity> roles = new ArrayList<>();
}
