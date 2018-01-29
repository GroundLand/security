package com.cl.config.intializer;

import com.cl.config.SecurityConfig;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Created by evel on 2018/1/24.
 */
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
    public SecurityWebApplicationInitializer() {
        super(SecurityConfig.class);
    }
}
