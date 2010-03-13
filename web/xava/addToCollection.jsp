<%
String rowAction = request.getParameter("rowAction");
%>

<table><tr><td>
<jsp:include page="list.jsp">
	<jsp:param name="rowAction" value="<%=rowAction%>"/>
</jsp:include>
</span>
</td></tr></table>
