<%@ include file="imports.jsp"%>

<jsp:useBean id="errors" class="org.openxava.util.Messages" scope="request"/>
<jsp:useBean id="messages" class="org.openxava.util.Messages" scope="request"/>
<jsp:useBean id="style" class="org.openxava.web.style.Style" scope="request"/>
<jsp:useBean id="context" class="org.openxava.controller.ModuleContext" scope="session"/>

<%
org.openxava.controller.ModuleManager manager = (org.openxava.controller.ModuleManager) context.get(request, "manager");
org.openxava.view.View view = (org.openxava.view.View) context.get(request, "xava_view");
boolean messagesOnTop = !"false".equalsIgnoreCase(request.getParameter("messagesOnTop"));
boolean buttonBar = !"false".equalsIgnoreCase(request.getParameter("buttonBar")); 
String buttonsAlign = buttonBar?"":"text-align: right;";
%>
<div class="<%=style.getModule()%>">
<form id="<xava:id name='form'/>" name="<xava:id name='form'/>"
	method='POST' <%=manager.getEnctype()%> 
	<%=manager.getFormAction(request)%> style="display: inline;"
	onsubmit="return false">
	
<%-- Here, and not at bottom of form, because if there are some erroneous
markup inside the view, then maybe these hidden fields are not found by javascript. 
Concretely, if you put this hidden fields on bottom then InvoiceAmounts (from OpenXavaTest) 
with Firefox 3 and Liferay 5.1.1, 5.1.2 and 5.2.2 produces a JavaScript error.
--%>	
<INPUT type="hidden" name="<xava:id name='xava_action'/>" value=""/>
<INPUT type="hidden" name="<xava:id name='xava_action_argv'/>" value=""/>
<INPUT type="hidden" name="<xava:id name='xava_action_range'/>" value=""/>
<INPUT type="hidden" name="<xava:id name='xava_action_already_processed'/>" value=""/>
<INPUT type="hidden" name="<xava:id name='xava_action_application'/>" value="<%=request.getParameter("application")%>"/>
<INPUT type="hidden" name="<xava:id name='xava_action_module'/>" value="<%=request.getParameter("module")%>"/>
<INPUT type="hidden" name="<xava:id name='xava_changed_property'/>"/>
<INPUT type="hidden" id="<xava:id name='xava_current_focus'/>" 
	name="<xava:id name='xava_current_focus'/>"/>
<INPUT type="hidden" id="<xava:id name='xava_previous_focus'/>" 
	name="<xava:id name='xava_previous_focus'/>"/>
<INPUT type="hidden" name="<xava:id name='xava_focus_forward'/>"/> 
<INPUT type="hidden" id="<xava:id name='xava_focus_property_id'/>" 
	name="<xava:id name='xava_focus_property_id'/>"/>

<div <%=style.getModuleSpacing()%> >

	<% if (buttonBar) { %>
    <div id='<xava:id name="button_bar"/>' class='<%=style.getButtonBar()%>'>		
		<jsp:include page="buttonBar.jsp"/>
	</div>
	<% } %>
    
    <% if (messagesOnTop) { %>    
    <div id='<xava:id name="errors"/>' style="display: inline;">
    	<jsp:include page="errors.jsp"/>
	</div>
    
	<div id='<xava:id name="messages"/>' style="display: inline;">
		<jsp:include page="messages.jsp"/>
	</div>            
    <% } %>
          		 
	<div id='<xava:id name="view"/>' <%=manager.isListMode()?"":("class='" + style.getDetail() + "'")%> style='padding-top: 2px;'>
		<jsp:include page='<%=manager.getViewURL()%>'/>		
	</div>    	
    <div style="clear: both; padding-top: 2px;"></div>    
	<div id='<xava:id name="bottom_buttons"/>' style="<%=buttonsAlign %> <%=style.getBottomButtonsStyle()%>">	
		<jsp:include page="bottomButtons.jsp"/>
	</div>
    
    <% if (!messagesOnTop) { %>
	<div id='<xava:id name="errors"/>'>
		<jsp:include page="errors.jsp"/>
	</div>
        
	<div id='<xava:id name="messages"/>'>
		<jsp:include page="messages.jsp"/>
	</div>               
    <% } %>
</div>
 
</form>
</div>