package nl.hu.bep.beoordeling.infrastructure.driver.web.request;

import javax.validation.constraints.NotBlank;

public class KeywordRequest {
    @NotBlank
    public String keyword;
}
