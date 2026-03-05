package lk.ijse.gdse.authservice.service.impl;

import lk.ijse.gdse.authservice.dto.UserDTO;
import lk.ijse.gdse.authservice.entity.User;
import lk.ijse.gdse.authservice.repository.UserRepository;
import lk.ijse.gdse.authservice.service.UserService;
import lk.ijse.gdse.authservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public User register(UserDTO userDTO) {
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        User user = User.builder()
                .username(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .build();

        return userRepository.save(user);
    }

    @Override
    public Map<String, String> login(UserDTO userDTO) {
        User user = userRepository.findByUsername(userDTO.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
            Map<String, String> tokens = new HashMap<>();
            tokens.put("accessToken", jwtUtil.generateAccessToken(user.getUsername()));
            tokens.put("refreshToken", jwtUtil.generateRefreshToken(user.getUsername()));
            return tokens;
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    @Override
    public String refreshAccessToken(String refreshToken) {
        if (jwtUtil.validateToken(refreshToken)) {
            String username = jwtUtil.getUsernameFromToken(refreshToken);
            return jwtUtil.generateAccessToken(username);
        } else {
            throw new RuntimeException("Invalid refresh token");
        }
    }


}
