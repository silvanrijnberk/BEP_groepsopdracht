package nl.hu.bep.gebruiker.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpClientConfig {
    @Value("${http-client.root-path.job}")
    private String rootPath;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
