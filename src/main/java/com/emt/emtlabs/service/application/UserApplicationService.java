package com.emt.emtlabs.service.application;

import com.emt.emtlabs.dto.CreateUserDto;
import com.emt.emtlabs.dto.DisplayUserDto;
import com.emt.emtlabs.dto.LoginUserDto;

import java.util.Optional;

public interface UserApplicationService {
    Optional<DisplayUserDto> register(CreateUserDto createUserDto);

    Optional<DisplayUserDto> login(LoginUserDto loginUserDto);

    Optional<DisplayUserDto> findByUsername(String username);
}
