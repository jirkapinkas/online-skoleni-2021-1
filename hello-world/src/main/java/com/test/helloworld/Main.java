package com.test.helloworld;

import com.test.helloworld.service.ItemService;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@ComponentScan // skenuje aktualni balicek ("com.test.helloworld") a vsechny jeho podbalicky!!!
public class Main {

    @Bean
    public DataSource dataSource() {
        System.out.println("dataSource constructed!!!");
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:hsqldb:hsql://localhost/eshop");
        ds.setUsername("sa");
        ds.setPassword("");
        return ds;
    }

//    @Bean
//    public JdbcTemplate jdbcTemplate() {
//        System.out.println("jdbcTemplate constructed!!!");
//        return new JdbcTemplate(dataSource()); // POZOR! TOHLE FUNGUJE POUZE VE TRIDACH S ANOTACI @Configuration !!!
//    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        System.out.println("jdbcTemplate constructed!!!");
        return new JdbcTemplate(dataSource);
    }


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(Main.class);

        ItemService itemService = applicationContext.getBean(ItemService.class);
        System.out.println(itemService.getItemCount());

        applicationContext.close();
    }
}
