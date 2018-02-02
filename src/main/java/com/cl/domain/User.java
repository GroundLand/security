package com.cl.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

public class User extends org.springframework.security.core.userdetails.User{
    private Long id;


    public User(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public Collection<GrantedAuthority> getAuthorities() {
        Role role  = new Role();
        return CollectionUtils.arrayToList(role);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}