package fr.rating.concert.service;

import fr.rating.concert.config.PasswordEncoder;
import fr.rating.concert.dto.LoginRequestDto;
import fr.rating.concert.dto.RegisterRequestDto;
import fr.rating.concert.dto.UserResponseDto;
import fr.rating.concert.entity.User;
import fr.rating.concert.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = new PasswordEncoder();

    public UserResponseDto register(RegisterRequestDto dto) {
        if (userRepository.findByEmail(dto.getEmail()) != null) {
            throw new RuntimeException("User already exists");
        }
        String PasswordHash = passwordEncoder.encode(dto.getRawPassword());
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPasswordHash(PasswordHash);
        userRepository.save(user);
        return new UserResponseDto(user.getUsername(), user.getEmail());

    }

    public UserResponseDto authenticate(LoginRequestDto dto) {
        User user = userRepository.findByEmail(dto.getEmail());
        if (passwordEncoder.matches(dto.getPassword(), user.getPasswordHash())) {
            return new UserResponseDto(user.getUsername(), user.getEmail());
        }
        return null;
    }
}
