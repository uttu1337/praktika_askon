package com.example.praktika_askon.mapper.user;

import com.example.praktika_askon.dto.user.UserCreateDto;
import com.example.praktika_askon.dto.user.UserResponseDto;
import com.example.praktika_askon.repository.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static UserResponseDto toResponseDto(UserEntity entity) {
        if (entity == null) return null;
        return UserResponseDto.builder()
                .id(entity.getId())
                .login(entity.getLogin())
                .email(entity.getEmail())
                .subscription(entity.getSubscription())
                .organization_id(entity.getOrganizationId())
                .build();
    }

    public static UserEntity fromCreateDto(UserCreateDto dto) {
        if (dto == null) return null;
        return UserEntity.builder()
                .login(dto.getLogin())
                .email(dto.getEmail())
                .passwordHash(dto.getPassword())
                .subscription(dto.getSubscriptionType())
                .deleted(false)
                .build();
    }

    public static void updateEntityFromDto(UserCreateDto dto, UserEntity entity) {
        if (dto == null || entity == null) return;
        entity.setLogin(dto.getLogin());
        entity.setEmail(dto.getEmail());
        // Остальные поля по бизнес-логике не меняем
    }
}
