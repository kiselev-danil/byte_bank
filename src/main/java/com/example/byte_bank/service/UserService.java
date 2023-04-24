package com.example.byte_bank.service;

import com.example.byte_bank.entity.RoleEntity;
import com.example.byte_bank.entity.UserEntity;
import com.example.byte_bank.form.UserRegistrationForm;
import com.example.byte_bank.repository.RoleRepository;
import com.example.byte_bank.repository.UserRepository;
import com.example.byte_bank.service.userDetails.MyUserDetails;
import com.example.byte_bank.view.UserView;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    UserRepository repositoryUser;
    RoleRepository repositoryRole;
    UserConverter converter;


    public Optional<UserView> getUserById(int id) {
        UserEntity entity = repositoryUser.findById(id).get();
        return Optional.of(converter.convertUserEntityToView(entity));
    }


    public Optional<UserView> getUserByUsername(String username) {
        Optional<UserEntity> userOptional = repositoryUser.findByLogin(username);
        if (userOptional.isEmpty()) {
            return Optional.empty();
        } else {
            UserEntity entity = userOptional.get();
            return Optional.of(converter.convertUserEntityToView(entity));
        }
    }


    public List<UserView> getAllUsers() {
        List<UserEntity> list = repositoryUser.findAll();
        return converter.ConvertArrayEntityToView(list);
    }

    public Optional<UserView> getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return getUserByUsername(auth.getName());
    }

    public Optional<Boolean> registerUser(UserRegistrationForm userRegistrationForm) {
//        repository.existsById()
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(userRegistrationForm.getUserLogin());
        userEntity.setBalance(userRegistrationForm.getBytesBalance());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(userRegistrationForm.getUserPassword());
        userEntity.setEncryptedPassword(encryptedPassword);
//        Collections.singleton(new RoleEntity(1, "ROLE_USER"));
        Set<RoleEntity> rs = new HashSet<>();
        rs.add(repositoryRole.findByName("ROLE_USER").get());
        userEntity.setRoles(rs);

        if (repositoryUser.exists(Example.of(userEntity))) {
            return Optional.empty();
        } else {
            repositoryUser.save(userEntity);
            return Optional.of(true);
        }
    }


    public Optional<Boolean> changePassword(int userId, String newPassword) {
        Optional<UserEntity> userOptional = repositoryUser.findById(userId);
        if (userOptional.isEmpty()) {
            return Optional.empty();
        }
        UserEntity userEntity = userOptional.get();

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(newPassword);
        userEntity.setEncryptedPassword(encryptedPassword);
        userEntity.setEncryptedPassword(encryptedPassword);

        repositoryUser.save(userEntity);
        return Optional.of(true);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUserDetails details = new MyUserDetails(repositoryUser.findByLogin(username).get());
        return details;
    }
}
