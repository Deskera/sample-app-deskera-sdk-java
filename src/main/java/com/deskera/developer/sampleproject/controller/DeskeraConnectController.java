package com.deskera.developer.sampleproject.controller;

import com.deskera.developer.sampleproject.constants.Constants;
import com.deskera.developer.sampleproject.dto.ConnectUrl;
import com.deskera.developer.sampleproject.service.UserService;
import com.deskera.sdk.common.client.OAuth2PartnerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@RestController
@RequestMapping(value = Constants.CONNECT_API, produces = MediaType.APPLICATION_JSON_VALUE)
public class DeskeraConnectController {

    @Autowired
    private OAuth2PartnerClient oAuth2PartnerClient;

    @Autowired
    private UserService userService;

    @Value(value = "${OAuth2PartnerClientId}")
    private String clientId;

    @Value(value = "${deskeraOAuthUri}")
    private String oauthUri;

    @Value(value = "${applicationUrl}")
    private String applicationUri;

    @GetMapping
    public ResponseEntity<ConnectUrl> connectToDeskera() throws UnsupportedEncodingException {
        String url = oauthUri + Constants.DESKERA_CONNECT_URL_PARAMS;
        url = url.replace(Constants.PLACEHOLDER_CLIENT_ID, clientId)
                .replace(Constants.PLACEHOLDER_SCOPE, Constants.DESKERA_DEFAULT_SCOPE)
                .replace(Constants.PLACEHOLDER_STATE, UUID.randomUUID().toString())
                .replace(Constants.PLACEHOLDER_REDIRECT_URI,
                        URLEncoder.encode(applicationUri, StandardCharsets.UTF_8.toString()));
        final ConnectUrl connectUrl = new ConnectUrl();
        connectUrl.setUrl(url);
        return new ResponseEntity<ConnectUrl>(connectUrl, HttpStatus.OK);
    }

    @GetMapping("/status")
    public ResponseEntity<ConnectUrl> isConnected() {
        long userCount = this.userService.getUserCount();
        final ConnectUrl connectUrl = new ConnectUrl();
        connectUrl.setConnected(userCount > 0);
        if(userCount > 0) {
            connectUrl.setUserDetails(this.userService.getFirstUser().getUserDetails());
        }
        return new ResponseEntity<ConnectUrl>(connectUrl, HttpStatus.OK);
    }

    @GetMapping("/disconnect")
    public ResponseEntity<Void> disconnect() {
        this.userService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}