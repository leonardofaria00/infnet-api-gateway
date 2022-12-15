package br.edu.infnet.apigateway;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoutesController {

    @Bean
    RouteLocator routeLocator(final RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/voting/v1/voting-api/**")
                        .filters(f -> f.circuitBreaker(
                                config -> config
                                        .setName("mycmd")
                                        .setFallbackUri("forward:/fallback")
                        ))
                        .uri("http://localhost:8081")
                )
                .build();
    }

    @GetMapping(value = "/fallback", produces = MediaType.APPLICATION_JSON_VALUE)
    public String fallback() {
        return "{\"message\":\"Serviço indisponível, tente novamente mais tarde.\"}";
    }
}
