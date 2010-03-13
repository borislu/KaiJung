<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ include file="../xava/imports.jsp"%>
<html xmlns:x="http://www.zkoss.org/2005/zul">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link href="../css/table.css" rel="stylesheet" type="text/css" />
<link href="../css/content.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
//在id 指定的 table 中增加一列
var index = 0;
function addtr( tableid ){
    index = $("#"+ tableid +">tbody>tr:eq(1)").attr("id").substring(3);
    //alert( index );
    index = parseInt(index, 10);
    index++;

    $("<tr id = 'row"+ index +"'><td><div align='center'><input name='checkbox' id='checkbox' type='checkbox' /></div></td><td><div align='center'>"+ index +"</div></td><td><div align='center'><input name='r1' id='r1' value='' size='12'maxlength='12' type='text' /></div></td><td><div align='center'><input name='r2' id='r2' value='' size='3' maxlength='3' type='text' /></div></td><td><div align='center'><input name='r3' id='r3'value='' size='4' maxlength='4' class='numbers' type='text' /></div></td><td><div align='center'><input name='r4' id='r4' value='' size='4' maxlength='4' class='numbers' type='text' /></div></td><td><div align='center'><input name='r5' id='r5' value='' size='4' maxlength='4' class='numbers' type='text' /></div></td><td><div align='center'><input name='r6' id='r6' value='' size='4' maxlength='4'class='numbers' type='text' /></div></td><td><div align='center'><input name='r7' id='r7' value='' size='4' maxlength='4' class='numbers' type='text' /></div></td><td><divalign='center'><input name='r8' id='r8' value='' size='6' maxlength='5' class='numbers' type='text' /></div></td><td><div align='center'><input name='r9' id='r9' value='' size='5' maxlength='5'type='text' /></div></td><td><div align='center'><input name='r10' id='r10' value='' size='28' maxlength='40' type='text' /></div></td><td><a href='javascript:void(0)' onclick='javascript:deltr(\"row"+ index +"\")' id='del"+ index +"'><img border='0' alt='刪除一列' src='../icons/cancel.png' class='addRow'></a></td></tr>").insertAfter($("#"+ tableid +">tbody>tr:eq(0)"));
				//    $('#'+ tableid ).append(str);
}

//删除 id 指定的列
function deltr ( rowid ){
    //alert('deltr rowid: '+ rowid);
    $('#'+ rowid ).remove();
}

$('document').ready(function() {
//    alert( $('#frameWest').contents().find("#mainTable").css('color' , 'red') );

//    $("#edit_add").click( addtr( "mainTable" ) );
});
</script>

<style type="text/css">
<!--
#content1 {
	width: 700px;
}

#table1 {
	float: left;
	width: 700px;
}

#table2 {
	float: left;
	top: 100px;
	height: 100px;
	width: 700px;
}

#column1 {
	float: left;
}

#column2 {
	float: left;
}

#column3 {
	float: left;
}
-->
</style>
</head>
<body>
<x:window id="win" title="new2 test" width="640px" border="normal" apply="com.kaijung.zk.controller.OrderStoreDController">
	<x:groupbox>
		<x:caption label="Event" />
		Item: <x:textbox id="itemid" cols="25" value="@{win$composer.current.itemid}" />
		<button id="add" label="Add" width="36px" height="24px"/>
	</x:groupbox>	
</x:window>
</body>
</html>
