<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://www.zkoss.org/jsp/zul" prefix="z"%>
<z:init use="org.zkoss.zkplus.databind.AnnotateDataBinderInit"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link href="../css/table.css" rel="stylesheet" type="text/css" />
<link href="../xava/style/liferay51/css/everything_unpacked.css" rel="stylesheet" type="text/css" />
<link href="../xava/style/liferay51/css/kaijung.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function batchAdd( articlenos, colors, sizes24, sizes26, sizes28, sizes30, sizes32, memos ){ // 批次增加列
    length = articlenos.length;
    //alert( 'articlenos[0]: '+ articlenos[0].value );
    for( i=0; i<length; i++ ){
        index2 = addtr( 'mainTable', articlenos[i].value, colors[i].value, sizes24[i].value, sizes26[i].value, sizes28[i].value, 
            sizes30[i].value, sizes32[i].value, memos[i].value ) ; //回傳的 index 從 2 開始
    }
}
//在id 指定的 table 中增加一列
function addtr( tableid, articleno, color, size24, size26, size28, size30, size32, memo ){
    index = $("#"+ tableid +">tbody>tr:eq(1)").attr("id").substring(3);
    index = parseInt(index, 10);
    index++;

    $("<tr id = 'row"+ index +"'><td><div align='center'><input name='checkbox' id='checkbox' type='checkbox' /></div></td><td><div align='center'>"+ index +"</div></td><td><div align='center'><input name='articleno' id='articleno' value='"+ articleno +"' size='12'maxlength='12' type='text' /></div></td><td><div align='center'><input name='color_1_1' id='color_1_1' value='"+ color +"' size='3' maxlength='3' type='text' /></div></td><td><div align='center' class='r1All'><input name='r1' id='r1' value='"+ size24 +"' size='3' maxlength='3' class='numbers' type='text' onChange='setAll();'/></div></td><td><div align='center' class='r2All'><input name='r2' id='r2' value='"+ size26 +"' size='3' maxlength='3' class='numbers' type='text' onChange='setAll();'/></div></td><td><div align='center' class='r3All'><input name='r3' id='r3' value='"+ size28 +"' size='3' maxlength='3' class='numbers' type='text' onChange='setAll();'/></div></td><td><div align='center' class='r4All'><input name='r4' id='r4' value='"+ size30 +"' size='3' maxlength='3'class='numbers' type='text' onChange='setAll();'/></div></td><td><div align='center' class='r5All'><input name='r5' id='r5' value='"+ size32 +"' size='3' maxlength='3' class='numbers' type='text' onChange='setAll();'/></div></td><td><div align='center' class='total'><input name='r6' id='r6' value='0' size='6' maxlength='6' class='numbers' type='text' readOnly/></div></td><td><div align='center'><input name='r9' id='r9' value='' size='6' maxlength='6'type='text' /></div></td><td><div align='center'><input name='r10' id='r10' value='"+ memo +"' size='28' maxlength='40' type='text' /></div></td><td><a href='javascript:void(0)' onclick='javascript:deltr(\"row"+ index +"\")' id='del"+ index +"'><img border='0' alt='刪除一列' src='../icons/cancel.png' class='addRow'></a></td></tr>").insertAfter($("#"+ tableid +">tbody>tr:eq(0)"));
    //    $('#'+ tableid ).append(str);
    return index;
}

//删除 id 指定的列
function deltr ( rowid ){
    //alert('deltr rowid: '+ rowid);
    $('#'+ rowid ).remove();
}
var quantities = {"quantities": [
        {"qty1": "4", "qty2": "5", "qty3": "6", "qty4": "7", "qty5": "8"},
        {"qty1": "15", "qty2": "14", "qty3": "13", "qty4": "12", "qty5": "11"}
    ]
};
function combineQty(){
    alert( 'quantity: '+ $('quantity') );
    $('quantity').value = $('qty_1_1').value ;
}

function setAll(){
	var total = [];
	var r1SumTotal = 0;
	for(var a=0;a<5;a++){
		total[a]=[];
	}
	$(".r1All :text").each(function(i){
		var k = $(this)[0].value;
		if(k != ""){
			r1SumTotal += parseInt(k);
			total[0][i] = parseInt(k);
		}else{
			total[0][i] = 0;
		}
		$("#sum1")[0].value = r1SumTotal;		
	});
	var r2SumTotal = 0;	
	$(".r2All :text").each(function(i){
		var k = $(this)[0].value;
		if(k != ""){
			r2SumTotal += parseInt(k);
			total[1][i] = parseInt(k);
		}else{
			total[1][i] = 0;
		}
		$("#sum2")[0].value = r2SumTotal;		
	});
	var r3SumTotal = 0;	
	$(".r3All :text").each(function(i){
		var k = $(this)[0].value;
		if(k != ""){
			r3SumTotal += parseInt(k);
			total[2][i] = parseInt(k);
		}else{
			total[2][i] = 0;
		}
		$("#sum3")[0].value = r3SumTotal;		
	});
	var r4SumTotal = 0;	
	$(".r4All :text").each(function(i){
		var k = $(this)[0].value;
		if(k != ""){
			r4SumTotal += parseInt(k);
			total[3][i] = parseInt(k);
		}else{
			total[3][i] = 0;
		}
		$("#sum4")[0].value = r4SumTotal;		
	});	
	var r5SumTotal = 0;	
	$(".r5All :text").each(function(i){
		var k = $(this)[0].value;
		if(k != ""){
			r5SumTotal += parseInt(k);
			total[4][i] = parseInt(k);
		}else{
			total[4][i] = 0;
		}
		$("#sum5")[0].value = r5SumTotal;		
	});
	var sum = 0
	$(".total :text").each(function(i){
		var totalSum = 0;
		for(var c=0;c<5;c++){
			totalSum += total[c][i];
			sum += total[c][i];
		}	
		$(this)[0].value=totalSum;
	});
	$("#sumTotal")[0].value = sum;
	
}

