package com.example.byte_bank.service;

import com.example.byte_bank.entity.UserEntity;
import com.example.byte_bank.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class AdminService extends UserService {
    public AdminService(UserRepository repository, UserConverter converter) {
        super(repository, converter);
    }

    public boolean deleteUserById(int userId) {
        if (repository.findById(userId).isEmpty()) {
            return false;
        }
        UserEntity entity = this.repository.findById(userId).get();
        repository.delete(entity);
        return true;
    }

    public boolean deleteUserByUsername(String userName) {
        if (repository.findByLogin(userName).isEmpty()) {
            return false;
        }
        UserEntity entity = this.repository.findByLogin(userName).get();
        repository.delete(entity);
        return true;
    }

    public boolean deleteAllUsers() {
        repository.deleteAll();
        return true;
    }

    public boolean creditToUserById(int bytesNumber, int userId) {
        if (repository.findById(userId).isEmpty()) {
            return false;
        }
        UserEntity entity = this.repository.findById(userId).get();
        entity.setBalance(entity.getBalance() + bytesNumber);
        repository.save(entity);
        return true;
    }

    public boolean creditToUserByUsername(int bytesNumber, String userName) {
        if (repository.findByLogin(userName).isEmpty()) {
            return false;
        }
        UserEntity entity = this.repository.findByLogin(userName).get();
        entity.setBalance(entity.getBalance() + bytesNumber);
        repository.save(entity);
        return true;
    }
}
