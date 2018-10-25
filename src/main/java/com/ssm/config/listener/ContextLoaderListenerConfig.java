package com.ssm.config.listener;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;

@Configuration
@ComponentScan(basePackages= "com.ssm.service")
@EnableTransactionManagement
@MapperScan(basePackages="com.ssm.mapper")
@PropertySource("classpath:config/database.properties")
public class ContextLoaderListenerConfig {

	//数据源
	@Bean
	public DataSource getDataSource(@Value("${jdbc.driverClassName}")String driverClassName,
									@Value("${jdbc.url}")String url,
									@Value("${jdbc.username}")String username,
									@Value("${jdbc.password}")String password) {
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setUrl(url);
		druidDataSource.setDriverClassName(driverClassName);
		druidDataSource.setUsername(username);
		druidDataSource.setPassword(password);
		return druidDataSource;
	}
	
	/**配置PageInterceptor插件*/
	@Bean
	public PageInterceptor getPageInterceptor(){
		PageInterceptor pageIntercptor=new PageInterceptor();
		Properties properties=new Properties();
		properties.setProperty("helperDialect", "mysql");
		properties.setProperty("reasonable", "true");
		properties.setProperty("pageSizeZero", "true");
		properties.setProperty("supportMethodsArguments", "true");
		properties.setProperty("returnPageInfo", "check");
		properties.setProperty("params", "count=countSql");
		pageIntercptor.setProperties(properties);
		return pageIntercptor;
	}
	
	//sqlSessionFacrotyBean
	@Bean
	public SqlSessionFactoryBean getSqlSessionFactoryBean(DataSource dataSource,PageInterceptor pageInterceptor) throws IOException {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setPlugins(new Interceptor[] {pageInterceptor});
		return sqlSessionFactoryBean;
	}
	
	//事务管理
	@Bean
	public DataSourceTransactionManager getdatasouDataSourceTransactionManager(DataSource dataSource) {
		DataSourceTransactionManager manager = new DataSourceTransactionManager();
		manager.setDataSource(dataSource);
		return manager;
	}
}
