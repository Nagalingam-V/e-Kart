package com.example.eKart.user.service;

import com.example.eKart.user.data.LoginData;
import com.example.eKart.user.data.UserData;
import com.example.eKart.user.domain.Users;
import com.example.eKart.user.domain.UsersRepository;
import com.example.eKart.user.exception.UserNotFoundException;
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
public class UserServiceImpl implements UserService{

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public UserData createUser(UserData userData) {
       userData.setPassword(passwordEncoder.encode(userData.getPassword()));
        Users user = mapToEntity(userData);
        return mapToResponse(usersRepository.save(user));
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
