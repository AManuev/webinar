<%--

    This is a simple page component using JSP that extends the foundation page component.

--%>

<%@ include file="/apps/webinar/components/global.jspx" %>

<%--Include a Global library from etc folder --%>
<cq:includeClientLib categories="apps.webinar.modernizr"/>
<%-- include a components category--%>
<%-- Should be done only on the page component. --%>
<cq:includeClientLib categories="apps.webinar.component" />

<body>


    <p>This is an example page component.</p>
    <cq:include path="par" resourceType="foundation/components/parsys"/>
</body>
