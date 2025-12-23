package org.example.javajdbctemplate.Transactions.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean // had l bean 3ndha nfs smiya de sa method
    public DataSource dataSource(){
        System.out.println("dans la class de configuration de database : dataSource bean");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost/transactions");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        return dataSource;

    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) { // il connais deja mon bean dataSource
        System.out.println("dans la classe config database : jdbc template");
        return new JdbcTemplate(dataSource);
    }
}
