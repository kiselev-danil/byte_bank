package com.example.byte_bank.service;

import com.example.byte_bank.form.UserRegistrationForm;
import com.example.byte_bank.view.UserView;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    Optional<UserView> getUserById(int id);

    Optional<UserView> getUserByUsername(String username);

    List<UserView> getAllUsers();

    Optional<Boolean> registerUser(UserRegistrationForm userRegistrationForm);

    Optional<Boolean> changePassword(int userId, String newPassword);
}
