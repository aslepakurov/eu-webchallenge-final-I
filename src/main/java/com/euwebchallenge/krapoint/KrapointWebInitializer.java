package com.euwebchallenge.krapoint;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import com.euwebchallenge.krapoint.AppConfig;

/**
 * KrapointWebInitializer Class
 *
 * @version 11/7/15
 */
public class KrapointWebInitializer implements WebApplicationInitializer {

    public void onStartup(ServletContext container) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(AppConfig.class);
        ctx.setServletContext(container);
        ServletRegistration.Dynamic servlet = container.addServlet(
                "dispatcher", new DispatcherServlet(ctx));
        FilterRegistration fr = container.addFilter("CorsFilter", CORSFilter.class);
        fr.addMappingForUrlPatterns(null, true, "/*");
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
    }
}
