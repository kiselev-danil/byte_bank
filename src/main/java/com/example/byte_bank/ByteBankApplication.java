package com.example.byte_bank;

import com.example.byte_bank.entity.RoleEntity;
import com.example.byte_bank.entity.UserEntity;
import com.example.byte_bank.repository.RoleRepository;
import com.example.byte_bank.repository.UserRepository;
import com.example.byte_bank.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class ByteBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(ByteBankApplication.class, args);
    }

    @Bean
    CommandLineRunner roles(RoleRepository roleRepository) {
        return args -> {
            Optional<RoleEntity> userRoleEntity = roleRepository.findByName("ROLE_USER");
            Optional<RoleEntity> adminRoleEntity = roleRepository.findByName("ROLE_ADMIN");
            if (userRoleEntity.isEmpty()) {
                RoleEntity userRole = new RoleEntity(1, "ROLE_USER");
                roleRepository.save(userRole);
            }
            if (adminRoleEntity.isEmpty()) {
                RoleEntity adminRole = new RoleEntity(2, "ROLE_ADMIN");
                roleRepository.save(adminRole);
            }
        };
    }

}
