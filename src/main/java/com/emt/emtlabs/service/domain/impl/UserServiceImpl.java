package com.emt.emtlabs.service.domain.impl;

import com.emt.emtlabs.model.domain.User;
import com.emt.emtlabs.model.enumerations.Role;
import com.emt.emtlabs.model.exceptions.InvalidUserCredentialsException;
import com.emt.emtlabs.repository.UserRepository;
import com.emt.emtlabs.service.domain.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, Role role) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Username or password cannot be empty");
        }
        if (!password.equals(repeatPassword)) {
            throw new IllegalArgumentException("Passwords do not match");
        }
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Username '" + username + "' already exists");
        }
        User user = new User(username, passwordEncoder.encode(password), name, surname, role);
        return userRepository.save(user);
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new IllegalArgumentException();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException(username));
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new InvalidUserCredentialsException();
        return user;

    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username '" + username + "' not found"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username '" + username + "' not found"));
    }
}
