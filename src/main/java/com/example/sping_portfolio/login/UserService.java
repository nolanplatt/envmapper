package com.example.sping_portfolio.login;

import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

// interface for methods that get user data
public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);

    List<User> getAll();
}