package com.glessit.neurofunky.configuration.servlet;


import com.glessit.neurofunky.configuration.RootConfiguration;
import com.glessit.neurofunky.configuration.jpa.JPAConfiguration;
import com.glessit.neurofunky.configuration.LuceneConfiguration;
import com.glessit.neurofunky.configuration.RestConfiguration;
import com.glessit.neurofunky.configuration.security.SecurityConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * See https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/WebApplicationInitializer.html
 */
public class RestServletConfiguration implements WebApplicationInitializer {

//    @Value(value = "${rest.primary.mapping}")
    private String servletMapping = "/api/*";

    private final static String servletName = "NeurofunkyRestServlet";

    @Override
    public void onStartup(javax.servlet.ServletContext servletContext) throws ServletException {

        // create 'root' spring application context
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
//        rootContext.register(LuceneConfiguration.class);
        rootContext.register(JPAConfiguration.class);
        rootContext.register(RootConfiguration.class);
        rootContext.register(SecurityConfiguration.class);

        servletContext.addListener(new ContextLoaderListener(rootContext));

        // create the dispatcher servlet's Spring application context
        AnnotationConfigWebApplicationContext restContext =
                new AnnotationConfigWebApplicationContext();
        restContext.register(RestConfiguration.class);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(restContext);
        dispatcherServlet.setDetectAllHandlerAdapters(true);

        ServletRegistration.Dynamic registration = servletContext
                .addServlet(servletName, dispatcherServlet);

        registration.setLoadOnStartup(1);
        registration.addMapping(servletMapping);
    }
}
