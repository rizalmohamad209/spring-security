package com.springsecurity.springsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
}
