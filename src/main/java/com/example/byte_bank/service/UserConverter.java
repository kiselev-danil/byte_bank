package com.example.byte_bank.service;

import com.example.byte_bank.entity.UserEntity;
import com.example.byte_bank.view.UserView;

import java.util.ArrayList;
import java.util.List;

public class UserConverter {
    public List<UserView> ConvertArrayEntityToView(List<UserEntity> list) {
        List<UserView> result = new ArrayList<>();
        for (UserEntity entity : list) {
            result.add(convertUserEntityToView(entity));
        }
        return result;
    }

    public UserView convertUserEntityToView(UserEntity entity) {
        return new UserView(entity.getId(), entity.getLogin(), entity.getBalance());
    }
}
