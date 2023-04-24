package com.example.byte_bank.service;

import com.example.byte_bank.entity.RoleEntity;
import com.example.byte_bank.entity.UserEntity;
import com.example.byte_bank.repository.RoleRepository;
import com.example.byte_bank.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AdminService extends UserService {
    public AdminService(UserRepository repositoryUser, RoleRepository repositoryRole, UserConverter converter) {
        super(repositoryUser, repositoryRole, converter);
    }

    public boolean deleteUserById(int userId) {
        if (repositoryUser.findById(userId).isEmpty()) {
            return false;
        }
        UserEntity entity = this.repositoryUser.findById(userId).get();
        repositoryUser.delete(entity);
        return true;
    }

    public boolean deleteUserByUsername(String userName) {
        if (repositoryUser.findByLogin(userName).isEmpty()) {
            return false;
        }
        UserEntity entity = this.repositoryUser.findByLogin(userName).get();
        repositoryUser.delete(entity);
        return true;
    }

    public boolean deleteAllUsers() {
        repositoryUser.deleteAll();
        return true;
    }

    public boolean creditToUserById(int bytesNumber, int userId) {
        if (repositoryUser.findById(userId).isEmpty()) {
            return false;
        }
        UserEntity entity = this.repositoryUser.findById(userId).get();
        entity.setBalance(entity.getBalance() + bytesNumber);
        repositoryUser.save(entity);
        return true;
    }

    public boolean creditToUserByUsername(int bytesNumber, String userName) {
        if (repositoryUser.findByLogin(userName).isEmpty()) {
            return false;
        }
        UserEntity entity = this.repositoryUser.findByLogin(userName).get();
        entity.setBalance(entity.getBalance() + bytesNumber);
        repositoryUser.save(entity);
        return true;
    }

    public void grantAdmineRole(int userId) {
        if (repositoryUser.findById(userId).isEmpty()) {
            return;
        }
        UserEntity user = repositoryUser.findById(userId).get();
        Set<RoleEntity> rs = user.getRoles();
        rs.add(repositoryRole.findByName("ROLE_ADMIN").get());
        user.setRoles(rs);
    }

}
