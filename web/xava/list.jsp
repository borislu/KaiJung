<%@ include file="imports.jsp"%>

<%@ page import="org.openxava.tab.impl.IXTableModel" %>
<%@ page import="org.openxava.util.Strings" %>
<%@ page import="org.openxava.util.XavaPreferences" %>
<%@ page import="org.openxava.model.meta.MetaProperty" %>
<%@ page import="org.openxava.web.WebEditors" %>
<%@ page import="org.openxava.util.Is" %>
<%@ page import="org.openxava.web.Ids" %>
<%@ page import="org.openxava.controller.meta.MetaAction"%>
<%@ page import="org.openxava.controller.meta.MetaControllers"%>
<%@page import="org.openxava.web.Actions"%>

<jsp:useBean id="errors" class="org.openxava.util.Messages" scope="request"/>
<jsp:useBean id="context" class="org.openxava.controller.ModuleContext" scope="session"/>
<jsp:useBean id="style" class="org.openxava.web.style.Style" scope="request"/>

<%
	org.openxava.controller.ModuleManager manager = (org.openxava.controller.ModuleManager) context.get(request, "manager", "org.openxava.controller.ModuleManager");
String collection = request.getParameter("collection"); 
String id = "list";
String collectionArgv = "";
String prefix = "";
String tabObject = request.getParameter("tabObject");
tabObject = (tabObject == null || tabObject.equals(""))?"xava_tab":tabObject;
if (collection != null && !collection.equals("")) {
	id = collection;
	collectionArgv=",collection="+collection;
	prefix = tabObject + "_";
}
org.openxava.tab.Tab tab = (org.openxava.tab.Tab) context.get(request, tabObject);
tab.setRequest(request); 
String action=request.getParameter("rowAction");
action=action==null?manager.getEnvironment().getValue("XAVA_LIST_ACTION"):action;
String viewObject = request.getParameter("viewObject");
String actionArgv = viewObject != null && !viewObject.equals("")?",viewObject=" + viewObject:"";
viewObject = (viewObject == null || viewObject.equals(""))?"xava_view":viewObject; 
org.openxava.view.View view = (org.openxava.view.View) context.get(request, viewObject); 
String sfilter = request.getParameter("filter");
boolean filter = !"false".equals(sfilter);
String displayFilter=""; 
String imageFilter="hide-filter";
String filterMessage="hide_filters";
if (!tab.isFilterVisible()) {
	displayFilter="none"; 
	imageFilter ="show-filter"; 
	filterMessage="show_filters";
}
String lastRow = request.getParameter("lastRow");
boolean singleSelection="true".equalsIgnoreCase(request.getParameter("singleSelection"));
String onSelectCollectionElementAction = view.getOnSelectCollectionElementAction();
MetaAction onSelectCollectionElementMetaAction = Is.empty(onSelectCollectionElementAction) ? null : MetaControllers.getMetaAction(onSelectCollectionElementAction);
String cssSelectedRow = style.getSelectedRow();
String selectedRowStyle = style.getSelectedRowStyle();
String rowStyle = "border-bottom: 1px solid;";
%>

<input type="hidden" name="xava_list<%=tab.getTabName()%>_filter_visible"/>

<%
	if (tab.isTitleVisible()) {
%>
<table width="100%" id="list-title" class=<%=style.getListTitleWrapper()%>>
<tr><td class=<%=style.getListTitle()%>>
<%=tab.getTitle()%>
</td></tr>
</table>
<%
	}
%>

