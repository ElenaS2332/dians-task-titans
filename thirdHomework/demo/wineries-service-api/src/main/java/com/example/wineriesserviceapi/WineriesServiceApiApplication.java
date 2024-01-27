package com.example.wineriesserviceapi;

import com.netflix.discovery.AbstractDiscoveryClientOptionalArgs;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableDiscoveryClient
public class WineriesServiceApiApplication {

    @Configuration
    static class EurekaClientConfig {
        @Bean
        public AbstractDiscoveryClientOptionalArgs<?> discoveryClientOptionalArgs() {
            return new AbstractDiscoveryClientOptionalArgs<Object>() {};
        }
    }
    public static void main(String[] args) {
        SpringApplication.run(WineriesServiceApiApplication.class, args);
    }

}
