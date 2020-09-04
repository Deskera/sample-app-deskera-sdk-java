package com.deskera.developer.sampleproject.config;

import com.deskera.sdk.common.client.ApiClient;
import com.deskera.sdk.common.client.ContactsApiClient;
import com.deskera.sdk.common.client.TenantsApiClient;
import com.deskera.sdk.common.client.OAuth2PartnerClient;
import com.deskera.sdk.common.dto.ENVIRONMENT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DeskeraConnectConfig {

    @Autowired
    private RestTemplate restTemplate;

    @Value(value = "${OAuth2PartnerClientId}")
    private String clientId;

    @Value(value = "${OAuth2PartnerSecret}")
    private String clientSecret;

    @Value(value = "${OAuth2PartnerRedirectUrl}")
    private String applicationCallbackUri;

    @Value(value = "${environment}")
    private ENVIRONMENT environment;

    @Bean
    @DependsOn("apiClient")
    public OAuth2PartnerClient oAuth2PartnerClient() {
        return new OAuth2PartnerClient();
    }

    @Bean
    @DependsOn("apiClient")
    public ContactsApiClient contactsApiClient() {
        return new ContactsApiClient();
    }

    @Bean
    @DependsOn("apiClient")
    public TenantsApiClient tenantsApiClient() {
        return new TenantsApiClient();
    }

    @Bean
    public ApiClient apiClient() {
        return new ApiClient(clientId, restTemplate, clientSecret, environment, applicationCallbackUri);
    }
}
