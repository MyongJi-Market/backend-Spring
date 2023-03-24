package org.myongjimarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MyongJiMarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyongJiMarketApplication.class, args);
    }

}
