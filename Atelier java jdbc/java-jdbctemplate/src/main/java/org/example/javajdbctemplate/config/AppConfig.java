package org.example.javajdbctemplate.config;

import org.example.javajdbctemplate.mappers.MyMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "org.example.javajdbctemplate")
public class AppConfig {

    @Bean
    public MyMapper createMapper(){
        return new MyMapper() ;
    }

}
