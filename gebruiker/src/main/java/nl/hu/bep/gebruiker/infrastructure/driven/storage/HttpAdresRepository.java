package nl.hu.bep.gebruiker.infrastructure.driven.storage;

import nl.hu.bep.gebruiker.core.port.storage.AdresRepository;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class HttpAdresRepository implements AdresRepository {
    private final String rootpath;
    private final RestTemplate client;

    public HttpAdresRepository(String rootpath, RestTemplate client) {
        this.rootpath = rootpath;
        this.client = client;
    }

    @Override
    public List<UUID> findByKeywordsEquals(String keyword) {
        URI uri = URI.create(this.rootpath + "/adres?keyword=" + keyword);
        AdresResult[] results = this.client.getForObject(uri, AdresResult[].class);

        if (results == null) {
            return new ArrayList<>();
        }

        return Arrays.stream(results)
                .map(result -> result.id)
                .collect(Collectors.toList());

    }
}
