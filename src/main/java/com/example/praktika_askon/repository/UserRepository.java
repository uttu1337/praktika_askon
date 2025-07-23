package com.example.praktika_askon.repository;

import com.example.praktika_askon.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    List<UserEntity> findUserEntityByDeletedFalse();

    Optional<UserEntity> findByLogin(String login);
}
