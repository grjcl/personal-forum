package com.forum.configurer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.forum.interceptor.LoginInterceptor;

/**
 * @author 彼得·潘
 */
@Configuration
public class WebAppConfigurer 
	implements WebMvcConfigurer {

	@Override
	public void addInterceptors(
			InterceptorRegistry registry) {
		// 黑名单
		List<String> pathPatterns
			= new ArrayList<>();
		pathPatterns.add("/user/**");
		pathPatterns.add("/article/**");
		pathPatterns.add("/coll/**");
		pathPatterns.add("/praise/**");
		pathPatterns.add("/com/**");
		// 白名单
		List<String> excludePathPatterns
			= new ArrayList<>();
		excludePathPatterns.add("/user/reg.do");
		excludePathPatterns.add("/user/login.do");
		excludePathPatterns.add("/user/logout.do");
		excludePathPatterns.add("/article/showHotArticle.do");
		excludePathPatterns.add("/article/showHotReviewsArticle.do");
		excludePathPatterns.add("/com/showNewComment.do");
		
		excludePathPatterns.add("index.html");
		excludePathPatterns.add("register.html");
		excludePathPatterns.add("login.html");
	
		// 注册
		registry
			.addInterceptor(new LoginInterceptor())
			.addPathPatterns(pathPatterns)
			.excludePathPatterns(excludePathPatterns);
	}

}
