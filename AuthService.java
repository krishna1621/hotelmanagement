package com.clarit.hs.service.authservice;

import com.clarit.hs.controller.IAuthService;

import com.clarit.hs.service.Exception.RestTemplateResponseErrorHandler;
import com.clarit.hs.service.items.model.LoginRequest;
import com.clarit.hs.service.items.model.LoginResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Service
public class AuthService implements IAuthService {
        private static  final Logger logger = LoggerFactory.getLogger(AuthService.class);
    @Autowired
   RestTemplate restTemplate;
    @Value("${spring.security.oauth2.client.provider.keycloak.issuer-uri}")
    private String issueUri;
    @Value("${spring.security.oauth2.client.registration.oauth2-client-credentials.client_id}")
    private String clientId;
    @Value("${spring.security.oauth2.client.registration.oauth2-client-credentials.client_secret}")
    private String clientSecret;
    @Value("${spring.security.oauth2.client.registration.oauth2-client-credentials.authorization-grant-type}")
    private String grantType;

    @Value("${spring.security.oauth2.client.provider.keycloak.token-uri}")
    private String tokenUrl;
    @Value("${spring.security.oauth2.client.registration.oauth2-client-credentials.scope}")
    private String scopeUrl;
    @Value("${keycloak.auth-server-url}")
    private String baseUrl;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.errorHandler(new RestTemplateResponseErrorHandler()).build();
    }
 


    @Override
    public ResponseEntity<LoginResponse> getTokenBy(LoginRequest loginRequest) {
        logger.info("Getting the token - {}",loginRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", clientId);
        map.add("client_secret", clientSecret);
        map.add("grant_type", grantType);
        map.add("scope",scopeUrl);
        map.add("username", loginRequest.getUsername());
        map.add("password", loginRequest.getPassword());

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(map, headers);
        ResponseEntity<LoginResponse> response = restTemplate.postForEntity(tokenUrl, httpEntity, LoginResponse.class);
        logger.info("Token generated Successfully");
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);

    }
 
}




