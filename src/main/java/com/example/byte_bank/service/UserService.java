package com.example.byte_bank.service;

import com.example.byte_bank.entity.UserEntity;
import com.example.byte_bank.form.UserRegistrationForm;
import com.example.byte_bank.repository.UserRepository;
import com.example.byte_bank.view.UserView;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    UserRepository repository;
    UserConverter converter;

    @Override
    public Optional<UserView> getUserById(int id) {
        UserEntity entity = repository.findById(id).get();
        return Optional.of(converter.convertUserEntityToView(entity));
    }

    @Override
    public Optional<UserView> getUserByUsername(String username) {
        Optional<UserEntity> userOptional = repository.findByUsername(username);
        if (userOptional.isEmpty()) {
            return Optional.empty();
        } else {
            UserEntity entity = userOptional.get();
            return Optional.of(converter.convertUserEntityToView(entity));
        }
    }

    @Override
    public List<UserView> getAllUsers() {
        List<UserEntity> list = repository.findAll();
        return converter.ConvertArrayEntityToView(list);
    }

    @Override
    public Optional<Boolean> registerUser(UserRegistrationForm userRegistrationForm) {
//        repository.existsById()
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(userRegistrationForm.getUserLogin());
        userEntity.setBalance(userRegistrationForm.getBytesBalance());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(userRegistrationForm.getUserPassword());
        userEntity.setEncryptedPassword(encryptedPassword);

        if (repository.exists(Example.of(userEntity))) {
            return Optional.empty();
        } else {
            repository.save(userEntity);
            return Optional.of(true);
        }
    }

    @Override
    public Optional<Boolean> changePassword(int userId, String newPassword) {
        Optional<UserEntity> userOptional = repository.findById(userId);
        if (userOptional.isEmpty()) {
            return Optional.empty();
        }
        UserEntity userEntity = userOptional.get();

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(newPassword);
        userEntity.setEncryptedPassword(encryptedPassword);
        userEntity.setEncryptedPassword(encryptedPassword);

        repository.save(userEntity);
        return Optional.of(true);
    }
}
