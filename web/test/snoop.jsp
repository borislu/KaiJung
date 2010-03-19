<%@page import="java.io.IOException"%>
<%@page import="java.util.Enumeration"%>
<center><h1>Snoop JSP</h1></center>
<h2>Init Parameters</h2>
<%
		Enumeration e = getInitParameterNames();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			String value = getInitParameter(key);
			%><b>--><%=key%></b> = <%=value%><br><%
		}
%>
<br><br>
<h2>Context init parameters</h2>
<%
		ServletContext context = getServletContext();
		Enumeration myEnum = context.getInitParameterNames();
		while (myEnum.hasMoreElements()) {
			String key = (String) myEnum.nextElement();
			Object value = context.getInitParameter(key);
			%><b>--><%=key%></b> = <%=value%><br><%
		}
%>

<br><br>
<h2>Context attributes</h2>
<%
		myEnum = context.getAttributeNames();
		while (myEnum.hasMoreElements()) {
			String key = (String) myEnum.nextElement();
			Object value = context.getAttribute(key);
			%><b>--><%=key%></b> = <%=value%><br><%
		}
%>
<br><br>
<h2>Request attributes</h2>
<%
		e = request.getAttributeNames();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			Object value = request.getAttribute(key);
			%><b>--><%=key%></b> = <%=value%><br><%
		}
%>

<br><br>
<h2>General attributes</h2>
<b>-->Servlet Name<b> = <%=getServletName()%><br>
<b>-->Protocol</b> = <%=request.getProtocol()%><br>
<b>-->Scheme</b> = <%=request.getScheme()%><br>
<b>-->Server Name</b> = <%=request.getServerName()%><br>
<b>-->Server Port</b> = <%=request.getServerPort()%><br>
<b>-->Server Info</b> = <%=context.getServerInfo()%><br>
<b>-->Remote Addr</b> = <%=request.getRemoteAddr()%><br>
<b>-->Remote Host</b> = <%=request.getRemoteHost()%><br>
<b>-->Character Encoding</b> = <%=request.getCharacterEncoding()%><br>
<b>-->Content Length</b> = <%=request.getContentLength()%><br>
<b>-->Content Type</b> = <%=request.getContentType()%><br>
<b>-->Locale</b> = <%=request.getLocale()%><br>
<b>-->Default Response Buffer:</b> = <%=response.getBufferSize()%><br>
<b>-->Request Is Secure</b> = <%=request.isSecure()%><br>
<b>-->Auth Type</b> = <%=request.getAuthType()%><br>
<b>-->HTTP Method</b> = <%=request.getMethod()%><br>
<b>-->Remote User</b> = <%=request.getRemoteUser()%><br>
<b>-->Request URI</b> = <%=request.getRequestURI()%><br>
<b>-->Context Path</b> = <%=request.getContextPath()%><br>
<b>-->Servlet Path</b> = <%=request.getServletPath()%><br>
<b>-->Path Info</b> = <%=request.getPathInfo()%><br>
<b>-->Path Trans</b> = <%=request.getPathTranslated()%><br>
<b>-->Query String</b> = <%=request.getQueryString()%><br>

<br><br>
<h2>Parameter names in this request:</h2>
<%		e = request.getParameterNames();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			String[] values = request.getParameterValues(key);
			%><b>--><%=key%> = <%
			for (int i = 0; i < values.length; i++) {
				%><%=values[i]%>&nbsp;<%
			}
%><br><%
		}
%>
<br><br>
<h2>Headers in this request:</h2>
<%
		e = request.getHeaderNames();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			String value = request.getHeader(key);
			%><b>--><%=key%></b> = <%=value%><br><%
		}
%>
<br><br>
<h2>Cookies in this request:</h2>
<%
		Cookie[] cookies = request.getCookies();
                if ( cookies != null ) {
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			%><b>--><%=cookie.getName()%></b> = <%=cookie.getValue()%><br>
<%
		}
                }
%>
<br><br>
<h2>Session Information:</h2>
<%
	HttpSession ssession = request.getSession();
%>
<b>-->Requested Session Id</b> = <%=request.getRequestedSessionId()%><br>
<b>-->Current Session Id</b> = <%=session.getId()%><br>
<b>-->Session Created Time</b> = <%=session.getCreationTime()%><br>
<b>-->Session Last Accessed Time</b> = <%=session.getLastAccessedTime()%><br>
<b>-->Session Max Inactive Interval Seconds</b> = <%=session.getMaxInactiveInterval()%><br>
<br><br>
<h2>Session values:</h2>
<%
		Enumeration names = ssession.getAttributeNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
%>
	<b>--><%=name%></b> = <%=session.getAttribute(name)%><br>
<%
		}
%> 
