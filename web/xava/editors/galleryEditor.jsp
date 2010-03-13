<%@ include file="../imports.jsp"%>

<%@ page import="org.openxava.model.meta.MetaProperty" %>

<%
String propertyKey = request.getParameter("propertyKey");
MetaProperty p = (MetaProperty) request.getAttribute(propertyKey);
String fvalue = (String) request.getAttribute(propertyKey + ".fvalue");
%>


<input type="hidden" name="<%=propertyKey%>" value="<%=fvalue%>">

<xava:image action='Gallery.edit' argv='<%="galleryProperty=" + p.getName()%>'/>


