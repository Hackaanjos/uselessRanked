package com.codecon.hackaton.hackanjos.controller;

import lombok.AllArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class HomeController {

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String index(
            @RequestParam(required = false) String error,
            @RequestParam(required = false) String message) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = authentication != null &&
                                authentication.isAuthenticated() &&
                                !"anonymousUser".equals(authentication.getPrincipal());

        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>");
        html.append("<html>");
        html.append("<head>");
        html.append("<title>Useless Ranked</title>");
        html.append("<style>");
        html.append("body { font-family: Arial, sans-serif; margin: 40px; }");
        html.append(".message { margin-bottom: 20px; }");
        html.append(".error { color: red; }");
        html.append(".success { color: green; }");
        html.append(".link { display: inline-block; margin-top: 20px; padding: 10px 20px; background-color: #007bff; color: white; text-decoration: none; border-radius: 5px; }");
        html.append(".link:hover { background-color: #0056b3; }");
        html.append("</style>");
        html.append("</head>");
        html.append("<body>");

        if (isAuthenticated && authentication.getPrincipal() instanceof OAuth2User) {
            OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
            html.append("<div class='message'>Olá, ").append((String) oAuth2User.getAttribute("name")).append("!</div>");
            html.append("<a href='http://localhost:8080/api/auth/logout' class='link'>Fazer Logout</a>");
        } else {
            html.append("<div class='message'>Olá mundo!</div>");
            html.append("<a href='http://localhost:8080/oauth2/authorization/google' class='link'>Fazer Login com Google</a>");
        }

        if (error != null) {
            html.append("<div class='message error'>").append(error).append("</div>");
        }

        if (message != null) {
            html.append("<div class='message success'>").append(message).append("</div>");
        }

        html.append("</body>");
        html.append("</html>");

        return html.toString();
    }
}
