package com.example.byte_bank.view;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserView {
    private Integer id;
    private String login;
    private int balance;
    private Set<RoleView> roleName;
}
