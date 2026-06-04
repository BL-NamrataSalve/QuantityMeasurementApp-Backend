package com.qm.quantitymeasurement.security;

import com.qm.quantitymeasurement.entity.UserEntity;
import com.qm.quantitymeasurement.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

    @org.springframework.beans.factory.annotation.Value("${FRONTEND_URL}")
    private String frontendUrl;

    @Autowired
    public OAuth2LoginSuccessHandler(UserRepository userRepository, JwtTokenProvider jwtTokenProvider, HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.httpCookieOAuth2AuthorizationRequestRepository = httpCookieOAuth2AuthorizationRequestRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        if (email == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Email not found from Google provider");
            return;
        }

        UserEntity user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            user = new UserEntity(email, name, null, "GOOGLE", "ROLE_USER");
            userRepository.save(user);
        } else {
            user.setName(name);
            user.setProvider("GOOGLE");
            userRepository.save(user);
        }

        String token = jwtTokenProvider.generateToken(email);
        String refreshToken = jwtTokenProvider.generateRefreshToken(email);

        clearAuthenticationAttributes(request, response);

        // Redirect the user back to the client application with the JWT token in a query parameter
        String targetUrl = frontendUrl + "/oauth2/redirect?token=" + token + "&refreshToken=" + refreshToken;
        
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }
}
