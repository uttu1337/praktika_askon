package com.example.praktika_askon.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDto {

    private String login;
    private String password;
    private String email;
    private UUID organizationId;
    private String subscriptionType;
}
