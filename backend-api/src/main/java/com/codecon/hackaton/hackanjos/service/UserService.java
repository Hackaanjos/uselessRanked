package com.codecon.hackaton.hackanjos.service;

import com.codecon.hackaton.hackanjos.model.User;
import com.codecon.hackaton.hackanjos.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User processOAuth2User(OAuth2User oAuth2User) {
        try {
            if (oAuth2User == null) {
                throw new IllegalArgumentException("OAuth2User não pode ser nulo");
            }

            String email = oAuth2User.getAttribute("email");
            String googleId = oAuth2User.getAttribute("sub");
            String name = oAuth2User.getAttribute("name");
            String picture = oAuth2User.getAttribute("picture");

            if (email == null || googleId == null) {
                throw new IllegalArgumentException("Email e Google ID são obrigatórios");
            }

            return userRepository.findByEmail(email)
                    .map(existingUser -> {
                        existingUser.setName(name);
                        existingUser.setPicture(picture);
                        existingUser.setGoogleId(googleId);
                        return userRepository.save(existingUser);
                    })
                    .orElseGet(() -> {
                        User newUser = new User();
                        newUser.setEmail(email);
                        newUser.setName(name);
                        newUser.setPicture(picture);
                        newUser.setGoogleId(googleId);
                        return userRepository.save(newUser);
                    });
        } catch (Exception exception) {
            throw new RuntimeException("Erro ao processar usuário OAuth2: " + exception.getMessage(), exception);
        }
    }
}
