package com.cl.domain;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by evel on 2018/2/2.
 */
public class Role implements GrantedAuthority {
    @Override
    public String getAuthority() {
        return "ADMIN";
    }
}
