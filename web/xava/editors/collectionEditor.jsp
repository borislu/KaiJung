<%@ include file="../imports.jsp"%>

<jsp:useBean id="context" class="org.openxava.controller.ModuleContext" scope="session"/>
<jsp:useBean id="errors" class="org.openxava.util.Messages" scope="request"/>
<jsp:useBean id="style" class="org.openxava.web.style.Style" scope="request"/>

<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Map" %>
<%@ page import="org.openxava.util.Maps" %>
<%@ page import="org.openxava.util.Is" %>
<%@ page import="org.openxava.util.XavaPreferences" %>
<%@ page import="org.openxava.view.View" %>
<%@ page import="org.openxava.model.meta.MetaProperty" %>
<%@ page import="org.openxava.model.meta.MetaReference" %>
<%@ page import="org.openxava.web.WebEditors" %>

<%
String collectionName = request.getParameter("collectionName");
String viewObject = request.getParameter("viewObject");
String listEditor = request.getParameter("listEditor");  
View view = (View) context.get(request, viewObject);
View collectionView = view.getSubview(collectionName);
if (!Is.emptyString(listEditor)) {
	collectionView.setDefaultListActionsForCollectionsIncluded(false);
}
View subview = view.getSubview(collectionName);
MetaReference ref = view.getMetaModel().getMetaCollection(collectionName).getMetaReference();
String viewName = viewObject + "_" + collectionName;
String propertyPrefixAccumulated = request.getParameter("propertyPrefix");
String idCollection = null;
if (Is.emptyString(propertyPrefixAccumulated)) {
	idCollection = collectionName;
}
else {
	// removing xava.ModelName.
	int idx = propertyPrefixAccumulated.indexOf('.');
	idx = propertyPrefixAccumulated.indexOf('.', idx+1) + 1;
	idCollection = propertyPrefixAccumulated.substring(idx) + collectionName;
}
boolean collectionEditable = subview.isCollectionEditable();
boolean collectionMembersEditables = subview.isCollectionMembersEditables();
boolean hasListActions = subview.hasListActions();
String lineAction = ""; 
if (collectionEditable || collectionMembersEditables) {
	lineAction = subview.getEditCollectionElementAction();
}
else {
	if (!subview.isDetailMemberInCollection()) {
		lineAction = subview.getViewCollectionElementAction();
	}
}
String propertyPrefix = propertyPrefixAccumulated == null?collectionName + ".":propertyPrefixAccumulated + collectionName + "."; 
%>
<table width="100%" class="<%=style.getList()%>" <%=style.getListCellSpacing()%>>
<% if (XavaPreferences.getInstance().isDetailOnBottomInCollections()) { %>
<tr><td>
<% try { %>
	<% if (!Is.emptyString(listEditor)) { %> 		
		<jsp:include page="<%=listEditor%>">
			<jsp:param name="rowAction" value="<%=lineAction%>"/>	
			<jsp:param name="viewObject" value="<%=viewName%>"/>
		</jsp:include>
	<% } else if (collectionView.isCollectionCalculated()) { %>
		<%@include file="../calculatedCollectionList.jsp" %>
	<% } else { %>
		<%@include file="../collectionList.jsp" %>
	<% } %>
<% } catch (Exception ex) { %>
</td></tr>
<tr><td class='<%=style.getErrors()%>'>
<%=ex.getLocalizedMessage()%>
<% } %>
</td></tr>
<% } // of: if (XavaPreferences...  %>
<%
// New
if (view.displayDetailInCollection(collectionName)) {
	context.put(request, viewName, collectionView);
%>
<tr class=<%=style.getCollectionListActions()%>><td colspan="<%=subview.getMetaPropertiesList().size()+1%>" class=<%=style.getCollectionListActions()%>>
<% if (collectionEditable) { %>
<xava:action action="<%=subview.getNewCollectionElementAction()%>" argv='<%="viewObject="+viewName%>'/>
<xava:action action="<%=subview.getRemoveSelectedCollectionElementsAction()%>" argv='<%="viewObject="+viewName%>'/>
<% } %>
<% 
Iterator itListActions = subview.getActionsNamesList().iterator();
while (itListActions.hasNext()) {
%>
&nbsp;<xava:action action="<%=itListActions.next().toString()%>" argv='<%="viewObject="+viewName%>'/>
<%	
} // while list actions
%>


</td></tr>
<%		
}
else {
%>
<td></td>
<%
	String argv = "collectionName=" + collectionName;
	Iterator it = subview.getMetaPropertiesList().iterator();
	String app = request.getParameter("application");
	String module = request.getParameter("module");
	while (it.hasNext()) {
		MetaProperty p = (MetaProperty) it.next(); 
		String propertyKey= propertyPrefix + p.getName();
		String valueKey = propertyKey + ".value";
		request.setAttribute(propertyKey, p);
		request.setAttribute(valueKey, subview.getValue(p.getName()));		
		String script = "";
		if (it.hasNext()) {
			if (subview.throwsPropertyChanged(p)) {			
				script = "onchange='openxava.throwPropertyChanged(\"" + 
						app + "\", \"" + 
						module + "\", \"" +
						propertyKey + "\")'";
			}
		}
		else {
			script = "onblur='openxava.executeAction(\"" + app + "\", \"" + module + "\", \"\", false, \"" + subview.getSaveCollectionElementAction() + "\", \"" + argv + "\")'";
		}
		Object value = request.getAttribute(propertyKey + ".value");
		if (WebEditors.mustToFormat(p, view.getViewName())) {
			String fvalue = WebEditors.format(request, p, value, errors, view.getViewName());
			request.setAttribute(propertyKey + ".fvalue", fvalue);
		}		
	%>
	<td>
		<jsp:include page="<%=WebEditors.getUrl(p, view.getViewName())%>">
			<jsp:param name="propertyKey" value="<%=propertyKey%>"/>
			<jsp:param name="script" value="<%=script%>"/>
			<jsp:param name="editable" value="true"/>
		</jsp:include>
	</td>
	<%
	}
}
%>

</tr>
<% if (!XavaPreferences.getInstance().isDetailOnBottomInCollections()) { %>
<tr><td>
<% try { %>
	<% if (!Is.emptyString(listEditor)) { %> 		
		<jsp:include page="<%=listEditor%>">
			<jsp:param name="rowAction" value="<%=lineAction%>"/>	
			<jsp:param name="viewObject" value="<%=viewName%>"/>
		</jsp:include>
	<% } else if (collectionView.isCollectionCalculated()) { %>
		<%@include file="../calculatedCollectionList.jsp" %>
	<% } else { %>
		<%@include file="../collectionList.jsp" %>
	<% } %>
<% } catch (Exception ex) { %>
</td></tr>
<tr><td class='<%=style.getErrors()%>'>
<%=ex.getLocalizedMessage()%>
<% } %>
</td></tr>
<% } // of: if (!XavaPreferences... %>
</table>