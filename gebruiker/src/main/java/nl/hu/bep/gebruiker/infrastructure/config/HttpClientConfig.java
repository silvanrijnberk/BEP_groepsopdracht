package nl.hu.bep.gebruiker.infrastructure.config;
import nl.hu.bep.gebruiker.infrastructure.driven.storage.HttpBestellingRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpClientConfig {

    @Value("${http-client.root-path.bestelling}")
    private String rootPath;

    @Bean
    public HttpBestellingRepository HttpBestellingRepository() {
        return new HttpBestellingRepository(rootPath, restTemplate());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
