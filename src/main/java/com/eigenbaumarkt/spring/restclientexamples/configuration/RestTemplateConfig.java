package com.eigenbaumarkt.spring.restclientexamples.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplateBuilder restTemplateBuilder() {
        // see: https://stackoverflow.com/a/60725247/7773582
        // Need to provide a rest template builder because
        // @RestTemplateAutoConfiguration does not work with webflux
        return new RestTemplateBuilder();
    }

     @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {

         return builder.build();

     }

}
