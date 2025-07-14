package com.example.praktika_askon.mapper.organization;

import com.example.praktika_askon.dto.organization.OrganizationCreateDto;
import com.example.praktika_askon.dto.organization.OrganizationResponseDto;
import com.example.praktika_askon.repository.entity.OrganizationEntity;
import org.springframework.stereotype.Component;

@Component
public class OrganizationMapper {

    public static OrganizationResponseDto toResponseDto(OrganizationEntity entity) {
        if (entity == null) return null;
        return OrganizationResponseDto.builder()
                .id(entity.getId())
                .orgName(entity.getOrgName())
                .version(entity.getVersion())
                .deleted(entity.getDeleted())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }

    public static OrganizationEntity fromCreateDto(OrganizationCreateDto dto) {
        if (dto == null) return null;
        return OrganizationEntity.builder()
                .orgName(dto.getOrgName())
                .deleted(false)
                .build();
    }

    public static void updateEntityFromDto(OrganizationCreateDto dto, OrganizationEntity entity) {
        if (dto == null || entity == null) return;
        entity.setOrgName(dto.getOrgName());
        // Остальные поля по бизнес-логике не меняем
    }
}
