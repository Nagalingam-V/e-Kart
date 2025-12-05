package com.example.eKart.user.service;

import com.example.eKart.user.data.LoginData;
import com.example.eKart.user.data.UserData;
import org.apache.catalina.User;

public interface UserService {

    public UserData createUser(UserData userData);

    Boolean loginUser(LoginData loginData);
}
