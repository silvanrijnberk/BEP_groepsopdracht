package nl.hu.bep.bestelling.infrastructure.driven.storage;

import nl.hu.bep.bestelling.core.port.storage.GerechtRepository;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class HttpGerechtRepository implements GerechtRepository {
    private final String rootPath;
    private final RestTemplate client;

    public HttpGerechtRepository(String rootPath, RestTemplate client) {
        this.rootPath = rootPath;
        this.client = client;
    }


    @Override
    public List<UUID> findByKeywordsEquals(String keyword) {
        URI uri = URI.create(this.rootPath + "/gerecht?keyword=" + keyword);
        GerechtResult[] results = this.client.getForObject(uri, GerechtResult[].class);

        if (results == null) {
            return new ArrayList<>();
        }

        return Arrays.stream(results)
                .map(result -> result.id)
                .collect(Collectors.toList());
    }
}
