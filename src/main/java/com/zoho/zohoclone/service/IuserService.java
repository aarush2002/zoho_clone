package com.zoho.zohoclone.service;

import com.zoho.zohoclone.dto.UserDTO;

public interface IuserService {
    String  login(String username, String password);

    void registerUser(UserDTO userDTO);
}
