<%@ include file="imports.jsp"%>

<jsp:useBean id="messages" class="org.openxava.util.Messages" scope="request"/>
<jsp:useBean id="style" class="org.openxava.web.style.Style" scope="request"/>

<% 
if (messages.contains()) {
%>
<div class='<%=style.getMessagesWrapper()%>'>
<table id="<xava:id name='messages_table'/>">
<%
	java.util.Iterator it = messages.getStrings(request).iterator();	
	while (it.hasNext()) {		
%>
<tr><td class=<%=style.getMessages()%>>
<%=style.getMessageStartDecoration()%>
<%=it.next()%>
<%=style.getMessageEndDecoration()%>
</td></tr>
<% } %>
</table>
</div>
<% } %>
