package com.example.praktika_askon.controller.organization;

import com.example.praktika_askon.dto.organization.OrganizationCreateDto;
import com.example.praktika_askon.dto.organization.OrganizationResponseDto;
import com.example.praktika_askon.service.OrganizationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/organizations")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    @GetMapping
    public ResponseEntity<List<OrganizationResponseDto>> getAllOrganizations() {
        List<OrganizationResponseDto> organizations = organizationService.getAllOrganizations();
        return ResponseEntity.ok(organizations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizationResponseDto> getOrganizationById(@PathVariable UUID id) {
        return organizationService.getOrganizationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OrganizationResponseDto> createOrganization(
            @Valid @RequestBody OrganizationCreateDto createDto) {
        OrganizationResponseDto created = organizationService.createOrganization(createDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrganizationResponseDto> updateOrganization(
            @PathVariable UUID id,
            @Valid @RequestBody OrganizationCreateDto updateDto) {
        return organizationService.updateOrganization(id, updateDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrganization(@PathVariable UUID id) {
        organizationService.deleteOrganization(id);
        return ResponseEntity.noContent().build();
    }
}
