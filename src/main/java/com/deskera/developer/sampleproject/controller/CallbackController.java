package com.deskera.developer.sampleproject.controller;

import com.deskera.developer.sampleproject.constants.Constants;
import com.deskera.developer.sampleproject.dto.UserDetails;
import com.deskera.developer.sampleproject.entities.UserSample;
import com.deskera.developer.sampleproject.service.UserService;
import com.deskera.sdk.common.client.OAuth2PartnerClient;
import com.deskera.sdk.common.dto.auth.OAuth2AccessToken;
import com.deskera.sdk.common.util.constants.ApiConstants;
import com.deskera.sdk.common.util.exception.BadRequestException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.Objects;

@Log4j2
@RestController
@RequestMapping(value = Constants.CALLBACK_API, produces = MediaType.APPLICATION_JSON_VALUE)
public class CallbackController {

    @Autowired
    private OAuth2PartnerClient oAuth2PartnerClient;

    @Autowired
    private UserService userService;

    @ApiOperation(value = Constants.PROCESS_CALLBACK,
            notes = Constants.PROCESS_CALLBACK,
            response = Void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = Constants.PROCESS_CALLBACK_SUCCESS_RESPONSE),
            @ApiResponse(code = 404, message = Constants.PROCESS_CALLBACK_FAILURE_RESPONSE)})
    @GetMapping
    public ResponseEntity<Void> processCallBack(
            @RequestParam(name = ApiConstants.CODE_CONST) final String authCode) {
        if (Objects.isNull(authCode)) {
            throw new BadRequestException(
                    "authCode is null. Tokens could not be generated");
        }
        try {
            OAuth2AccessToken oAuth2AccessToken = this.oAuth2PartnerClient.connectWithDeskera(authCode);

            String userDetailsJson = extractUserDetailsfromToken(oAuth2AccessToken.getDeskeraToken());
            ObjectMapper objectMapper = new ObjectMapper();
            UserDetails ud = objectMapper.readValue( userDetailsJson, UserDetails.class);

            UserSample user = new UserSample();
            user.setUserId(String.valueOf(ud.getUserId()));
            user.setAccessToken(oAuth2AccessToken.getDeskeraToken());
            user.setRefreshToken(oAuth2AccessToken.getRefreshToken());
            user.setUserDetails(userDetailsJson);
            userService.update(user);
        } catch (Exception e) {
            log.error("The Callback response failed with status : " + HttpStatus.INTERNAL_SERVER_ERROR,
                    e);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private String extractUserDetailsfromToken(String deskeraToken) throws JsonProcessingException {
        String[] tokenParts = deskeraToken.split("\\.");
        return new String(Base64.getDecoder().decode(tokenParts[1]));
    }
}