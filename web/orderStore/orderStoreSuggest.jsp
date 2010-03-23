<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ include file="../xava/imports.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link href="../css/table.css" rel="stylesheet" type="text/css" />
<link href="../xava/style/liferay51/css/everything_unpacked.css" rel="stylesheet" type="text/css" />
<link href="../xava/style/liferay51/css/kaijung.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery-1.4.2.min.js"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/OrderStoreNew.js'></script>
<script type='text/javascript' src='orderStoreNew.js'></script>
<script type="text/javascript">
var recordIndex;
function batchOrder(){
   /*
    size = $('.datarow').length;
    articlenos = new Array(size);
    colors = new Array(size);
    sizes = new Array(size);
    modifyids = new Array(size);
    memos = new Array(size);
   */
    recordSize = $('.datarow').length;
    //alert( 'recordSize: '+ recordSize );
    for(i=1;i<=recordSize;i++){
        //alert( 'chk_'+ i +' exist , length: '+ $('#chk_'+i).length + ', checked: '+ $('#chk_'+i).checked );
        if( $('#chk_'+i).length > 0 ){
            if( $('#chk_'+i).attr("checked")==true ){
                barcode = $('#barcode_'+i).val()
                articleno = $('#articleno_'+i).val()
                price = $('#price_'+i).val()
                color = $('#color_'+i).val()
                size24 = $('#size24_'+i).val()
                size26 = $('#size26_'+i).val()
                size28 = $('#size28_'+i).val()
                size30 = $('#size30_'+i).val()
                size32 = $('#size32_'+i).val()
                memo = $('#memo_'+i).val()
                parent.frames["frameWest"].batchAdd( // 以欄的方式將記錄寫入陣列，然後傳到顯示的 iframe
                    barcode, articleno, price, color, size24, size26, size28, size30, size32, memo); //呼叫 new.jsp 的 batchAdd()
            //    deltr('tr'+i);
            /*
                $('#barcode_'+i).value = "";
                $('#articleno_'+i).value = "";
                $('#price_'+i).value = "";
                $('#color_'+i).value = "";
                $('#size24_'+i).value = "";
                $('#size26_'+i).value = "";
                $('#size28_'+i).value = "";
                $('#size30_'+i).value = "";
                $('#size32_'+i).value = "";
                $('#memo_'+i).value = "";
                $('#reason_'+i).value = "";
                $('#stock_'+i).value = "";
                $('#sales_'+i).value = "";
                $('#color_'+i).value = "";
                $('#chk_'+i).checked = false;
            */
                $('#tr'+ i +' td').remove();
            }
        }
    }//for
}//batchOrder

//删除 id 指定的列，最小為 1
function delCheckedTrs (){
    $("input[name='chk']:checked").each(function(rowIndex) {
        $('#tr'+ (rowIndex+1) ).remove();
     });
}

function selectAll(){
   if($("#selectall").attr("checked"))
   {
     $("input[name='chk']").each(function() {
         $(this).attr("checked", true);
     });
   }
   else
   {
     $("input[name='chk']").each(function() {
         $(this).attr("checked", false);
     });          
   }
}
</script> 
<style type="text/css">
<!--
#append { visibility:hidden; }
-->
</style>
</head>

<body onload="javascript:beginLoad();">
<form id="form1" action="">
<div><div>
  </div><table id="mainTable" border="1" bordercolor="#cccccc" cellpadding="0" cellspacing="0" width="818">
    
    <tbody><tr>
      <td class="tableHead2" height="25" nowrap="nowrap" width="33">期間</td>
      <td class="tableHead2" colspan="5">2009/11/23 至 2009/11/25</td>

        <td class="tableHead" colspan="1">65/SML
        </td><td colspan="1" class="tableHead">70/MDM
        </td>
        <td colspan="1" class="tableHead">75/LRG
        </td>
        <td colspan="1" class="tableHead">80/ELG
        </td>
        <td colspan="1" class="tableHead">85/QLG
        </td>

      <td class="tableHead2" rowspan="2">備註</td>
      <td class="tableHead2" rowspan="2">
        <input name="selectall" id="selectall" type="checkbox" onChange="selectAll();"/>
      </td>
    </tr>
    <tr>
      <td class="tableHead2" height="17" nowrap="nowrap">項次</td>
      <td class="tableHead2" nowrap="nowrap">推薦原因</td>
      <td class="tableHead2" nowrap="nowrap" width="44">庫存</td>

      <td class="tableHead2" nowrap="nowrap" width="42">銷售量</td>
      <td class="tableHead2" width="40">貨號</td>
      <td class="tableHead2" width="42">顏色</td>
      <td class="tableHead" colspan="1">
24
        </td>
        <td colspan="1" class="tableHead">
26
        </td>
        <td colspan="1" class="tableHead">
28
        </td>
        <td colspan="1" class="tableHead">
30
        </td>
        <td colspan="1" class="tableHead">
