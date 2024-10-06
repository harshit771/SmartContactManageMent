package com.scm.scm.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.scm.scm.entities.Provider;
import com.scm.scm.entities.User;
import com.scm.scm.helper.ContactConstant;
import com.scm.scm.repository.UserRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {

    Logger logger = LoggerFactory.getLogger(OAuthSuccessHandler.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        var user = (DefaultOAuth2User) authentication.getPrincipal();
        var oauth2 = (OAuth2AuthenticationToken) authentication;

        String authorizedClientRegistrationId = oauth2.getAuthorizedClientRegistrationId();

        User user2 = new User();
        user2.setUserId(UUID.randomUUID().toString());
        user2.setPassword(passwordEncoder.encode("Password"));
        user2.setRoleList(List.of(ContactConstant.ROLE_USER));
        user2.setEmailVerified(true);
        
        if (authorizedClientRegistrationId.equals("google")) {
            user2.setEmail(user.getAttribute("email").toString());
            user2.setProfilePic(user.getAttribute("picture"));
            user2.setProvider(Provider.GOOGLE);
            user2.setProviderUserId(user.getName());
            user2.setAbout("This is generated by google");
            user2.setName(user.getAttribute("name").toString());
        }else if(authorizedClientRegistrationId.equals("github")){
            String email=user.getAttribute("email").toString() != null 
            ? user.getAttribute("email").toString() : user.getAttribute("login").toString() +"@gmail.com" ;

            String picture=user.getAttribute("avatar_url").toString();
            
            user2.setEmail(email);
            user2.setProfilePic(picture);
            user2.setProviderUserId(user.getName());
            user2.setName(user.getAttribute("login").toString());
            user2.setAbout("This is created by github");
            user2.setProvider(Provider.GITHUB);

        }

        User user3 = userRepository.findByEmail(user2.getEmail()).orElse(null);
        if (user3 == null) {
            userRepository.save(user2);
        }

        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");
    }

}
