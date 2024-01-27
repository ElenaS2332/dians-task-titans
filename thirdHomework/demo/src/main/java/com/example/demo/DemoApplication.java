package com.example.demo;

import com.example.demo.model.JsonReader;
import com.example.demo.model.Wineries;
import com.example.demo.service.WineriesService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


import java.io.IOException;
import java.util.List;

@ServletComponentScan
@SpringBootApplication
@EnableEurekaServer
@ComponentScan(basePackages = "com.example")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(WineriesService wineriesService) {
        return args -> {
            try {
                JsonReader jsonReader = new JsonReader();
                List<Wineries> wineries = jsonReader.readWineryData("dians-task-titans\\thirdHomework\\demo\\src\\main\\resources\\wineries.json");

                wineriesService.saveWineriesData(wineries);
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

}
