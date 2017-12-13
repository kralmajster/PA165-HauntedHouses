package fi.muni.pa165.hauntedhouses.config;

import fi.muni.pa165.hauntedhouses.ApiContract;

import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 *
 * @author Mario Majernik, 422165
 *
 */

public class Initializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        servletContext.addListener(RequestContextListener.class);
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{ApiContract.REST + "/*"};
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SecurityConfiguration.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }
    
}
