package com.quantity.QuantityApp.service;

import com.quantity.QuantityApp.DTO.AuthResponseDTO;
import com.quantity.QuantityApp.DTO.UserLoginDTO;
import com.quantity.QuantityApp.DTO.UserRegisterDTO;
import com.quantity.QuantityApp.DTO.UserResponseDTO;
import com.quantity.QuantityApp.entity.userEntity;
import com.quantity.QuantityApp.repository.UserRepository;
import com.quantity.QuantityApp.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repo;

    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;
    public UserService(UserRepository repo,PasswordEncoder encoder,JwtUtil jwtUtil) {
        this.repo = repo;
        this.encoder = encoder;
        this.jwtUtil=jwtUtil;
    }

    public AuthResponseDTO register(UserRegisterDTO dto) {

        if (repo.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        userEntity user = new userEntity();
        user.setUserName(dto.getUserName());
        user.setEmail(dto.getEmail());
        user.setPassword(encoder.encode(dto.getPassword()));

        repo.save(user);

        String token = jwtUtil.generateToken(user.getEmail());

        return new AuthResponseDTO(token, "User Registered Successfully");
    }

    public AuthResponseDTO login(UserLoginDTO dto) {

        userEntity user = repo.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new AuthResponseDTO(token, "Login Successful");
    }
}
