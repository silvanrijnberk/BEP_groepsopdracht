package nl.hu.bep.bestelling.infrastructure.config;
import nl.hu.bep.bestelling.infrastructure.driven.storage.HttpGerechtRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpClientConfig {

    @Value("${http-client.root-path.bestelling}")
    private String rootPath;

    @Bean
    public HttpGerechtRepository httpJobRepository() {
        return new HttpGerechtRepository(rootPath, restTemplate());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
