<%@ include file="../imports.jsp"%>

<%@ page import="org.openxava.model.meta.MetaProperty" %>
<%@ page import="org.openxava.util.Labels" %>


<%@page import="org.openxava.web.Ids"%><jsp:useBean id="context" class="org.openxava.controller.ModuleContext" scope="session"/>

<%
org.openxava.controller.ModuleManager manager = (org.openxava.controller.ModuleManager) context.get(request, "manager", "org.openxava.controller.ModuleManager");

String propertyKey = request.getParameter("propertyKey");
MetaProperty p = (MetaProperty) request.getAttribute(propertyKey);
String fvalue = (String) request.getAttribute(propertyKey + ".fvalue");
if ("0".equals(fvalue)) fvalue = "";
boolean editable="true".equals(request.getParameter("editable"));
%>
<% if (editable) { %>
<a href="javascript:openxava.editors.html.openWindow('<%=request.getContextPath()%>/xava/editors/fckEditor.jsp?nproperty=<%=propertyKey%>&form=<xava:id name='form'/>')"
title="<%=Labels.get("Collection.edit")%>">
<img border="0" src="<%=request.getContextPath()%>/xava/images/edit-text.gif" 
title="<%=Labels.get("Collection.edit")%>" align="right"></a><br>
<%}%>
<input type="hidden" name="<%=propertyKey%>" value='<%=fvalue%>'>	
<div id="<%=propertyKey%>_html_editor_show_value"><%=fvalue%></div>
