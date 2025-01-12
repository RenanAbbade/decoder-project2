package com.ead.authuser.config;

import net.kaczmarzyk.spring.data.jpa.web.SpecificationArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class ResolverConfig implements WebMvcConfigurer {
    //Classe de configuração de aspectos gerais da aplicação

    //Adicionando configuração padrão para argumentos Pageable

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){
        argumentResolvers.add(new SpecificationArgumentResolver()); //a lib kaczmarzyk faz a conversão de parametros enviados pelo client para tipos java

        var pageableHandlerMethodArgumentResolver = new PageableHandlerMethodArgumentResolver();
        int PAGE_SIZE = 10;
        int PAGE_NUMBER = 0;
        pageableHandlerMethodArgumentResolver.setFallbackPageable(PageRequest.of(PAGE_NUMBER, PAGE_SIZE));
        argumentResolvers.add(pageableHandlerMethodArgumentResolver);

    }

    @Override
    public void addCorsMappings(CorsRegistry registry){
        //registry.addMapping("/users/**").allowedOrigins("");
        registry.addMapping("/**").maxAge(3600);
    }
}
