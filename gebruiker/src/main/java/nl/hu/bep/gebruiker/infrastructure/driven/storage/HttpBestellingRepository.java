package nl.hu.bep.gebruiker.infrastructure.driven.storage;

import nl.hu.bep.gebruiker.core.port.storage.BestellingenRepository;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class HttpBestellingRepository implements BestellingenRepository {
    private final String rootPath;
    private final RestTemplate client;

    public HttpBestellingRepository(String rootPath, RestTemplate client) {
        this.rootPath = rootPath;
        this.client = client;
    }

    @Override
    public List<UUID> findBestellingenByKeyword(String keyword) {
        URI uri = URI.create(this.rootPath + "/bestellingen?keyword=" + keyword);
        BestellingResult[] results = this.client.getForObject(uri, BestellingResult[].class);

        if (results == null) {
            return new ArrayList<>();
        }

        return Arrays.stream(results)
                .map(result -> result.id)
                .collect(Collectors.toList());
    }
}
