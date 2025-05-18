package com.codecon.hackaton.hackanjos.controller;

import com.codecon.hackaton.hackanjos.model.User;
import com.codecon.hackaton.hackanjos.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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
    private static final String FRONTEND_URL = "http://localhost:4200";

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
                return new RedirectView(FRONTEND_URL + "/error?message=Usuário não autenticado");
            }

            userService.processOAuth2User(oAuth2User);

            HttpSession session = request.getSession();
            String redirectUrl = (String) session.getAttribute(REDIRECT_URL);

            if (redirectUrl == null || redirectUrl.isEmpty()) {
                redirectUrl = FRONTEND_URL;
            }

            session.removeAttribute(REDIRECT_URL);

            return new RedirectView(redirectUrl);
        } catch (Exception exception) {
            return new RedirectView(FRONTEND_URL + "/error?message=" + exception.getMessage());
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

    /**
     * Realiza o logout do usuário e redireciona para a página inicial.
     * Se o usuário não estiver autenticado, redireciona para a página inicial com mensagem de erro.
     *
     * @param request HttpServletRequest para acessar a sessão
     * @param response HttpServletResponse para configurar os headers de resposta
     * @return RedirectView para a página inicial
     */
    @GetMapping("/logout")
    public RedirectView logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getPrincipal())) {
            return new RedirectView(FRONTEND_URL + "?error=Usuário não está autenticado");
        }

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        new SecurityContextLogoutHandler().logout(request, response, auth);

        SecurityContextHolder.clearContext();

        return new RedirectView(FRONTEND_URL + "?message=Logout realizado com sucesso");
    }
}