32
        </td>
    </tr>
    <tr id="tr1" class="datarow">
      <td style="text-align: center;"><div align="center">1</div></td>
      <td style="text-align: center;"><input id="reason_1"   name="reason_1"  value="銷貨補貨" readonly class="reasonR"></td>
      <td style="text-align: center;"><input id="stock_1"   name="stock_1"  value="120" readonly class="stockR"></td>
      <td style="text-align: center;"><input id="sales_1"   name="sales_1"  value="55" readonly class="salesR"></td>
      <td style="text-align: center;"><input id="articleno_1"  name="articleno_1"  value="001-00001" readonly class="articlenoR"></td>
      <td style="text-align: center;"><input id="color_1"   name="color_1"   value="BLUE" readonly class="colorR"></td>
      <td style="text-align: center;"><input id="size24_1"   name="size24_1"  value="5" maxlength="3" class="orderSize"/>/121</td>
      <td style="text-align: center;"><input id="size26_1"   name="size26_1"  value="6" maxlength="3" class="orderSize"/>/132</td>
      <td style="text-align: center;"><input id="size28_1"   name="size28_1"  value="7" maxlength="3"  class="orderSize"/>/173</td>
      <td style="text-align: center;"><input id="size30_1"   name="size30_1"  value="8" maxlength="3"  class="orderSize"/>/184</td>
      <td style="text-align: center;"><input id="size32_1"   name="size32_1"  value="9" maxlength="3"  class="orderSize"/>/115</td>
      <td style="text-align: center;"><input id="memo_1"   name="memo_1" class="memo01" /></td>
      <td style="text-align: center;"><input id="chk_1"   name="chk" type="checkbox" checked/></td>
      <td style="text-align: center;"><input id="barcode_1"  name="barcode_1" value="BC000001" readonly class="barcodeR" style="visibility:hidden;"></td>
      <td style="text-align: center;"><input id="price_1"   name="price_1" value="1000" readonly class="barcodeR" style="visibility:hidden;"></td>
    </tr>
    <tr id="tr2" class="datarow">
      <td style="text-align: center;"><div align="center">2</div></td>
      <td style="text-align: center;"><input id="reason_2"   name="reason_2"  value="缺貨到貨" readonly class="reasonR"></td>
      <td style="text-align: center;"><input id="stock_2"   name="stock_2"  value="120" readonly class="stockR"></td>
      <td style="text-align: center;"><input id="sales_2"   name="sales_2"  value="55" readonly class="salesR"></td>
      <td style="text-align: center;"><input id="articleno_2"  name="articleno_2"  value="001-00002" readonly class="articlenoR"></td>
      <td style="text-align: center;"><input id="color_2"   name="color_2"   value="RED" readonly class="colorR"></td>
      <td style="text-align: center;"><input id="size24_2"   name="size24_2"  value="11" maxlength="3" class="orderSize"/>/121</td>
      <td style="text-align: center;"><input id="size26_2"   name="size26_2"  value="12" maxlength="3" class="orderSize"/>/132</td>
      <td style="text-align: center;"><input id="size28_2"   name="size28_2"  value="13" maxlength="3"  class="orderSize"/>/173</td>
      <td style="text-align: center;"><input id="size30_2"   name="size30_2"  value="14" maxlength="3"  class="orderSize"/>/184</td>
      <td style="text-align: center;"><input id="size32_2"   name="size32_2"  value="15" maxlength="3"  class="orderSize"/>/115</td>
      <td style="text-align: center;"><input id="memo_2"   name="memo_2" class="memo01" /></td>
      <td style="text-align: center;"><input id="chk_2"   name="chk" type="checkbox" checked/></td>
      <td style="text-align: center;"><input id="barcode_2"  name="barcode_2" value="BC000002" readonly class="barcodeR" style="visibility:hidden;"></td>
      <td style="text-align: center;"><input id="price_2"   name="price_2" value="450" readonly class="barcodeR" style="visibility:hidden;"></td>
    </tr>
    <tr>
      <td style="text-align: center;"><div align="center">3</div></td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
    </tr>
    <tr>
      <td style="text-align: center;"><div align="center">4</div></td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
    </tr>
    <tr>
      <td style="text-align: center;"><div align="center">5</div></td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
    </tr>
    <tr>
      <td style="text-align: center;"><div align="center">6</div></td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
    </tr>
    <tr>
      <td style="text-align: center;"><div align="center">7</div></td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
    </tr>
    <tr>
      <td style="text-align: center;"><div align="center">8</div></td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
    </tr>
    <tr>
      <td style="text-align: center;"><div align="center">9</div></td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
    </tr>
    <tr>
      <td style="text-align: center;"><div align="center">10</div></td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
      <td style="text-align: center;">&nbsp;</td>
    </tr>
    <!--  <input type="button" id="initImport" value="IMPORE" onClick="javascript:beginLoad();" /> -->
  </tbody>
  
  </table>
</div>
<!--<input type="button" id="append" onclick="javascript:batchOrder();"> --><!-- 隱藏按鈕，全部加入訂單 -->
</form>
</body>
</html>
