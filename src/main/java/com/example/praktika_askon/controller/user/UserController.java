package com.example.praktika_askon.controller.user;

import com.example.praktika_askon.dto.user.UserCreateDto;
import com.example.praktika_askon.dto.user.UserResponseDto;
import com.example.praktika_askon.service.EmailService;
import com.example.praktika_askon.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    @Value("${plugin.path}")
    private String pluginPath;

    private final UserService userService;
    private final EmailService emailService;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable UUID id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(
            @Valid @RequestBody UserCreateDto createDto) {
        UserResponseDto created = userService.createUser(createDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable UUID id,
            @Valid @RequestBody UserCreateDto updateDto) {
        return userService.updateUser(id, updateDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/purchase")
    public ResponseEntity<String> purchaseProduct(@Valid @RequestBody UserCreateDto dto) {

        userService.purchaseProduct(dto);
        return ResponseEntity.ok("Product successfully purchased");
    }

    @GetMapping("/test")
    public String sendTestEmail() {
        File file = new File(pluginPath); // или любой другой файл

        try {
            emailService.sendEmailWithAttachment(
                    "gaindrang@gmail.com",
                    "Тестовое письмо",
                    "Привет! Это тест отправки письма с вложением.",
                    file
            );

            return "Письмо отправлено!";

        } catch (MessagingException e) {

            throw new RuntimeException(e);
        }
    }
}
