package com.test.helloworld;

import com.test.helloworld.annotation.JdbcProfile;
import com.test.helloworld.service.ItemService;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@ComponentScan // skenuje aktualni balicek ("com.test.helloworld") a vsechny jeho podbalicky!!!
public class Main {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private Environment environment;

    @JdbcProfile
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

    @JdbcProfile
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        System.out.println("jdbcTemplate constructed!!!");
        return new JdbcTemplate(dataSource);
    }


    public static void main(String[] args) {
        // Staci pridat do VM Options:
        // -Dspring.profiles.active=dummy
        // NEBO:
        // -Dspring.profiles.active=jdbc
//        AnnotationConfigApplicationContext applicationContext
//                = new AnnotationConfigApplicationContext(Main.class);

        // NEBO:
        AnnotationConfigApplicationContext applicationContext
                = new AnnotationConfigApplicationContext();
        applicationContext.getEnvironment().setActiveProfiles("dummy");
        applicationContext.register(Main.class);
        applicationContext.refresh();

        ItemService itemService = applicationContext.getBean(ItemService.class);
        System.out.println(itemService.getItemCount());

        applicationContext.close();
    }
}
