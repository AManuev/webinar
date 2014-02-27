package com.epam.cq.demo.services.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.felix.scr.annotations.sling.SlingFilter;
import org.apache.felix.scr.annotations.sling.SlingFilterScope;

//@Component(label = "CQBlueprints example filter", metatype = true)
//@Service(javax.servlet.Filter.class)
//@Property(name="sling.filter.scope", value = "REQUEST")
//@Property(name="service.ranking", value = "-5000")
@SlingFilter(scope = SlingFilterScope.REQUEST, order = -5000, label = "CQ Webinar filter", metatype = true)
public class SimpleFilter implements Filter {

    private String testFilter;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        testFilter = "Filter start work";
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {

        // FYI: if you aptly filter you may lost a possibility to install package.
        // In this case disable component filter thought the OSGi console.

        // if (request.getParameter("filter").equals("1")) {
        // response.getWriter().println("Filter first!");
        // }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