<table id="<xava:id name='<%=id%>'/>" class="<%=style.getList()%>" width="100%" <%=style.getListCellSpacing()%> style="<%=style.getListStyle()%>">
<tr class="<%=style.getListHeader()%>">
<th class="<%=style.getListHeaderCell()%>" style="text-align: center" width="60">
	<%
		String imageFilterPrefix = request.getContextPath() + "/xava/images/";
	%>
	<input name="xava_image_filter_prefix" type="hidden" value="<%=imageFilterPrefix%>"/>     
	<a id="<xava:id name='<%="filter_link_" + id%>'/>" href="javascript:openxava.manageFilterRow('<%=request.getParameter("application")%>', '<%=request.getParameter("module")%>', '<%=id%>', '<%=tabObject%>')" title="<xava:message key='<%=filterMessage%>'/>"><img id="<xava:id name='<%="filter_image_" + id%>'/>" align='middle' 
		src='<%=imageFilterPrefix%><%=imageFilter%>.gif' border='0'/></a>
	<xava:image action="List.customize" argv="<%=collectionArgv%>"/>
	<%
		if (tab.isCustomize()) {
	%><xava:image action="List.addColumns" argv="<%=collectionArgv%>"/><%
		}
	%>
</th>
<th class="<%=style.getListHeaderCell()%>" width="5">
	<%
		String actionOnClickAll = Actions.getActionOnClickAll(
		request.getParameter("application"), request.getParameter("module"), 
		onSelectCollectionElementAction, viewObject, prefix, cssSelectedRow,
		selectedRowStyle, rowStyle);
	%>
	<INPUT type="CHECKBOX" name="<xava:id name='xava_selected_all'/>" value="<%=prefix%>selected_all" <%=actionOnClickAll%> />
</th>
<%
	tab.reset();
java.util.Collection properties = tab.getMetaProperties();
java.util.Iterator it = properties.iterator();
int columnIndex = 0;
while (it.hasNext()) {
	MetaProperty property = (MetaProperty) it.next();
	String align = "";
	if (style.isAlignHeaderAsData()) {
		align =property.isNumber() && !property.hasValidValues()?"style='vertical-align: middle;text-align: right'":"style='vertical-align: middle;'";
	}
%>
<th class="<%=style.getListHeaderCell()%>" <%=align%>>
<%
	if (tab.isCustomize()) {
%><xava:image action="List.moveColumnToLeft" argv='<%="columnIndex="+columnIndex+collectionArgv%>'/><%
	}
%>
<%
	if (property.isCalculated()) {
%>
<%=property.getQualifiedLabel(request)%>&nbsp;
<%
	} else {
%>
<span class="<%=style.getListOrderBy()%>">
<xava:link action='List.orderBy' argv='<%="property="+property.getQualifiedName() + collectionArgv%>'><%=property.getQualifiedLabel(request)%></xava:link>&nbsp;
</span>
<%
	if (tab.isOrderAscending(property.getQualifiedName())) {
%>
<img src="<%=request.getContextPath()%>/xava/images/<%=style.getAscendingImage()%>" border="0" align="middle"/>
<%
	}
%>
<%
	if (tab.isOrderDescending(property.getQualifiedName())) {
%>
<img src="<%=request.getContextPath()%>/xava/images/<%=style.getDescendingImage()%>" border="0" align="middle"/>
<%
	}
%>
	
<%
		}
		   
		   if (tab.isCustomize()) {
	%>
	<xava:image action="List.moveColumnToRight" argv='<%="columnIndex="+columnIndex+collectionArgv%>'/>
	<xava:image action="List.removeColumn" argv='<%="columnIndex="+columnIndex+collectionArgv%>'/>
<%
	}
%>
</th>
<%
	columnIndex++;
}
%>
</tr>
<%
	if (filter) {
%>
<tr id="<xava:id name='<%="tr_list_filter_" + id%>'/>" class=<%=style.getListSubheader()%> style="display: <%=displayFilter%>"> 
<th class=<%=style.getListSubheaderCell()%> style="text-align: center" width="60">
<xava:action action="List.filter" argv="<%=collectionArgv%>"/>
</th>
<th class=<%=style.getListSubheaderCell()%> width="5">
	<a title='<xava:message key="clear_condition_values"/>' href="javascript:void(0)">
		<img src='<%=request.getContextPath()%>/xava/images/clear-right.gif'
			border='0' align='middle' onclick="openxava.clearCondition('<%=request.getParameter("application")%>', '<%=request.getParameter("module")%>', '<%=prefix%>')"/>
	</a>
</th>
<%
	it = properties.iterator();
String [] conditionValues = tab.getConditionValues();
String [] conditionComparators = tab.getConditionComparators();
int iConditionValues = -1; 
while (it.hasNext()) {
	MetaProperty property = (MetaProperty) it.next();
	if (!property.isCalculated()) {
		iConditionValues++; 
		boolean isValidValues = property.hasValidValues();
		boolean isString = "java.lang.String".equals(property.getType().getName());
		boolean isBoolean = "boolean".equals(property.getType().getName()) || "java.lang.Boolean".equals(property.getType().getName());
		boolean isDate = java.util.Date.class.isAssignableFrom(property.getType()) && !property.getType().equals(java.sql.Time.class);
		int maxLength = property.getSize();
		int length = Math.min(isString?property.getSize()*4/5:property.getSize(), 20);
		String value= conditionValues==null?"":conditionValues[iConditionValues];
		String comparator = conditionComparators==null?"":Strings.change(conditionComparators[iConditionValues], "=", "eq");		
		if (isValidValues) {
%>	
<th class=<%=style.getListSubheaderCell()%> align="left">
<%-- Boolean.toString( ) for base0 is needed in order to work in WebSphere 6 --%>
<jsp:include page="comparatorsValidValuesCombo.jsp">
	<jsp:param name="validValues" value="<%=property.getValidValuesLabels(request)%>" />
	<jsp:param name="value" value="<%=value%>" />
	<jsp:param name="base0" value="<%=Boolean.toString(!property.isNumber())%>" />
	<jsp:param name="prefix" value="<%=prefix%>"/>
	<jsp:param name="index" value="<%=iConditionValues%>"/>
</jsp:include>		
	<%
				}
						else if (isBoolean) {
			%>
<th class="<%=style.getListSubheaderCell()%>" align="left">
<jsp:include page="comparatorsBooleanCombo.jsp">
	<jsp:param name="comparator" value="<%=comparator%>" />
	<jsp:param name="prefix" value="<%=prefix%>"/>
	<jsp:param name="index" value="<%=iConditionValues%>"/> 
</jsp:include>
	<%
		} else { // Not boolean
	%>
<th class=<%=style.getListSubheaderCell()%> align="left">
<%
	String urlComparatorsCombo = "comparatorsCombo.jsp" // in this way because websphere 6 has problems with jsp:param
	+ "?comparator=" + comparator
	+ "&isString=" + isString
	+ "&isDate=" + isDate
	+ "&prefix=" + prefix  
	+ "&index=" + iConditionValues;
%>
<jsp:include page="<%=urlComparatorsCombo%>" />
<input name="<xava:id name='<%=prefix + "conditionValue." + iConditionValues%>'/>" class=<%=style.getEditor()%> type="text" maxlength="<%=maxLength%>" size="<%=length%>" value="<%=value%>"/>
	<%
		}
	%>
</th>
<%
	}
	else {
%>
<th class=<%=style.getListSubheaderCell()%>></th>
<%
	} 
} // while
%>
</tr>
<%
	} /* if (filter) */
