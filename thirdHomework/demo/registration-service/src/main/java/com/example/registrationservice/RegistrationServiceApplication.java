package com.example.registrationservice;

import com.example.demo.DemoApplication;
import com.netflix.discovery.AbstractDiscoveryClientOptionalArgs;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.http.EurekaClientHttpRequestFactorySupplier;
import org.springframework.cloud.netflix.eureka.http.RestTemplateDiscoveryClientOptionalArgs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@Import(RegistrationConfiguration.class)
public class RegistrationServiceApplication {

    @Configuration
    public class EurekaClientConfig {

        @Bean
        public RestTemplateDiscoveryClientOptionalArgs discoveryClientOptionalArgs(
                EurekaClientHttpRequestFactorySupplier eurekaClientHttpRequestFactorySupplier) {
            return new RestTemplateDiscoveryClientOptionalArgs(eurekaClientHttpRequestFactorySupplier);
        }
    }

    @Configuration
    public class RestTemplateConfig {
        @Bean
        public RestTemplate restTemplate() {
            return new RestTemplate();
        }
    }

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "accounts-server");
        SpringApplication.run(RegistrationServiceApplication.class, args);
    }

}
