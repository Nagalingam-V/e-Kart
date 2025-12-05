package com.example.eKart.user.service;

import com.example.eKart.user.data.LoginData;
import com.example.eKart.user.data.UserData;
import com.example.eKart.user.domain.Users;
import com.example.eKart.user.domain.UsersRepository;
import com.example.eKart.user.exception.UserNotFoundException;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserData createUser(UserData userData) {
       userData.setPassword(passwordEncoder.encode(userData.getPassword()));
        Users user = mapToEntity(userData);
        return mapToResponse(usersRepository.save(user));
    }

    @Override
    public Boolean loginUser(LoginData loginData) {
        Users user = usersRepository.findByUserName(loginData.getUserName());
        if(Objects.isNull(user)){
            log.error("Entered Wrong User name");
            throw new UserNotFoundException(loginData.getUserName());
        }

       if(!passwordEncoder.matches(loginData.getPassword(), user.getPassword())){
           log.error("Password missMatch, kindly reEnter correct password");
           throw new RuntimeException("Password is Incorrect");
       }

        return true;
    }


    public Users mapToEntity(UserData userData){
        Users user = new Users();
        user.setFirstName(userData.getFirstName());
        user.setLastName(userData.getLastName());
        user.setEmail(userData.getEmail());
        user.setPassword(userData.getPassword());
        user.setUserName(userData.getUserName());

        return user;
    }

    public UserData mapToResponse(Users user){
        UserData userData = new UserData();
        userData.setId(user.getId());
        userData.setFirstName(user.getFirstName());
        userData.setLastName(user.getLastName());
        userData.setEmail(user.getEmail());
        userData.setUserName(user.getUserName());
        userData.setPassword(user.getPassword());

        return userData;
    }
}
