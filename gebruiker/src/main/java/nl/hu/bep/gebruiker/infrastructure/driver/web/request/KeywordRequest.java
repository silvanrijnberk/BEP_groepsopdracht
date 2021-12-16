package nl.hu.bep.gebruiker.infrastructure.driver.web.request;

import javax.validation.constraints.NotBlank;

public class KeywordRequest {
    @NotBlank
    public String keyword;
}
