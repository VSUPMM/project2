package com.example.demo.model;

import com.example.demo.model.result.Result;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
/*import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;*/

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@Entity
@Table(name = "user", schema = "public")
public class User /*implements UserDetails*/ {

    public static final String ROLE_STUDENT="STUDENT";
    public static final String ROLE_TEACHER="ADMIN";

    @Id @GeneratedValue
    @Column(name = "userId")
    private Long userId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "course")
    private Integer course;

    @Column(name = "user_group")
    private String user_group;

    @Column(name = "role")
    private String role;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @JsonManagedReference(value="UserResult-movement")
    @OneToMany(mappedBy="user", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<Result> result;

    public User() {
    }

    public User(String name, String surname,  String login, String password) {
        this.name = name;
        this.surname = surname;
        //this.role = role;
        this.login = login;
        this.password = password;
    }

    /*@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_"+ role));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }*/

    public Long getUserId() {
        return userId;
    }
}