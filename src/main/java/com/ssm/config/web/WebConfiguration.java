package com.ssm.config.web;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.ssm.config.listener.ContextLoaderListenerConfig;
import com.ssm.config.servlet.DispatchServletConfig;

public class WebConfiguration extends AbstractAnnotationConfigDispatcherServletInitializer {

	/**
	 * 返回带有@Configuration注解的类会用来定义ContextLoaderListener
	 * 创建ApplicationContext中的bean(非web组件)
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		
		return new Class<?>[] {ContextLoaderListenerConfig.class};
	}

	/**
	 * 返回带有@Configuration注解的类用来定义DispatcherServlet上下文中的bean(web组件)
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		
		return new Class<?>[] {DispatchServletConfig.class};
	}

	/**
	 * 请求路径配置，配置为"/"表示它将处理所有请求
	 */
	@Override
	protected String[] getServletMappings() {
		
		return new String[] {"/"};
	}

}
