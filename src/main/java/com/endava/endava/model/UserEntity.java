package com.endava.endava.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Username is mandatory")
    @Column
    private String username;

    @Length(min = 6, message = "Password length must be more than 5 characters")
    @NotBlank(message = "Password is mandatory")
    @Column
    private String password;

    @Pattern(regexp = "(ROLE_USER|ROLE_ADMIN)")
    @NotBlank(message = "Role is mandatory")
    @Column
    private String role;

    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public void setRole(final String role) {
        this.role = role;
    }
}
