package edu.poly.vgreens.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import edu.poly.vgreens.interceptor.GlobalInterceptor;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = { "edu.poly.vgreens", "edu.poly.vgreens.config", "edu.poly.vgreens.controller",
		"edu.poly.vgreens.controller.admin", "edu.poly.vgreens.controller.site", "edu.poly.vgreens.entity",
		"edu.poly.vgreens.interceptor", "edu.poly.vgreens.log", "edu.poly.vgreens.model", "edu.poly.vgreens.model2",
		"edu.poly.vgreens.repository", "edu.poly.vgreens.restcontroller.admin", "edu.poly.vgreens.restcontroller.site",
		"edu.poly.vgreens.service", "edu.poly.vgreens.service.impl"

})
public class InterceptorConfig implements WebMvcConfigurer {
	@Autowired
	GlobalInterceptor globalInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(globalInterceptor).addPathPatterns("/**").excludePathPatterns("/rest/**", "/admin/**",
				"/vgreens/account/address/**");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
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
//		registry.addResourceHandler("/**").addResourceLocations("/").setCachePeriod(0);
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
		registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/");
		registry.addResourceHandler("/adminScript/**").addResourceLocations("classpath:/static/adminScript/");
		registry.addResourceHandler("/siteScript/**").addResourceLocations("classpath:/static/siteScript/");
		registry.addResourceHandler("/imageTemp/**").addResourceLocations("classpath:/webapp/upload/imageTemp/");

	}

	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("UTF-8");
		return resolver;
	}

	@Bean
	public Cloudinary coudinary() {
		Cloudinary c = new Cloudinary(ObjectUtils.asMap(
				"cloud_name", "lancloudshop",
				"api_key", "243852965266734",
				"api_secret","4AbJO_tm-uDQHkjtWuEI7ugqPsM",
				"secure",true
		));
		return c; 
	}

}
