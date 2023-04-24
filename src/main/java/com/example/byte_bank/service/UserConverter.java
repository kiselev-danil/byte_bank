package com.example.byte_bank.service;

import com.example.byte_bank.entity.RoleEntity;
import com.example.byte_bank.entity.UserEntity;
import com.example.byte_bank.view.RoleView;
import com.example.byte_bank.view.UserView;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserConverter {
    public List<UserView> ConvertArrayEntityToView(List<UserEntity> list) {
        List<UserView> result = new ArrayList<>();
        for (UserEntity entity : list) {
            result.add(convertUserEntityToView(entity));
        }
        return result;
    }

    public UserView convertUserEntityToView(UserEntity entity) {
        Set<RoleEntity> rolesEtities = entity.getRoles();
        Set<RoleView> rolesViews = new HashSet<>();
        for (RoleEntity role : rolesEtities) {
            RoleView rv = new RoleView(role.getId(), role.getName());
            rolesViews.add(rv);
        }
        return new UserView(entity.getId(), entity.getLogin(), entity.getBalance(), rolesViews);
    }
}