</script>
<style type="text/css">
				<!--
				#_eis_batchsave { visibility:hidden; }
				-->
</style>
</head>

<body>
<z:page zscriptLanguage="java">
<input type="hidden" id="quantity" value="@{gru$composer.beanD.quantity}"/> 

<jsp:include page="../xava/module_include.jsp" flush="true"> 
    <jsp:param name="application" value="KaiJung" /> 
    <jsp:param name="module" value="OrderStoreDetailOnly" /> 
</jsp:include> 

<div id="lineTable">
<z:groupbox id="gru" apply="com.kaijung.zk.controller.OrderStoreDController">
<zscript><![CDATA[
    import com.kaijung.dao.*;
    import com.kaijung.jpa.*;
    import com.kaijung.zk.controller.OrderStoreDController;
    OrderStoreDController controller = new OrderStoreDController();
    OrderStoreD bean = new OrderStoreD();
    void combineQty() {
        alert( "qty_1_1: "+ qty_1_1.value );
        bean.setQuantity( qty_1_1.value );
        controller.insert( bean );
    }
    ]]>
</zscript>
<table border="0" cellpadding="0" cellspacing="0" width="734">
 <tbody>
  <tr>
   <td>
   <div align="center">
   <table id="mainTable" align="center" border="1" bordercolor="#cccccc" cellpadding="0" cellspacing="0" width="765">
       <tbody> 
        <tr id="headRow">
         <td rowspan="1" class="tableHead2" width="6"><!--全選--> <input name="selectall" id="selectall" type="checkbox" /></td>
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
         <td><a href='javascript:void(0)' onclick='addtr("mainTable","","","","","","","","")'>
              <img border='0' alt='新增一列' src='../icons/edit_add.png'></a></td>
        </tr>
        <tr method="post" name="row1" id="row1">
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
                <div align="center"><z:textbox id="color_1_1" cols="3" maxlength="3" /></div>
            </td>
            <td>
                <div align="center" class="r1All"><input name="r1" id="r1" value="" size="3" maxlength="3" class="numbers" type="text" onChange="setAll();"/></div>
            </td>
            <td>
                <div align="center" class="r2All"><input name="r2" id="r2" value="" size="3" maxlength="3" class="numbers" type="text" onChange="setAll();"/></div>
            </td>
            <td>
                <div align="center" class="r3All"><input name="r3" id="r3" value="" size="3" maxlength="3" class="numbers" type="text" onChange="setAll();"/></div>
            </td>
            <td>
                <div align="center" class="r4All"><input name="r4" id="r4" value="" size="3" maxlength="4" class="numbers" type="text" onChange="setAll();"/></div>
            </td>
            <td>
                <div align="center" class="r5All"><input name="r5" id="r5" value="" size="3" maxlength="4" class="numbers" type="text" onChange="setAll();"/></div>
            </td>
            <td>
                <div align="center" class='total'><input name="r6" id="r6" value="0" size="6" maxlength="6" class="numbers" type="text" readOnly/></div>
            </td>
            <td>
                <div align="center"><input name="r9" id="r9" value="" size="6" maxlength="6" type="text" /></div>
            </td>
            <td>
                <div align="center"><input name="r10" id="r10" value="" size="28" maxlength="40" type="text" /></div>
            </td>
            <td><a href="javascript:void(0)" id="edit_del1"><img border="0" alt="刪除一列" src="../icons/cancel.png" class="addRow"></a></td>
        </tr>
        <tr id="sumRow">
            <td rowspan="1" colspan="4" class="tableHead2">合計</td>
            <td colspan="1" align="center">
            	<div align="center"><input name="sum1" id="sum1" value="0" size="3" maxlength="4" type="text" readonly/></div>
            </td>
            <td colspan="1" align="center">
                <div align="center"><input name="sum2" id="sum2" value="0" size="3" maxlength="4" type="text" readonly/></div>
            </td>
            <td colspan="1" align="center">
                <div align="center"><input name="sum3" id="sum3" value="0" size="3" maxlength="4" type="text" readonly/></div>
            </td>
            <td colspan="1" align="center">
                <div align="center"><input name="sum4" id="sum4" value="0" size="3" maxlength="4" type="text" readonly/></div>
            </td>
            <td colspan="1" align="center">
                <div align="center"><input name="sum5" id="sum5" value="0" size="3" maxlength="4" type="text" readonly/></div>
            </td>
            <td colspan="1" align="center">
            	<div align="center"><input name="sumTotal" id="sumTotal" value="0" size="6" maxlength="6" type="text" readonly/></div>
            </td>
            <td colspan="3" class="tableHead2"></td>
        </tr> 
        <tr id="remarkRow">
            <td colspan="4" class="tableHead2" valign="middle">備註</td>
            <td colspan="10" class="tableHead2" valign="middle"><textarea cols="82" rows="1" name="remark">&nbsp;</textarea></td>
        </tr>
    </tbody>
   </table>
   </div>
   </td>
  </tr>
 </tbody>
</table>
<z:button id="batchsave" label="儲存" width="1px" height="1px"/><!-- onClick="combineQty()" onClick='Clients.evalJavaScript("combineQty()")'-->
</z:groupbox> 
</div>
</z:page>
</body>
</html>
