package com.example.byte_bank.repository;

import com.example.byte_bank.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    public Optional<UserEntity> findByLogin(String login);

}
