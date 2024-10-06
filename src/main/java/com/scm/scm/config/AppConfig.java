package com.scm.scm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class AppConfig {

    @Value("${cloudinary.cloud.name}")
    public String cloudName;

    @Value("${cloudinary.api.key}")
    public String apiKey;

    @Value("${cloudinary.api.secret}")
    public String apiSecret;

    @Bean
    public Cloudinary cloudinary(){

        return new Cloudinary(ObjectUtils.asMap(
            "cloud_name", cloudName,
            "api_key", apiKey,
            "api_secret", apiSecret
        ));
    }
}
