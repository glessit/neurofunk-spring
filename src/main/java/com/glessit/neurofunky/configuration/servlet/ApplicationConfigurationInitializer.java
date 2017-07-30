package com.glessit.neurofunky.configuration.servlet;


import com.glessit.neurofunky.configuration.RestConfiguration;
import com.glessit.neurofunky.configuration.RootConfiguration;
import com.glessit.neurofunky.configuration.jpa.JPAConfiguration;
import com.glessit.neurofunky.configuration.security.SecurityConfiguration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.EnumSet;

import static org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer.DEFAULT_FILTER_NAME;

/**
 * See https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/WebApplicationInitializer.html
 */
public class ApplicationConfigurationInitializer implements WebApplicationInitializer {

    public static final String DISPATCHER_API_PREFIX = "/api/*";

    private final static String servletName = "NeurofunkyRestServlet";

    @Override
    public void onStartup(javax.servlet.ServletContext servletContext) throws ServletException {

        // create 'root' spring application context
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        // rootContext.register(LuceneConfiguration.class);
        rootContext.register(JPAConfiguration.class);
        rootContext.register(RootConfiguration.class);
        rootContext.register(SecurityConfiguration.class);
//        rootContext.register(SimpleSecurityConfiguration.class);
//        rootContext.register(WebSecurityConfiguration.class);
//        rootContext.register(GlobalMethodSecurity.class);
//        rootContext.register(SecurityConfiguration.class);

        servletContext.addListener(new ContextLoaderListener(rootContext));

        // create SpringSecurityFilterChain
        insertSpringSecurityFilterChain(servletContext);

        // create the dispatcher servlet's Spring application context
        AnnotationConfigWebApplicationContext restContext =
                new AnnotationConfigWebApplicationContext();

        restContext.register(RestConfiguration.class);
        //restContext.register(SimpleSecurityConfiguration.class);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(restContext);
        dispatcherServlet.setDetectAllHandlerAdapters(true);

        ServletRegistration.Dynamic registration = servletContext
                .addServlet(servletName, dispatcherServlet);

        registration.setLoadOnStartup(1);
        registration.addMapping(DISPATCHER_API_PREFIX);
    }

    private final void insertSpringSecurityFilterChain(ServletContext servletContext) {
        String filterName = DEFAULT_FILTER_NAME;
        DelegatingFilterProxy springSecurityFilterChain = new DelegatingFilterProxy(filterName);
        String contextAttribute = getWebApplicationContextAttribute();
        if (contextAttribute != null) {
            springSecurityFilterChain.setContextAttribute(contextAttribute);
        }
        registerFilter(servletContext, true, filterName, springSecurityFilterChain);
    }

    private String getWebApplicationContextAttribute() {
        String dispatcherServletName = null;
        if (dispatcherServletName == null) {
            return null;
        }
        return "org.springframework.web.servlet.FrameworkServlet.CONTEXT." + dispatcherServletName;
    }

    private final void registerFilter(
            ServletContext servletContext,
            boolean insertBeforeOtherFilters,
            String filterName,
            Filter filter) {

        FilterRegistration.Dynamic registration = servletContext.addFilter(filterName, filter);
        if (registration == null) {
            throw new IllegalStateException(
                    "Duplicate Filter registration for '" + filterName
                            + "'. Check to ensure the Filter is only configured once.");
        }
        registration.setAsyncSupported(true);
        registration.addMappingForUrlPatterns(
                EnumSet.of(DispatcherType.REQUEST, DispatcherType.ERROR, DispatcherType.ASYNC),
                !insertBeforeOtherFilters,
                "/*");
    }
}
