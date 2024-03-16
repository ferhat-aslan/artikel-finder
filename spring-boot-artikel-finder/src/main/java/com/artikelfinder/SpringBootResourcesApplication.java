package com.artikelfinder;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.core.io.ClassPathResource;

public class SpringBootResourcesApplication {
    public static void main(String[] args) throws Exception {
        ClassPathResource resource = new ClassPathResource("/hello", SpringBootResourcesApplication.class);
        try (InputStream inputStream = resource.getInputStream()) {
            String string = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            System.out.println(string);
        }
    }
}
