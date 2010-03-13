<%@ include file="imports.jsp"%>

<%@ page import="org.openxava.controller.meta.MetaAction" %>

<jsp:useBean id="context" class="org.openxava.controller.ModuleContext" scope="session"/>
<jsp:useBean id="style" class="org.openxava.web.style.Style" scope="request"/>

<%
org.openxava.controller.ModuleManager manager = (org.openxava.controller.ModuleManager) context.get(request, "manager", "org.openxava.controller.ModuleManager");
manager.setSession(session);
boolean onBottom = false;

if (manager.isButtonBarVisible()) {
%>
 
	<table width="100%" <%=style.getButtonBarSpacing()%> class="<%=style.getButtonBar2()%>" style="<%=style.getButtonBarStyle()%>">
	<tr>
	<td class=<%=style.getButtonBarStart(onBottom)%> style="<%=style.getButtonBarStartStyle()%>" width=1>&nbsp;</td>
	<td style='vertical-align: middle' class="<%=style.getButtonBarMiddle(onBottom)%>" style="<%=style.getButtonBarMiddleStyle()%>">
	
	<%
	java.util.Iterator it = manager.getMetaActions().iterator();
	while (it.hasNext()) {
		MetaAction action = (MetaAction) it.next();
		if (action.isHidden()) continue;
		if (action.hasImage()) { 
		%>
		<xava:image action="<%=action.getQualifiedName()%>"/>
		<%
		} 
	}
	%>
	</td>
	
	<td align="right" style='vertical-align: middle' class="<%=style.getMode(onBottom)%>">
	&nbsp;
	<%
	java.util.Stack previousViews = (java.util.Stack) context.get(request, "xava_previousViews"); 
	if (previousViews.isEmpty()) {
		java.util.Iterator itSections = manager.getMetaActionsMode().iterator();
		boolean firstTime = true;
		while (itSections.hasNext()) {
			MetaAction action = (MetaAction) itSections.next();
			if (action.isHidden()) continue;
			if (firstTime) firstTime=false;
			else {
			%>
			-
			<%
			}
			// 'modeNameAction' only run well if the only modes
			// are list and detail, but at momment that is the case
			String modeNameAction = action.getName().equals("list")?"list":"detail"; 
			if (modeNameAction.equals(manager.getModeName())) {
			%>
			<b><%=action.getLabel(request)%></b>
			<%
			}
			else {	
			%>
			<xava:link action="<%=action.getQualifiedName()%>"/>
			<%
			}
		}
	}
		%>
	</td>
	<td class="<%=style.getButtonBarEnd(onBottom)%>" style="<%=style.getButtonBarEndStyle()%>" width=1>&nbsp;</td>
	</tr>
	</table>
<% } // end isButtonBarVisible %>