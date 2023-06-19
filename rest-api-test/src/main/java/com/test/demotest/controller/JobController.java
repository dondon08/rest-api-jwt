package com.test.demotest.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.test.demotest.util.JwtTokenUtil;

@RestController
@RequestMapping("/api")
public class JobController {

    private final JwtTokenUtil jwtTokenUtil;
    
    //URL endpoint
    private final String apiUrl = "http://dev3.dansmultipro.co.id/api/recruitment";

    @Autowired
    public JobController(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @GetMapping("/jobs")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getJobList() {
        // Mendapatkan token JWT dari autentikasi saat ini
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token = (String) authentication.getCredentials();

        // Membangun request entity dengan token JWT di header Authorization
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        RequestEntity<Void> requestEntity = 
        		new RequestEntity<>(headers, HttpMethod.GET, UriComponentsBuilder.fromHttpUrl(apiUrl + "/positions.json").build().toUri());

        // Melakukan permintaan HTTP ke URL yang diberikan
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

        // Mengembalikan response dari permintaan HTTP
        return ResponseEntity.ok(responseEntity.getBody());
    }
    
    @GetMapping("/jobs/{id}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getJobDetail(@PathVariable("id") String id) {
        // Mendapatkan token JWT dari autentikasi saat ini
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token = (String) authentication.getCredentials();

        // Membangun request entity dengan token JWT di header Authorization
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        RequestEntity<Void> requestEntity = 
        		new RequestEntity<>(headers, HttpMethod.GET, URI.create(apiUrl + "/positions/" + id));

        // Melakukan permintaan HTTP ke URL yang diberikan
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

        // Mengembalikan response dari permintaan HTTP
        return ResponseEntity.ok(responseEntity.getBody());
    }
}