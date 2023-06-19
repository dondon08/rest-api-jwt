package com.test.demotest.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.demotest.entity.User;
import com.test.demotest.service.UserService;
import com.test.demotest.util.JwtTokenUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    /**
     * Endpoint untuk login pengguna.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) throws AuthenticationException {
        // Memvalidasi pengguna berdasarkan username dan password
        boolean isValidUser = userService.validateUser(authRequest.getUsername(), authRequest.getPassword());
        if (isValidUser) {
            // Memuat detail pengguna berdasarkan username yang diberikan
            final UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
            // Mengambil objek pengguna berdasarkan username
            final User user = userService.findByUsername(authRequest.getUsername());
            // Membangkitkan token JWT menggunakan userDetails dan user yang diperoleh sebelumnya
            final String token = jwtTokenUtil.generateToken(userDetails, user);
            // Menyusun respons dengan token sebagai nilai
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            // Mengembalikan respons dengan token sebagai respons body
            return ResponseEntity.ok(response);
        } else {
            // Jika pengguna tidak valid, mengembalikan respons dengan status 401 dan pesan kesalahan
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }
}

