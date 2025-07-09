package com.ecommerce.userauthservice;

import com.ecommerce.userauthservice.security.models.Client;
import com.ecommerce.userauthservice.security.repositories.ClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserAuthServiceApplicationTests {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    RegisteredClientRepository registeredClientRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Test
    void addClientDetails() {

        Optional<Client> client = clientRepository.findByClientId("jay-client");

//        Checking if Client is already present else load the client to Client Repo
        if (client.isPresent()) {
            assertNotNull(client, "Value should not be null");
        } else {
            RegisteredClient oidcClient = RegisteredClient.withId(UUID.randomUUID().toString())
                    .clientId("jay-client")
                    .clientSecret(passwordEncoder.encode("secretPassword"))
                    .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                    .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                    .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                    .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                    .redirectUri("https://oauth.pstmn.io/v1/callback")
                    .postLogoutRedirectUri("https://oauth.pstmn.io/v1/callback")
                    .scope("ADMIN")
                    .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                    .build();
            registeredClientRepository.save(oidcClient);
        }
    }
}