%>
<%
	int totalSize = 0;
if (tab.isRowsHidden()) {
%>
	<tr id="nodata"><td align="center">
	<xava:link action="List.showRows" argv="<%=collectionArgv%>"/>
	</td></tr>
<%
	}
else {

IXTableModel model = tab.getTableModel(); 
totalSize = tab.getTotalSize();
if (totalSize > 0) {
for (int f=tab.getInitialIndex(); f<model.getRowCount() && f < tab.getFinalIndex(); f++) {
	String checked=tab.isSelected(f)?"checked='true'":"";
	String cssClass=f%2==0?style.getListPair():style.getListOdd();	
	String cssCellClass=f%2==0?style.getListPairCell():style.getListOddCell(); 
	String cssStyle = tab.getStyle(f);
	if (cssStyle != null) {
		cssClass = cssClass + " " + cssStyle; 
		if (style.isApplySelectedStyleToCellInList()) cssCellClass = cssCellClass + " " + cssStyle; 
	}
	String events=f%2==0?style.getListPairEvents(cssStyle):style.getListOddEvents(cssStyle);
	String cssClassToActionOnClick = cssClass;
	if (tab.isSelected(f)){
		cssClass = cssSelectedRow + " " + cssClass;
		rowStyle = rowStyle + " " + selectedRowStyle;
		events = f%2==0?style.getListPairEvents(cssStyle, cssSelectedRow):style.getListOddEvents(cssStyle, cssSelectedRow);
	}
	String prefixIdRow = Ids.decorate(request, prefix);
%>
<tr id="<%=prefixIdRow%><%=f%>" class="<%=cssClass%>" <%=events%> style="<%=rowStyle%>">
	<td class="<%=cssCellClass%>" style="vertical-align: middle;text-align: center; <%=style.getListCellStyle()%>">
<%
	if (!org.openxava.util.Is.emptyString(action)) {
%>
<xava:action action='<%=action%>' argv='<%="row=" + f + actionArgv%>'/>
<%
	}
	String actionOnClick = Actions.getActionOnClick(
		request.getParameter("application"), request.getParameter("module"), 
		onSelectCollectionElementAction, f, viewObject, prefixIdRow + f,
		cssSelectedRow, cssClassToActionOnClick, selectedRowStyle, rowStyle, 
		onSelectCollectionElementMetaAction);
%>
	</td>
	<td class="<%=cssCellClass%>" style="<%=style.getListCellStyle()%>">
	<INPUT type="<%=singleSelection?"RADIO":"CHECKBOX"%>" name="<xava:id name='xava_selected'/>" value="<%=prefix + "selected"%>:<%=f%>" <%=checked%> <%=actionOnClick%> />
	</td>	
<%
	for (int c=0; c<model.getColumnCount(); c++) {
		MetaProperty p = tab.getMetaProperty(c);
		String align =p.isNumber() && !p.hasValidValues()?"vertical-align: middle;text-align: right; ":"vertical-align: middle; ";
		String cellStyle = align + style.getListCellStyle();
		String fvalue = null;
		if (p.hasValidValues()) {
			fvalue = p.getValidValueLabel(request, model.getValueAt(f, c));
		}
		else {
			fvalue = WebEditors.format(request, p, model.getValueAt(f, c), errors, view.getViewName(), true);
		}
%>
	<td class="<%=cssCellClass%>" style="<%=cellStyle%>"><%=fvalue%>&nbsp;</td>
<%
	}
%>
</tr>
<%
}

}
else {
%>
<tr id="nodata"><td class="<%=totalSize==0?style.getMessages():style.getErrors()%>">
<% if (totalSize == 0) { %>
<b><xava:message key="no_objects"/></b>
<% } else { %>
<b><xava:message key="list_error"/></b>
<% } %>
</td></tr>
<%
}
}

