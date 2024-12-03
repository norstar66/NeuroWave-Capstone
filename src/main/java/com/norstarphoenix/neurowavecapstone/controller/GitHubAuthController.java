package com.norstarphoenix.neurowavecapstone.controller;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth/github")
public class GitHubAuthController {

    @GetMapping("/callback")
    public ResponseEntity<String> handleGitHubCallback(@RequestParam String code) {
        // Use the "code" to request an access token
        String accessToken = exchangeCodeForToken(code);

        // Save the token securely (e.g., in a session or database)
        // For now, return it as plain text for testing
        return ResponseEntity.ok("Access Token: " + accessToken);
    }

    private final Dotenv dotenv = Dotenv.load();

    private String exchangeCodeForToken(String code) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://github.com/login/oauth/access_token";

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("client_id", System.getenv("GITHUB_CLIENT_ID"));
        body.add("client_secret", System.getenv("GITHUB_CLIENT_SECRET"));
        body.add("code", code);
        body.add("redirect_uri", "http://localhost:8080/auth/github/callback");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);

        return (String) response.getBody().get("access_token");
    }

    @GetMapping("/codespaces")
    public ResponseEntity<List<Map<String, Object>>> getUserCodespaces(@RequestParam String accessToken) {
        List<Map<String, Object>> codespaces = fetchUserCodespaces(accessToken);
        return ResponseEntity.ok(codespaces);
    }

    private List<Map<String, Object>> fetchUserCodespaces(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.github.com/user/codespaces";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        HttpEntity<Void> request = new HttpEntity<>(headers);

        ResponseEntity<List> response = restTemplate.exchange(url, HttpMethod.GET, request, List.class);
        return response.getBody();
    }
    private final Map<String, String> tokenStore = new HashMap<>();

    public void saveToken(String userId, String token) {
        tokenStore.put(userId, token);
    }

    public String getToken(String userId) {
        return tokenStore.get(userId);
    }
}