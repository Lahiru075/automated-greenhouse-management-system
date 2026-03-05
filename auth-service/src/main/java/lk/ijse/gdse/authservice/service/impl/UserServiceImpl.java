package lk.ijse.gdse.authservice.service.impl;

import lk.ijse.gdse.authservice.dto.UserDTO;
import lk.ijse.gdse.authservice.entity.User;
import lk.ijse.gdse.authservice.repository.UserRepository;
import lk.ijse.gdse.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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
}
