package com.deskera.developer.sampleproject.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetails {

    @JsonProperty(value = "given_name")
    private String givenName;

    @JsonProperty(value = "family_name")
    private String familyName;

    private Long userId;

    private Long tenantId;

    private String currency;

    private String isOrgSet;

    private boolean isSocialAuthUser;

    private String name;

    private String email;

    @JsonProperty(value = "phone_number")
    private String contact;

    private String token;

    private String iss;

    @JsonProperty(value = "auth_time")
    private Long authTime;

    private List<String> permissions;

    private Map<String, Object> properties = new HashMap<>();

    @JsonAnySetter
    public void setUnknown(String name, Object value) {
        properties.put(name, value);
    }
}
