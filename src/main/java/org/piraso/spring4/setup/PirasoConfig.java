package org.piraso.spring4.setup;

import org.piraso.server.spring.web.PirasoServlet;
import org.piraso.web.base.PirasoFilter;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import java.util.Collections;

@Configuration
@ImportResource("classpath*:spring/piraso.xml")
public class PirasoConfig {

    @Bean
    public FilterRegistrationBean pirasoFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setUrlPatterns(Collections.singleton("/*"));
        bean.setFilter(new PirasoFilter());
        bean.setOrder(-1);

        return bean;
    }

    @Bean
    public PirasoServlet pirasoServlet() {
        return new PirasoServlet();
    }

    @Bean
    public ServletRegistrationBean pirasoRequestHandlerServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean();

        HttpRequestHandlerServlet dispatcherServlet = new HttpRequestHandlerServlet();
        bean.setName("pirasoServlet");
        bean.setServlet(dispatcherServlet);
        bean.setUrlMappings(Collections.singleton("/piraso/logging"));

        return bean;
    }
}
