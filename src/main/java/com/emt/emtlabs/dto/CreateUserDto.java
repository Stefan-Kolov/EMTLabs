package com.emt.emtlabs.dto;

import com.emt.emtlabs.model.domain.User;
import com.emt.emtlabs.model.enumerations.Role;

public record CreateUserDto(String username,
                            String password,
                            String repeatPassword,
                            String name,
                            String surname,
                            Role role
) {
    public User toUser(){
        return new User(username, password, name, surname, role);
    }
}
