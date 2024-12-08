package com.norstarphoenix.neurowavecapstone.serviceTests;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.norstarphoenix.neurowavecapstone.config.GitHubAppJwt;
import com.norstarphoenix.neurowavecapstone.services.GitHubApiService;

class GitHubApiServiceTest {

    @Test
    void testGetInstallationToken() throws Exception {
        // Mock dependencies
        GitHubAppJwt jwtGenerator = mock(GitHubAppJwt.class);
        RestTemplate restTemplate = mock(RestTemplate.class);

        // Mock JWT generation
        when(jwtGenerator.generateJwt()).thenReturn("mocked_jwt");

        // Mock API response
        String installationId = "123";
        String installationToken = "mocked_token";
        Map<String, String> responseBody = Map.of("token", installationToken);

        ResponseEntity<Map> mockResponse = new ResponseEntity<>(responseBody, HttpStatus.OK);
        when(restTemplate.exchange(
                anyString(),
                eq(HttpMethod.POST),
                any(HttpEntity.class),
                eq(Map.class),
                eq(installationId)
        )).thenReturn(mockResponse);

        // Test the service
        GitHubApiService service = new GitHubApiService(jwtGenerator, restTemplate);
        String token = service.getInstallationToken(installationId);

        // Verify
        assertEquals(installationToken, token);
        verify(jwtGenerator).generateJwt();
        verify(restTemplate).exchange(
                anyString(),
                eq(HttpMethod.POST),
                any(HttpEntity.class),
                eq(Map.class),
                eq(installationId)
        );
    }
}