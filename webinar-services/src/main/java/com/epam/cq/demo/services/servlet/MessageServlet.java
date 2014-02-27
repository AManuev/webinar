package com.epam.cq.demo.services.servlet;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

@SlingServlet(metatype = false, paths = MessageServlet.MAPPING_PATH)
public class MessageServlet extends SlingAllMethodsServlet {

    public static final java.lang.String MAPPING_PATH = "/services/webinar/message-servlet";

    protected static final String CONTENT_TYPE = "text/plain";
    protected static final String PAGE_ENCODING = "UTF-8";

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException,
            IOException {

        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(PAGE_ENCODING);

        response.getWriter().print("<< Some information from servlet. >>");
    }
}
