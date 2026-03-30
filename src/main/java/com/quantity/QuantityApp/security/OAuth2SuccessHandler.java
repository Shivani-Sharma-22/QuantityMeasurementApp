package com.quantity.QuantityApp.security;

import com.quantity.QuantityApp.entity.userEntity;
import com.quantity.QuantityApp.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.*;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final UserRepository repo;
    private final JwtUtil jwtUtil;

    public OAuth2SuccessHandler(UserRepository repo, JwtUtil jwtUtil) {
        this.repo = repo;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        Optional<userEntity> existingUser = repo.findByEmail(email);

        userEntity user;

        if (existingUser.isPresent()) {
            user = existingUser.get();
        } else {
            user = new userEntity();
            user.setEmail(email);
            user.setUserName(name);
            user.setPassword(""); // no password
            repo.save(user);
        }

        // Generate JWT
        String token = jwtUtil.generateToken(email);

        // Send token in response
        response.getWriter().write("JWT Token: " + token);
    }
}