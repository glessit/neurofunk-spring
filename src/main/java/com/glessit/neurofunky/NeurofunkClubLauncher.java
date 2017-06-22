package com.glessit.neurofunky;

import com.glessit.neurofunky.configuration.servlet.ApplicationConfigurationInitializer;
import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.annotations.ClassInheritanceHandler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.ConcurrentHashSet;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.web.WebApplicationInitializer;

public class NeurofunkClubLauncher {

    public static void main(String[] args) throws Exception {

        Server server = new Server(8081);

        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setResourceBase("src/main/webapp");
        webAppContext.setContextPath("/");
        webAppContext.setConfigurations(new Configuration[] {
                new AnnotationConfiguration() {
                    @Override
                    public void preConfigure(WebAppContext context) {
                        // i don't know, but jetty can't see classes of WebApplicationInitializer, just add it manually
                        ClassInheritanceMap map = new ClassInheritanceMap();
                        map.put(WebApplicationInitializer.class.getName(),
                                new ConcurrentHashSet<String>() {{
                                    add(ApplicationConfigurationInitializer.class.getName());
                                }});
                        context.setAttribute(CLASS_INHERITANCE_MAP, map);
                        _classInheritanceHandler = new ClassInheritanceHandler(map);
                    }
                }
        });
        server.setHandler(webAppContext);
        server.start();
    }
}
