package com.example.eKart.user.service;

import com.example.eKart.user.data.LoginData;
import com.example.eKart.user.data.UserData;
import com.example.eKart.user.data.UserRegisteredEvent;
import com.example.eKart.user.domain.Users;
import com.example.eKart.user.domain.UsersRepository;
import com.example.eKart.user.exception.UserNotFoundException;
import com.example.eKart.user.messaging.producer.UserEventProducer;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserEventProducer userEventProducer;


    @Override
    public UserData createUser(UserData userData) {
       userData.setPassword(passwordEncoder.encode(userData.getPassword()));
        Users user = mapToEntity(userData);
        UserData createdUser = mapToResponse(usersRepository.save(user));
        //send Message to User Email
        UserRegisteredEvent event = new UserRegisteredEvent(createdUser.getId(),createdUser.getEmail(), createdUser.getFirstName() +" "+ createdUser.getLastName());
        userEventProducer.sendUserRegisteredEvent(event);

        return createdUser;
    }

    @Override
    public String loginUser(LoginData loginData) {

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginData.getUserName(), loginData.getPassword())
        );

       if(authenticate.isAuthenticated()){
           Users user = usersRepository.findByUserName(loginData.getUserName());
           return jwtService.generateToken(user);
       }
        return "failed to log-in";
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
