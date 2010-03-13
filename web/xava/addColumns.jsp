<%@ include file="imports.jsp"%>

<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Locale" %> <%-- Trifon --%>
<%@ page import="org.openxava.util.Locales" %> <%-- Trifon --%>
<%@ page import="org.openxava.util.Labels" %> <%-- Trifon --%>


<jsp:useBean id="context" class="org.openxava.controller.ModuleContext"
scope="session"/>
<jsp:useBean id="style" class="org.openxava.web.style.Style" scope="request"/>

<%
org.openxava.tab.Tab tab = (org.openxava.tab.Tab) context.get(request,
"xava_customizingTab");

%>
<div class="portlet-form-label">
<xava:message key="choose_property_add_list_prompt"/>
</div>

<table id="<xava:id name='xavaPropertiesList'/>" class='<%=style.getList()%>' width="100%" <%=style.getListCellSpacing()%> style="margin-bottom: 5px; <%=style.getListStyle()%>">
<tr class="<%=style.getListHeader()%>">
	<th class="<%=style.getListHeaderCell()%>" style="text-align: center" width="5"></th>
	<th class="<%=style.getListHeaderCell()%>" style="text-align: center"><xava:message key="property"/></th>
	<th class="<%=style.getListHeaderCell()%>" style="text-align: center"><xava:message key="label"/></th>
</tr>
<%
int f=0;
Locale currentLocale = Locales.getCurrent(); //Trifon
for (Iterator it=tab.getColumnsToAdd().iterator(); it.hasNext();) {
	String property = (String) it.next();
	String cssClass=f%2==0?style.getListPair():style.getListOdd();	
	String cssCellClass=f%2==0?style.getListPairCell():style.getListOddCell(); 
	String events=f%2==0?style.getListPairEvents(cssCellClass):style.getListOddEvents(cssCellClass);		
	f++;
	String propertyI18n = Labels.getQualified(property, currentLocale); // Trifon
%>
<tr class="<%=cssClass%>" <%=events%> style="border-bottom: 1px solid;">
	<td class="<%=cssCellClass%>" style="<%=style.getListCellStyle()%>" width="5">
		<INPUT type="CHECKBOX" name="<xava:id name='xava_selected'/>" value="selectedProperties:<%=property%>"/>
	</td>
	<td class="<%=cssCellClass%>" style="<%=style.getListCellStyle()%>">
		<%=property%>
	</td>
	<td class="<%=cssCellClass%>" style="<%=style.getListCellStyle()%>">
		<%=propertyI18n%>
	</td>
</tr>
<%
}
%>
</table>

<table width="100%" class="<%=style.getListInfo()%>">
<tr class='<%=style.getListInfoDetail()%>'>
<td class='<%=style.getListInfoDetail()%>' style='vertical-align: middle'>
<%
int last=tab.getAddColumnsLastPage();
int current=tab.getAddColumnsPage();
if (current > 1) {
%>
<xava:image action='List.goAddColumnsPreviousPage'/>
<% } 
for (int i=1; i<=last; i++) {
if (i == current) {
%>	 
 <b><%=i%></b>
<% } else { %>
 <xava:link action='List.goAddColumnsPage' argv='<%="page=" + i%>'><%=i%></xava:link>
<% }
} 
if (current < last) {
%>
 <xava:image action='List.goAddColumnsNextPage'/> 
<% } %>	 
</td>
</tr>
</table>
