package com.example.praktika_askon.service.user;

import com.example.praktika_askon.dto.user.UserCreateDto;
import com.example.praktika_askon.dto.user.UserResponseDto;
import com.example.praktika_askon.mapper.user.UserMapper;
import com.example.praktika_askon.repository.UserRepository;
import com.example.praktika_askon.repository.entity.UserEntity;
import com.example.praktika_askon.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public List<UserResponseDto> getAllUsers() {

        return userRepository.findUserEntityByDeletedFalse()
                .stream()
                .map(UserMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserResponseDto> getUserById(UUID id) {

        return userRepository.findById(id)
                .filter(user -> !Boolean.TRUE.equals(user.getDeleted()))
                .map(UserMapper::toResponseDto);
    }

    @Override
    public UserResponseDto createUser(UserCreateDto createDto) {

        UserEntity entity = UserMapper.fromCreateDto(createDto);
        entity.setPasswordHash(passwordEncoder.encode(createDto.getPassword()));

        UserEntity saved = userRepository.save(entity);

        return UserMapper.toResponseDto(saved);
    }

    @Override
    public Optional<UserResponseDto> updateUser(UUID id, UserCreateDto dto) {
        return userRepository.findById(id).map(user -> {

            if (dto.getLogin() != null) {
                user.setLogin(dto.getLogin());
            }

            if (dto.getEmail() != null) {
                user.setEmail(dto.getEmail());
            }

            if (dto.getOrganizationId() != null) {
                user.setOrganizationId(dto.getOrganizationId());
            }

            if (dto.getSubscriptionType() != null) {
                user.setSubscription(dto.getSubscriptionType());
            }

            // Если пришёл новый пароль — хэшируем
            if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
                user.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
            }

            UserEntity updated = userRepository.save(user);
            return UserMapper.toResponseDto(updated);
        });
    }

    @Override
    public void deleteUser(UUID id) {
        Optional<UserEntity> entityOptional = userRepository.findById(id)
                .filter(user -> !Boolean.TRUE.equals(user.getDeleted()));

        if (entityOptional.isEmpty()) {
            return;
        }

        UserEntity entity = entityOptional.get();
        entity.setDeleted(true);
        entity.setDeletedAt(LocalDateTime.now());
        userRepository.save(entity);
    }
}
