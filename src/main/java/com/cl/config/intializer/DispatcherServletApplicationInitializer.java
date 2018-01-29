package com.cl.config.intializer;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * Created by evel on 2018/1/29.
 */
public class DispatcherServletApplicationInitializer extends AbstractDispatcherServletInitializer{
    @Override
    protected WebApplicationContext createServletApplicationContext() {
        XmlWebApplicationContext context = new XmlWebApplicationContext();
        context.setConfigLocation("classpath:spring/spring-mvc.xml");
        return context;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected WebApplicationContext createRootApplicationContext() {
        return null;
    }

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[] { new HiddenHttpMethodFilter(), new CharacterEncodingFilter("UTF-8",true) };
    }
}

