package com.codecon.hackaton.hackanjos.controller;

import com.codecon.hackaton.hackanjos.model.User;
import com.codecon.hackaton.hackanjos.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private static final String REDIRECT_URL = "redirectUrl";

    /**
     * Redireciona o usuário para a URL de sucesso após autenticação via OAuth2.
     * @param oAuth2User
     * @param request
     * @return
     * Redireciona para a URL de sucesso ou para a página de erro se o usuário não estiver autenticado.
     */
    @GetMapping("/success")
    public RedirectView success(@AuthenticationPrincipal OAuth2User oAuth2User, HttpServletRequest request) {
        try {
            if (oAuth2User == null) {
                return new RedirectView("/error?message=Usuário não autenticado");
            }

            userService.processOAuth2User(oAuth2User);

            HttpSession session = request.getSession();
            String redirectUrl = (String) session.getAttribute(REDIRECT_URL);

            if (redirectUrl == null || redirectUrl.isEmpty()) {
                redirectUrl = "/";
            }

            session.removeAttribute(REDIRECT_URL);

            return new RedirectView(redirectUrl);
        } catch (Exception exception) {
            return new RedirectView("/error?message=" + exception.getMessage());
        }
    }

    /**
     * Retorna o usuário autenticado via OAuth2.
     *
     * @param oAuth2User Usuário autenticado pelo Spring Security.
     * @return ResponseEntity com informações do usuário ou erro se não autenticado.
     */
    @GetMapping("/user")
    public ResponseEntity<Map<String, Object>> getCurrentUser(@AuthenticationPrincipal OAuth2User oAuth2User) {
        if (oAuth2User == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Usuário não autenticado"));
        }

        User user = userService.processOAuth2User(oAuth2User);

        return ResponseEntity.ok(Map.of(
            "id", user.getId(),
            "email", user.getEmail(),
            "name", user.getName(),
            "picture", user.getPicture()
        ));
    }
}
