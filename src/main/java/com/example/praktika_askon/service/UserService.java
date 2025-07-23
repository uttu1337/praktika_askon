package com.example.praktika_askon.service;

import com.example.praktika_askon.dto.user.UserCreateDto;
import com.example.praktika_askon.dto.user.UserResponseDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    List<UserResponseDto> getAllUsers();
    Optional<UserResponseDto> getUserById(UUID id);
    UserResponseDto createUser(UserCreateDto dto);
    Optional<UserResponseDto> updateUser(UUID id, UserCreateDto dto);
    void deleteUser(UUID id);

    void purchaseProduct(UserCreateDto dto);
}
