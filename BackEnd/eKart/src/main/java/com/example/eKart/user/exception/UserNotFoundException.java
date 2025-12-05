package com.example.eKart.user.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String userName) {
        super("No user are available with this user_name "+ userName );
    }
}
