package com.cl.config;

import com.cl.util.PropertiesUtil;
import com.cl.config.security.MyUserDetailService;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created by evel on 2018/1/31.
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"com.cl.web"})
@MapperScan("com.cl.web.dao")
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private Environment env;

    private static final int CACHE_PERIOD = 31556926; // one year

    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    @PostConstruct
    public void init() {
        requestMappingHandlerAdapter.setIgnoreDefaultModelOnRedirect(true);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        // Serving static files using the Servlet container's default Servlet.
        configurer.enable();
    }

    @Bean
    public FreeMarkerConfigurer freemarkerConfig()throws IOException {
        Properties properties = PropertiesUtil.formClassPath("freemarker.properties");
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath("/WEB-INF/views/");
        freeMarkerConfigurer.setFreemarkerSettings(properties);
        return freeMarkerConfigurer;
    }

    @Bean("freemarkerResolver")
    public FreeMarkerViewResolver freemarkerResolver() {
        FreeMarkerViewResolver bean = new FreeMarkerViewResolver();
        bean.setCache(true);
        bean.setContentType("text/html; charset=UTF-8");
        bean.setViewClass(FreeMarkerView.class);
        bean.setExposeRequestAttributes(true);
        bean.setExposeSessionAttributes(true);
        bean.setExposeSpringMacroHelpers(true);
        bean.setRequestContextAttribute("request");
        bean.setSuffix(".ftl");
        return bean;
    }

    /**
     *  Mybatis
     * @return
     */
    @Bean("dataSource")
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/admin?characterEncoding=UTF-8");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(){
        return  new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws IOException{
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setConfigurationProperties(PropertiesUtil.formClassPath("mybatis-config.properties"));
        return sessionFactoryBean;
    }

    /**
     * spring security
     * @return
     */
    @Bean
    public ProviderManager providerManager(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(new MyUserDetailService());
        List<AuthenticationProvider> providers = Arrays.asList(provider);
        ProviderManager manager = new ProviderManager(providers);
        return manager;
    }



}
