package com.example.praktika_askon.service;

import jakarta.mail.MessagingException;

import java.io.File;

public interface EmailService {
    void sendEmailWithAttachment(String to, String subject, String message, File pluginFile) throws MessagingException;
}
