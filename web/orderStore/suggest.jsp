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
<script type="text/javascript" src="../js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.form.js"></script>
<script type="text/javascript"> 
				function batchOrder(){
								//alert( $('.datarow').length );
/*
								size = $('.datarow').length;
								articlenos = new Array(size);
								colors = new Array(size);
								sizes = new Array(size);
								modifyids = new Array(size);
								memos = new Array(size);
*/
								parent.frames["frameWest"].batchAdd( // 以欄的方式將記錄寫入陣列，然後傳到顯示的 iframe
												$('input[name="barcode"]'), $('input[name="articleno"]'), $('input[name="price"]'), $('input[name="color"]'), $('input[name="size24"]'), $('input[name="size26"]'), $('input[name="size28"]'), 
												$('input[name="size30"]'), $('input[name="size32"]'), $('input[name="memo"]') 
								); //呼叫 new.jsp 的 batchAdd()
				}
</script> 
<style type="text/css">
<!--
#append { visibility:hidden; }
-->
</style>
</head>

<body>
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
        <input name="selectall" id="selectall" type="checkbox" checked />
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
      <td style="text-align: center;"><input id="reason1" value="銷貨補貨" readonly class="reasonR"></td>
      <td style="text-align: center;"><input id="stock1" value="120" readonly class="stockR"></td>
      <td style="text-align: center;"><input id="sales1" value="55" readonly class="salesR"></td>
      <td style="text-align: center;"><input id="articleno1" name="articleno" value="609-61545" readonly class="articlenoR"></td>
      <td style="text-align: center;"><input id="color1" name="color" value="BRN" readonly class="colorR"></td>
      <td style="text-align: center;"><input id="size24_1" name="size24" maxlength="3" value="5" class="orderSize"/>/121</td>
      <td style="text-align: center;"><input id="size26_1" name="size26" maxlength="3" value="6" class="orderSize"/>/132</td>
      <td style="text-align: center;"><input id="size28_1" name="size28" maxlength="3" value="7" class="orderSize"/>/173</td>
      <td style="text-align: center;"><input id="size30_1" name="size30" maxlength="3" value="8" class="orderSize"/>/184</td>
      <td style="text-align: center;"><input id="size32_1" name="size32" maxlength="3" value="9" class="orderSize"/>/115</td>
      <td style="text-align: center;"><input id="memo1" name="memo" class="memo01" /></td>
      <td style="text-align: center;"><input id="chk1" name="chks" type="checkbox" checked /></td>
      <td style="text-align: center;"><input id="barcode1" name="barcode" value="BC-754" readonly class="barcodeR" style="visibility:hidden;"></td>
      <td style="text-align: center;"><input id="price1" name="price" value="1,000" readonly class="barcodeR" style="visibility:hidden;"></td>
    </tr>
    <tr id="tr2" class="datarow">
      <td style="text-align: center;"><div align="center">2</div></td>
      <td style="text-align: center;"><input id="reason2" value="缺貨到貨" readonly class="reasonR"></td>
      <td style="text-align: center;"><input id="stock2" value="120" readonly class="stockR"></td>
      <td style="text-align: center;"><input id="sales2" value="55" readonly class="salesR"></td>
      <td style="text-align: center;"><input id="articleno2" name="articleno" value="609-52345" readonly class="articlenoR"></td>
      <td style="text-align: center;"><input id="color2" name="color" value="BRN" readonly class="colorR"></td>
      <td style="text-align: center;"><input id="size24_2" name="size24" maxlength="3" value="11" class="orderSize"/>/121</td>
      <td style="text-align: center;"><input id="size26_2" name="size26" maxlength="3" value="12" class="orderSize"/>/132</td>
      <td style="text-align: center;"><input id="size28_2" name="size28" maxlength="3" value="13" class="orderSize"/>/173</td>
      <td style="text-align: center;"><input id="size30_2" name="size30" maxlength="3" value="14" class="orderSize"/>/184</td>
      <td style="text-align: center;"><input id="size32_2" name="size32" maxlength="3" value="15" class="orderSize"/>/115</td>
      <td style="text-align: center;"><input id="memo2" name="memo" class="memo01" /></td>
      <td style="text-align: center;"><input id="chk2" name="chks" type="checkbox" checked /></td>
      <td style="text-align: center;"><input id="barcode2" name="barcode" value="AB-425" readonly class="barcodeR" style="visibility:hidden;"></td>
      <td style="text-align: center;"><input id="price2" name="price" value="450" readonly class="barcodeR" style="visibility:hidden;"></td>
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
  </tbody>
  
  </table>
</div>
<input type="button" id="append" onclick="javascript:batchOrder();"><!-- 隱藏按鈕，全部加入訂單 -->
</form>
</body>
</html>
