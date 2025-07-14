package com.example.praktika_askon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.praktika_askon.repository.entity.OrganizationEntity;

import java.util.List;
import java.util.UUID;


@Repository
public interface OrganizationRepository extends JpaRepository<OrganizationEntity, UUID> {
    List<OrganizationEntity> findOrganizationEntityByDeletedFalse();
}