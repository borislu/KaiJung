<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<z:page zscriptLanguage="java">
<z:window id="win" title="To do list" width="640px" border="normal" apply="org.zkforge.todo.event.EventController">
	<z:listbox id="box" multiple="true" rows="5" model="@{win$composer.allEvents, load-after='add.onClick, delete.onClick, update.onClick'}" 
		selectedItem="@{win$composer.current}">
		<z:listhead>
			<z:listheader label="Item" />
			<z:listheader label="Priority" width="50px" />
			<z:listheader label="Date" width="90px" />
		</z:listhead>		
		<z:listitem self="@{each='event'}" value="@{event}">			
			<z:listcell label="@{event.name}" />
			<z:listcell label="@{event.priority}" />
			<z:listcell label="@{event.date}" />
		</z:listitem>
	</z:listbox>
	<z:groupbox>
		<z:caption label="Event" />
		Item: <z:textbox id="name" cols="25" value="@{win$composer.current.name}" />
		Priority: <z:intbox id="priority" cols="1" value="@{win$composer.current.priority}" />
		Date: <z:datebox id="date" cols="8" value="@{win$composer.current.date}" />
		<z:button id="add" label="Add" width="36px" height="24px"/>
		<z:button id="update" label="Update" width="46px" height="24px"/>
		<z:button id="delete" label="Delete" width="46px" height="24px"/>
	</z:groupbox>	
</z:window>
</z:page>
</body>
</html>
