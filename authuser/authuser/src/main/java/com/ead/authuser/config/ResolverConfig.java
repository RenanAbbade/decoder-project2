package com.ead.authuser.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class ResolverConfig implements WebMvcConfigurer {
    //Classe de configuração de aspectos gerais da aplicação

    //Adicionando configuração padrão para argumentos Pageable

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){
        var pageableHandlerMethodArgumentResolver = new PageableHandlerMethodArgumentResolver();
        int PAGE_SIZE = 10;
        int PAGE_NUMBER = 0;
        pageableHandlerMethodArgumentResolver.setFallbackPageable(PageRequest.of(PAGE_NUMBER, PAGE_SIZE));
        argumentResolvers.add(pageableHandlerMethodArgumentResolver);

    }


}
