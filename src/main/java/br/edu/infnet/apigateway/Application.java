package br.edu.infnet.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    RouteLocator routeLocator(final RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/voting/v1/voting-api/**").uri("http://localhost:8081")
                )
                .build();
    }

}
