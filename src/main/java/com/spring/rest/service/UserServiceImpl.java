package com.spring.rest.service;

import com.spring.rest.models.Role;
import com.spring.rest.models.User;
import com.spring.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @Override
    public List<User> listUser () {
        return userRepository.findAll();
    }

    @Override
    public void addUser (User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void removeUser (long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUser (User user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public User getUserById (long id) {
        return userRepository.findById(id).orElse(null);
    }

    @PostConstruct
    @Transactional
    public User addTestAdmin() {

        User admin = new User();
        admin.setUsername("admin");
        admin.setLastname("admin");
        admin.setAge(33);
        admin.setEmail("admin@mail.ru");
        admin.setPassword(bCryptPasswordEncoder.encode("admin"));
        admin.addRole((new Role(admin.getId(), "Admin")));
        return userRepository.save(admin);
    }
}