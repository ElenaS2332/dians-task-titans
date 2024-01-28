package com.example.demo;

import com.example.demo.model.JsonReader;
import com.example.demo.model.Wineries;
import com.example.demo.service.WineriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

import static java.lang.String.format;

@ServletComponentScan
@SpringBootApplication

public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Autowired
    private ResourceLoader resourceLoader;

    @Value("classpath:wineries.json")
    private Resource wineriesJson;
    @Bean
    public CommandLineRunner run(WineriesService wineriesService) {
        return args -> {
            try {
                JsonReader jsonReader = new JsonReader();
                final ClassPathResource classPathResource = new ClassPathResource(format("wineries.json", File.separator));
                final File jsonFile = classPathResource.getFile();
                List<Wineries> wineries = jsonReader.readWineryData(jsonFile.getPath());

                wineriesService.saveWineriesData(wineries);
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }
}
