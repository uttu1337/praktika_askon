package com.example.praktika_askon.service.organization;

import com.example.praktika_askon.dto.organization.OrganizationCreateDto;
import com.example.praktika_askon.dto.organization.OrganizationResponseDto;
import com.example.praktika_askon.mapper.organization.OrganizationMapper;
import com.example.praktika_askon.repository.OrganizationRepository;
import com.example.praktika_askon.repository.entity.OrganizationEntity;
import com.example.praktika_askon.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;

    @Override
    public List<OrganizationResponseDto> getAllOrganizations() {
        return organizationRepository.findOrganizationEntityByDeletedFalse()
                .stream()
                .map(OrganizationMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OrganizationResponseDto> getOrganizationById(UUID id) {
        return organizationRepository.findById(id)
                .filter(org -> !Boolean.TRUE.equals(org.getDeleted()))
                .map(OrganizationMapper::toResponseDto);
    }

    @Override
    public OrganizationResponseDto createOrganization(OrganizationCreateDto createDto) {
        OrganizationEntity entity = OrganizationMapper.fromCreateDto(createDto);
        OrganizationEntity saved = organizationRepository.save(entity);
        return OrganizationMapper.toResponseDto(saved);
    }

    @Override
    public Optional<OrganizationResponseDto> updateOrganization(UUID id, OrganizationCreateDto updateDto) {
        Optional<OrganizationEntity> entityOptional = organizationRepository.findById(id)
                .filter(org -> !Boolean.TRUE.equals(org.getDeleted()));

        if (entityOptional.isEmpty()) {
            return Optional.empty();
        }

        OrganizationEntity entity = entityOptional.get();
        OrganizationMapper.updateEntityFromDto(updateDto, entity);
        OrganizationEntity saved = organizationRepository.save(entity);
        return Optional.of(OrganizationMapper.toResponseDto(saved));
    }

    @Override
    public void deleteOrganization(UUID id) {
        Optional<OrganizationEntity> entityOptional = organizationRepository.findById(id)
                .filter(org -> !Boolean.TRUE.equals(org.getDeleted()));

        if (entityOptional.isEmpty()) {
            return;
        }

        OrganizationEntity entity = entityOptional.get();
        entity.setDeleted(true);
        entity.setDeletedAt(LocalDateTime.now());
        organizationRepository.save(entity);
    }
}
