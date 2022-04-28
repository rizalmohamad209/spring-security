package com.springsecurity.springsecurity.services;

import com.springsecurity.springsecurity.entity.RoleEntity;
import com.springsecurity.springsecurity.entity.UsersEntity;
import com.springsecurity.springsecurity.exception.ResourceNotFoundException;
import com.springsecurity.springsecurity.repository.RoleRepository;
import com.springsecurity.springsecurity.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UsersServices, UserDetailsService {

    @Autowired
    private final UsersRepository usersRepository;

    @Autowired
    private final RoleRepository roleRepository;

    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;






    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersEntity user = usersRepository.findByUsername(username);
        if(user != null){
            log.info("User found in the database : {}", username);
        }else{
            log.error("User not found in the database");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
    @Override
    public UsersEntity signUp(UsersEntity body) {
        UsersEntity userExists = usersRepository.findByUsername(body.getUsername());
        if(userExists != null){
            throw new RuntimeException(String.format("User with username '%s' already exists",body.getUsername())
            );
        }
        String encodePassword = bCryptPasswordEncoder.encode(body.getPassword());
        body.setPassword(encodePassword);
        return usersRepository.save(body);
    }

    @Override
    public RoleEntity post(RoleEntity body) {
        return roleRepository.save(body);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        UsersEntity users = usersRepository.findByUsername(username);
        RoleEntity role = roleRepository.findByName(roleName);
        users.getRoles().add(role);
    }

    @Override
    public UsersEntity getUserByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    @Override
    public List<UsersEntity> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public List<RoleEntity> getAllRole() {
        return roleRepository.findAll();
    }


}
