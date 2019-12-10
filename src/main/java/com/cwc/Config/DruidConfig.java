package com.cwc.Config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;

@Configuration
public class DruidConfig {
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid(){
        return  new DruidDataSource();

    }

/*
配置Druid监控
配置一个管理后台的Servlet
* */
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("loginUsername","admin");
        hashMap.put("loginPassword","admin");
        hashMap.put("allow","");
        bean.setInitParameters(hashMap);
        return bean;

    }
    /*
    * 配置一个监控web的filter
    * */
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean=new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("exclusions","*.js,*.css,/druid/*");
        bean.setInitParameters(hashMap);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }
}
