package com.example.byte_bank.entity;

import com.example.byte_bank.view.RoleView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleEntity implements GrantedAuthority {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;

    public RoleEntity(int id) {
        this.id = id;
    }

    public RoleEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Column
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<UserEntity> users;

    @Override
    public String getAuthority() {
        return name;
    }
}
