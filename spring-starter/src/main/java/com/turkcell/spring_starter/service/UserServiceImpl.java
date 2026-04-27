package com.turkcell.spring_starter.service;

import org.springframework.stereotype.Service;

import com.turkcell.spring_starter.dto.LoginRequest;
import com.turkcell.spring_starter.dto.RegisterRequest;
import com.turkcell.spring_starter.repository.UserRepository;
import com.turkcell.spring_starter.entity.User;

import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserServiceImpl {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(RegisterRequest registerRequest) {

        User userWithSameEmail = this.userRepository.findByEmail(registerRequest.getEmail()).orElse(null);
        if(userWithSameEmail != null) {
            throw new RuntimeException("Bu e-posta zaten kayıtlı");
        }

        User user = new User();
        user.setEmail(registerRequest.getEmail());  

        String encodePassword = this.passwordEncoder.encode(registerRequest.getPassword());
        user.setPassword(encodePassword); // Şifrelenmiş halde kaydetme

        userRepository.save(user);

    }

    public String login(LoginRequest loginRequest) {
        User user = this.userRepository.findByEmail(loginRequest.getEmail())
            .orElseThrow(() -> new RuntimeException("Giriş Bilgileri Yanlış"));
        boolean passwordMatch = this.passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());
        if(!passwordMatch) {
        throw new RuntimeException("Giriş Bilgileri Yanlış");
    }

    return "Giriş Başarılı";
    }
}
