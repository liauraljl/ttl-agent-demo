package com.ljl.demo.ttlagentdemo.config;

import com.ljl.demo.ttlagentdemo.filter.ContextCreateFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfiguration {

    @Bean
    public FilterRegistrationBean traceWebFilterRegistrationBean() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setOrder(Integer.MIN_VALUE);
        ContextCreateFilter filter = new ContextCreateFilter();
        bean.setFilter(filter);
        bean.addUrlPatterns("/*");
        return bean;
    }
}
