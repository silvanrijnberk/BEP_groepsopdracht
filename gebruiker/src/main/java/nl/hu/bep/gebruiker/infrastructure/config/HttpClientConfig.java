package nl.hu.bep.gebruiker.infrastructure.config;

import nl.hu.bep.gebruiker.infrastructure.driven.storage.HttpAdresRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpClientConfig {
    @Value("${http-client.root-path.adres}")
    private String rootPath;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public HttpAdresRepository httpAdresRepository(){
        return new HttpAdresRepository(rootPath, restTemplate());
    }
}
