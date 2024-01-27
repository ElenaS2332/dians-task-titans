package com.example.wineriesserviceapi;

import com.netflix.discovery.AbstractDiscoveryClientOptionalArgs;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.http.EurekaClientHttpRequestFactorySupplier;
import org.springframework.cloud.netflix.eureka.http.RestTemplateDiscoveryClientOptionalArgs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableDiscoveryClient
public class WineriesServiceApiApplication {

    @Configuration
    public class EurekaClientConfig {
        @Bean
        public RestTemplateDiscoveryClientOptionalArgs discoveryClientOptionalArgs(
                EurekaClientHttpRequestFactorySupplier eurekaClientHttpRequestFactorySupplier) {
            return new RestTemplateDiscoveryClientOptionalArgs(eurekaClientHttpRequestFactorySupplier);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(WineriesServiceApiApplication.class, args);
    }

}
