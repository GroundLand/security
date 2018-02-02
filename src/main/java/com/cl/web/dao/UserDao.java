package com.cl.web.dao;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

/**
 * Created by evel on 2018/2/2.
 */
@Service
public interface UserDao {
    User getByUserName(String username);
}
