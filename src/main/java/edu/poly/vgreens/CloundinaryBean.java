//package edu.poly.vgreens;
//
//import java.util.ArrayList;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.web.context.support.ServletContextResource;
//import org.springframework.web.multipart.commons.CommonsMultipartResolver;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
//import org.springframework.web.servlet.resource.PathResourceResolver;
//import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
//import org.springframework.web.servlet.resource.ResourceResolver;
//import org.springframework.web.servlet.resource.ResourceUrlProvider;
//import org.springframework.web.servlet.resource.ResourceUrlProviderExposingInterceptor;
//
//import com.cloudinary.Cloudinary;
//import com.cloudinary.utils.ObjectUtils;
//
//@Configuration
//@EnableWebMvc
//@EnableTransactionManagement
//@ComponentScan(basePackages = {
//		"edu.poly.vgreens",
//		"edu.poly.vgreens.config",
//		"edu.poly.vgreens.controller",
//		"edu.poly.vgreens.controller.admin",
//		"edu.poly.vgreens.controller.site",
//		"edu.poly.vgreens.entity",
//		"edu.poly.vgreens.interceptor",
//		"edu.poly.vgreens.log",
//		"edu.poly.vgreens.model",
//		"edu.poly.vgreens.model2",
//		"edu.poly.vgreens.repository",
//		"edu.poly.vgreens.restcontroller.admin",
//		"edu.poly.vgreens.restcontroller.site",
//		"edu.poly.vgreens.service",
//		"edu.poly.vgreens.impl"
//		
//})
//public class CloundinaryBean implements WebMvcConfigurer{
//	
//	@Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		 registry.addResourceHandler(
//	                "/static/adminScript/**",
//	                "/images/**",
//	                "/css/**",
//	                "/js/**",
//	                "/siteScript/**")
//	                .addResourceLocations(
//	                        "classpath:/resources/static/adminScript/",
//	                        "classpath:/static/images/",
//	                        "classpath:/static/css/",
//	                        "classpath:/static/js/",
//	                        "classpath:/static/siteScript/");
//		 registry.addResourceHandler("/upload/**").addResourceLocations("classpath:/webapp/upload/");
//		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
//		registry.addResourceHandler("/js/**").addResourceLocations( "classpath:/static/js/");
//		registry.addResourceHandler("/images/**").addResourceLocations( "classpath:/static/images/");
//		registry.addResourceHandler("/adminScript/**").addResourceLocations( "classpath:/static/adminScript/");
//		registry.addResourceHandler("/siteScript/**").addResourceLocations( "classpath:/static/siteScript/");
//    }
//	
//	
//	
//	@Bean
//	public CommonsMultipartResolver multipartResolver() {
//		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
//		resolver.setDefaultEncoding("UTF-8");
//		return resolver;
//	}
//
//	@Bean
//	public Cloudinary coudinary() {
//		Cloudinary c = new Cloudinary(ObjectUtils.asMap(
//				"cloud_name", "vgreens-ttg",
//				"api_key", "654955639487878",
//				"api_secret","yLbP2ygFZ-vLznwHoF-CYL7uJTc",
//				"secure",true
//		));
//		return c; 
//	}
//	
//
//}
