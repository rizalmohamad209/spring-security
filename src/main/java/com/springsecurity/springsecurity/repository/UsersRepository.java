package com.springsecurity.springsecurity.repository;

import com.springsecurity.springsecurity.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<UsersEntity, Integer> {
    UsersEntity findByUsername(String username);
}
