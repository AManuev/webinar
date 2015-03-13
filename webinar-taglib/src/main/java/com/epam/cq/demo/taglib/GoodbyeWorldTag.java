package com.epam.cq.demo.taglib;

import com.cqblueprints.taglib.CqSimpleTagSupport;
import com.epam.cq.demo.services.GoodbyeWorldService;
import com.epam.cq.demo.services.GoodbyeWorldServiceImpl;
import com.squeakysand.jsp.tagext.annotations.JspTag;
import com.squeakysand.jsp.tagext.annotations.JspTagAttribute;

import java.io.IOException;

import javax.servlet.jsp.JspException;

/**
 * Example JSP Custom Tag demonstrating three important concepts:
 *
 * 1. use of the SqueakySand annotations for auto-generating the Tag Library Descriptor (.tld) file
 *
 * 2. extending the CqSimpleTagSupport class from the CQ Blueprints library that provides many
 *    useful methods to make writing JSP Custom Tags for the CQ platform easier for developers
 *
 * 3. accessing an OSGI service from within a JSP Custom Tag using one of the methods inherited
 *    from the CqSimpleTagSupport class
 */
@JspTag
public class GoodbyeWorldTag extends CqSimpleTagSupport {

    private String name;

    @Override
    public void doTag() throws JspException, IOException {
        GoodbyeWorldService service = getService(GoodbyeWorldService.class);
        String message = service.getMessage(name);
        getJspWriter().write(message);
    }

    public String getName() {
        return name;
    }

    @JspTagAttribute(required = true, rtexprvalue = true)
    public void setName(String name) {
        this.name = name;
    }

}
