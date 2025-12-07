package com.example.eKart.user.service;

import com.example.eKart.user.domain.Users;
import com.example.eKart.user.domain.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);
    private final UsersRepository usersRepository;;

    public CustomUserDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       Users users = usersRepository.findByUserName(username);

       if(Objects.isNull(users)){
           log.error("No User available with this UserName: "+ username);
           throw new UsernameNotFoundException("User Not found");
       }

       return new CustomUserDetails(users);
    }
}
