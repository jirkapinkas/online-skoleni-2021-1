package com.test.helloworld;

import com.test.helloworld.service.ItemService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan // skenuje aktualni balicek ("com.test.helloworld") a vsechny jeho podbalicky!!!
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(Main.class);

        ItemService itemService = applicationContext.getBean(ItemService.class);
        System.out.println(itemService.getItemCount());

        applicationContext.close();
    }
}
