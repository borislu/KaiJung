<%@ page import="org.openxava.model.meta.MetaProperty" %>

<jsp:useBean id="style" class="org.openxava.web.style.Style" scope="request"/>

<%
String propertyKey = request.getParameter("propertyKey");
MetaProperty p = (MetaProperty) request.getAttribute(propertyKey);
String fvalue = (String) request.getAttribute(propertyKey + ".fvalue");
if ("0".equals(fvalue)) fvalue = "";
String align = p.isNumber()?"right":"left";
boolean editable="true".equals(request.getParameter("editable"));
String disabled=editable?"":"disabled";
String script = request.getParameter("script");
int rows = p.getSize() / 80 + 1;
script = script + " onkeyup='return openxava.limitLength(event, " + p.getSize() + ")' ";
%>


<textarea name="<%=propertyKey%>" class=<%=style.getEditor()%>
	rows="<%=rows%>" cols="80"
	title="<%=p.getDescription(request)%>"	
	<%=disabled%>
	<%=script%>><%=fvalue%></textarea>	
<% if (!editable) { %>
	<input type="hidden" name="<%=propertyKey%>" value="<%=fvalue%>">
<% } %>			
