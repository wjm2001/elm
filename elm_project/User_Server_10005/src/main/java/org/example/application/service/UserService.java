package org.example.application.service;

import jakarta.annotation.Resource;
import org.example.domain.model.User;
import org.example.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Resource
    private UserRepository userRepository;

    public User findUserByUsernameAndPassword(String username, String password) {
        return userRepository.findUserByUsernameAndPassword(username,password);
    }

    public User saveUser(String username, String password, String name,Integer sex) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);
        user.setSex(sex);
        userRepository.save(user);
        return user;
    }

    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    public User findUserByUsername(String username){
        return userRepository.findUserByUsername(username);
    }
}
