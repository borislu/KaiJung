<?init class="org.zkoss.zkplus.databind.AnnotateDataBinderInit" ?>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>訂貨</title>
<link href="../css/table.css" rel="stylesheet" type="text/css" />
<link href="../xava/style/liferay51/css/everything_unpacked.css" rel="stylesheet" type="text/css" />
<link href="../xava/style/liferay51/css/kaijung.css" rel="stylesheet" type="text/css" />
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

//$('document').ready(function() {
//    alert( $('#frameWest').contents().find("#mainTable").css('color' , 'red') );
//    $("#edit_add").click( addtr( "mainTable" ) );
//});
</script>

</head>

<body>
<z:page zscriptLanguage="java">
<form id="lineForm" action="controller" method="post">

<iframe src="../xava/modules.jsp?application=KaiJung&module=OrderStoreHead" width="820" frameborder="0"   scrolling="no"></iframe>

<div id="lineTable">
<z:window id="win" title="訂貨明細" width="820px" border="normal" apply="com.kaijung.zk.controller.OrderStoreDController">
<table border="0" cellpadding="0" cellspacing="0" width="734">
	<tbody>
		<tr>
			<td>
			<div align="center">
			<table id="mainTable" align="center" border="1" bordercolor="#cccccc"
				cellpadding="0" cellspacing="0" width="765">
				<tbody>
					<tr id="headRow">
						<td rowspan="1" class="tableHead2" width="6"><!--全選--> <input
							name="selectall" id="selectall" type="checkbox" /></td>
						<td rowspan="1" class="tableHead2" width="32" nowrap="nowrap">項次</td>
						<td rowspan="1" class="tableHead2" width="104">貨號</td>
						<td rowspan="1" class="tableHead2" width="40">顏色</td>
						<td rowspan="1" colspan="1" class="tableHead3">
						<div align="center">65/SML<br />
						024<br />
						</div>
						</td>
						<td rowspan="1" colspan="1" class="tableHead3">
						<div align="center">70/MDM<br />
						026<br />
						</div>
						</td>
						<td rowspan="1" colspan="1" class="tableHead3">
						<div align="center">75/LRG<br />
						028<br />
						</div>
						</td>
						<td rowspan="1" colspan="1" class="tableHead3">
						<div align="center">80/ELG<br />
						030<br />
						</div>
						</td>
						<td colspan="1" rowspan="1" class="tableHead3">
						<div align="center">85/QLG<br />
						032<br />
						</div>
						</td>

						<td rowspan="1" class="tableHead2" width="56">小計</td>
						<td rowspan="1" class="tableHead2" width="56">修改單號</td>
						<td rowspan="1" colspan="1" class="tableHead2">備註</td>
						<td><a href="javascript:void(0)" id="edit_add"
							onclick="addtr('mainTable')"><img border="0" alt="新增一列"
							src="../icons/edit_add.png" class="addRow"></a></td>
					</tr>
					<tr id="row1">
						<td>
						<div align="center"><input name="checkbox" id="checkbox"
							type="checkbox" /></div>
						</td>
						<td>
						<div align="center">1</div>
						</td>
						<td>
						<div align="center">
						<z:textbox id="articleno" cols="12" /></div>
						</td>
						<td>
						<div align="center"><input name="r6" id="r6" value=""
							size="3" maxlength="3" class="numbers" type="text" /></div>
						</td>
						<td>
						<div align="center"><z:textbox id="quantity" cols="3" value="@{win$composer.dBean.quantity}" /></div>
						</td>
						<td>
						<div align="center"><input name="r6" id="r6" value=""
							size="4" maxlength="3" class="numbers" type="text" /></div>
						</td>
						<td>
						<div align="center"><input name="r6" id="r6" value=""
							size="4" maxlength="3" class="numbers" type="text" /></div>
						</td>
						<td>
						<div align="center"><input name="r6" id="r6" value=""
							size="4" maxlength="4" class="numbers" type="text" /></div>
						</td>
						<td>
						<div align="center"><input name="r7" id="r7" value=""
							size="4" maxlength="4" class="numbers" type="text" /></div>
						</td>
						<td>
						<div align="center"><input name="r8" id="r8" value=""
							size="6" maxlength="5" class="numbers" type="text" /></div>
						</td>
						<td>
						<div align="center"><input name="r9" id="r9" value=""
							size="5" maxlength="5" type="text" /></div>
						</td>
						<td>
						<div align="center"><input name="r10" id="r10" value=""
							size="28" maxlength="40" type="text" /></div>
						</td>
						<td><a href="javascript:void(0)" id="edit_del1"><img
							border="0" alt="刪除一列" src="../icons/cancel.png" class="addRow"></a></td>
					</tr>
					<tr id="sumRow">
						<td rowspan="1" colspan="4" class="tableHead2">合計</td>
						<td colspan="1" style="vertical-align: top;"></td>
						<td colspan="1" style="vertical-align: top;"></td>
						<td colspan="1" style="vertical-align: top;"></td>
						<td colspan="1" style="vertical-align: top;"></td>
						<td colspan="1" style="vertical-align: top;"></td>
						<td colspan="1" align="center">&nbsp;</td>
						<td colspan="3" class="tableHead2"></td>
					</tr>
					<tr id="remarkRow">
						<td colspan="4" class="tableHead2" valign="middle">備註</td>
						<td colspan="10" class="tableHead2" valign="middle"><textarea
							cols="76" rows="1" name="remark">&nbsp;</textarea></td>
					</tr>
				</tbody>
			</table>
			</div>
			</td>
		</tr>
	</tbody>
</table>
<z:button id="add" label="儲存" width="36px" height="24px"/>
</z:window>
</div>
</form> 
</z:page>
</body>
</html>
