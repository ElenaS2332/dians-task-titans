package com.example.demo;

import com.example.demo.model.JsonReader;
import com.example.demo.model.Wineries;
import com.example.demo.service.WineriesService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.AbstractDiscoveryClientOptionalArgs;
import com.netflix.discovery.EurekaClientConfig;
import com.netflix.discovery.shared.resolver.EurekaEndpoint;
import com.netflix.discovery.shared.transport.EurekaHttpClient;
import com.netflix.discovery.shared.transport.TransportClientFactory;
import com.netflix.discovery.shared.transport.jersey.TransportClientFactories;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@EnableEurekaServer
public class DemoApplication {

    public static final String REGISTRATION_SERVER_HOSTNAME = "registration.server.hostname";

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "registration-server");
        SpringApplication.run(DemoApplication.class, args);
    }

    @Configuration
    static class EurekaClientConfig {
        @Bean
        public AbstractDiscoveryClientOptionalArgs<?> discoveryClientOptionalArgs() {
            return new AbstractDiscoveryClientOptionalArgs() {};
        }

        @Bean
        public TransportClientFactories transportClientFactories() {
            return new TransportClientFactories() {
                @Override
                public TransportClientFactory newTransportClientFactory(com.netflix.discovery.EurekaClientConfig clientConfig, Collection additionalFilters, InstanceInfo myInstanceInfo) {
                    return new NewTransportClientFactory(clientConfig, additionalFilters, myInstanceInfo);
                }

                @Override
                public TransportClientFactory newTransportClientFactory(com.netflix.discovery.EurekaClientConfig clientConfig, Collection additionalFilters, InstanceInfo myInstanceInfo, Optional optional, Optional optional2) {
                    return new NewTransportClientFactory(clientConfig, additionalFilters, myInstanceInfo);
                }
            };
        }

    }

//    @Configuration
//    public class RestTemplateConfig {
//        @Bean
//        public RestTemplate restTemplate() {
//            return new RestTemplate();
//        }
//    }
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
