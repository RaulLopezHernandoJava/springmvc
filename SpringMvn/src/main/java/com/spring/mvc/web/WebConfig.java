package com.spring.mvc.web;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

// Se necesita implementar esta interfaz (wEBmVConfigurer) porque posee
// un metodo Interceptor que nos permitira implementar la internacionalizacion
// en la aplicacion y asi poder manejar varios idiomas

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(new Locale("es"));
		return slr;

	}

	// Configuramos Interceptor para poder cambiar el lenguaje de manera dinamica

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registro) {
		registro.addInterceptor(localeChangeInterceptor());
		
	}
	
	// Mappping de la url del path por default. Las vistas que no pasan por ningun controlador se registran aqui
	@Override
	public void addViewControllers(ViewControllerRegistry registro) {
		registro.addViewController("/").setViewName("index");
		registro.addViewController("/login");
		registro.addViewController("/errores/403").setViewName("/errores/403");
	}
}
