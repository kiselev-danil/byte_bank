package com.example.byte_bank.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserEntity {

    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    private Integer id;

    @Column(nullable = false, unique = true, length = 45)
    private String login;

    @Column(nullable = false, length = 64)
    private String encryptedPassword;

    @Column
    private int balance;
}
