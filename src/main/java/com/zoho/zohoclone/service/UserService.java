package com.zoho.zohoclone.service;

import com.zoho.zohoclone.dto.UserDTO;
import com.zoho.zohoclone.entity.User;
import com.zoho.zohoclone.repository.UserRepository;
import com.zoho.zohoclone.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IuserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String login(String username, String password) {
        return userRepository.findByUsername(username)
                .filter(user -> user.getPassword().equals(password))
                .map(user -> jwtUtil.generateToken(user.getUsername()))
                .orElse(null);
    }

    public void registerUser(UserDTO userDTO) {
        // validate username/email
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        userRepository.save(user);
    }

}
