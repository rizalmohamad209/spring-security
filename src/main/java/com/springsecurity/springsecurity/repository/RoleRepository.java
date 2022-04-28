package com.springsecurity.springsecurity.repository;

import com.springsecurity.springsecurity.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    RoleEntity findByName(String name);


}
