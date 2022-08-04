package com.druiz.bs10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Db1Application {

    public static void main(String[] args) {
        SpringApplication.run(Db1Application.class, args);
    }

}
