package com.scm.scm.helper;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

public class Helper {

    public static String getEmailOfLoggedInUser(Authentication authentication){

        if(authentication instanceof OAuth2AuthenticationToken){
            var oAuth2AuthenticationToken =(OAuth2AuthenticationToken)authentication;
            var authorizedClientRegistrationId = oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
            String email="";
            if(authorizedClientRegistrationId.equalsIgnoreCase("google")){
                 email = oAuth2AuthenticationToken.getPrincipal().getAttribute("email").toString();
            }else if(authorizedClientRegistrationId.equalsIgnoreCase("github")){
                email=oAuth2AuthenticationToken.getPrincipal().getAttribute("email").toString() != null 
            ? oAuth2AuthenticationToken.getPrincipal().getAttribute("email").toString() 
            : oAuth2AuthenticationToken.getPrincipal().getAttribute("login").toString() +"@gmail.com" ;
            }
            return email;

        }else{
            return authentication.getName();
        }
       
    }

    public static String getLinkForEmailVerificatiton(String emailToken) {

        return "http://localhost:8080/auth/verify-email?token=" + emailToken;

    }

}