if (lastRow != null) {
%>
<tr>
	<jsp:include page="<%=lastRow%>"/>
</tr>
<%
}
%>
</table>
<% if (!tab.isRowsHidden()) { %>
<table width="100%" class="<%=style.getListInfo()%>">
<tr class='<%=style.getListInfoDetail()%>'>
<td class='<%=style.getListInfoDetail()%>' style='vertical-align: middle'>
<%
int last=tab.getLastPage();
int current=tab.getPage();
if (current > 1) {
%>
<xava:image action='List.goPreviousPage' argv='<%=collectionArgv%>'/>
<% } 
for (int i=1; i<=last; i++) {
if (i == current) {
%>	 
 <b><%=i%></b>
<% } else { %>
 <xava:link action='List.goPage' argv='<%="page=" + i + collectionArgv%>'><%=i%></xava:link>
<% }} 
if (!tab.isLastPage()) {
%>
 <xava:image action='List.goNextPage' argv='<%=collectionArgv%>'/> 
<% } %>	 
</td>
<td style='text-align: right; vertical-align: middle' class='<%=style.getListInfoDetail()%>'>
<% if (XavaPreferences.getInstance().isShowCountInList()) { %>
<xava:message key="list_count" intParam="<%=totalSize%>"/>
<% } %>
<% if (collection == null) { %>
(<xava:link action="List.hideRows" argv="<%=collectionArgv%>"/>)
<% } %>
</td>
</tr>
</table>
<% } %>
