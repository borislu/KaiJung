function dumpInputs(){//列印本頁的所有 input elements, 用來 Debug
	inputDebug = 'orderStoreDetail.js: editable: inputs: ';
	$('input').each(
			function(){
				inputDebug += ' , '+ $(this).attr('id') +': '+ $(this).val();
			}
	);
	alert( inputDebug );
}
function afterSave(){
	$.cookie("JSESSIONID",null);
	parent.frames["frameEast"].openxava.executeAction('KaiJung', 'OrderStoreListOnly', '', false, 'List.goPage', 'page=1');
	parent.frames["frameEast"].window.location.reload();
}
function afterDel() {
	if ($("#ox_KaiJung_OrderStoreDetailOnly__messages_table").length > 0) {
		parent.frames["frameEast"].window.location.reload();
		$.cookie("JSESSIONID", null);
	} else {
		setTimeout("afterDel()", 50);
	}
}
function editable(){ //將ox原先提供的惟讀模式改成可編輯模式
	 var oid_txt;
    trs = $("tr[id^='ox_KaiJung_OrderStoreDetailOnly__xava_collectionTab_details_']"	);
	 var oid_th = $("a[href^=\"javascript:openxava.executeAction('KaiJung', 'OrderStoreDetailOnly', '', false, 'List.orderBy', 'property=oid,collection=details')\"]");
//alert('oid_th:'+ oid_th.text() );
    oid_th.parent().parent().remove();//移除最後的orderStore_oid欄位標題
    trs.each(function(sn){
        //alert('module_orderStoreDetail.jsp: '+ $('#ox_KaiJung_OrderStoreDetailOnly__xava_collectionTab_details_'+ sn +' td:gt(1)' ) );
        var ids = ['articleno', 'price', 'color', 's24', 's26', 's28', 's30', 's32', 'sum', 'amount', 'isCustOrder', 'modifyid', 'memo', 'status', 'oid'];
        var szs = ['10', '5', '3', '3', '3', '3', '3', '3', '6', '8', '3', '6', '28', '8', '0'];
        //var types = ['','','','','','','','','','','','','',''];
          //將明細oid放入hidden input後移除原始的text
   	  var oid = $(this).find("td:last");
   	  oid_txt = oid.text().trim();
        $(this).append('<input id="orderd_oid'+ '_'+ sn + '" value="' + oid_txt + '" type="hidden"/>');
        $(this).find('td:gt(1)').each(
            function(i){
         	    var data = $.trim(this.childNodes[0].data);
                if(!data){ data = ''; }
  	             //$(this).find(':first-child').remove();
  	             this.removeChild(this.childNodes[0]);//移除原來的label資料
      	       $(this).append('<input id="'+ ids[i] +'_'+ sn + '" value="' + data +'" size="'+ szs[i] +'"/>');
                 }//
           );
        oid.remove();//移除最後的orderStore_oid欄位
    }); //trs.each
//alert( 'orderStoreDetail.js: oid: '+ oid_txt );
     // 用來將quantity的資料從json取出，置入欄位中。 
     OrderStoreNew.getDetailSet ( oid_txt, function(orderStoreD_Set){ // argument: wareId , return: orderStoreD_Set
//alert( 'orderStoreDetail.js: length: '+ orderStoreD_Set.length );
    		for (var i=0; i < orderStoreD_Set.length; i++) {
//alert( 'orderStoreDetail.js: quantity: '+ orderStoreD_Set[i].quantity );
    			var qtyobj = jQuery.parseJSON( orderStoreD_Set[i].quantity );
//alert( 'orderStoreDetail.js: qtyobj: '+ qtyobj.s24 );
    			dwr.util.setValue( 's24_'+ i , qtyobj.s24 );
    			dwr.util.setValue( 's26_'+ i , qtyobj.s26 );
    			dwr.util.setValue( 's28_'+ i , qtyobj.s28 );
    			dwr.util.setValue( 's30_'+ i , qtyobj.s30 );
    			dwr.util.setValue( 's32_'+ i , qtyobj.s32 );
    		}//for
     });
}
function changeLink(){
 //$(document).ready(function(){
 ///*
 if( ($('a')==null) || ($('a').length < 1) ){
     setTimeout( 'changeLink()', 50 );  
 }else{
 //*/
     $('#ox_KaiJung_OrderStoreDetailOnly__CRUD___save').attr('onclick','javascript:afterSave();');
     $('#ox_KaiJung_OrderStoreDetailOnly__CRUD___delete').attr('onclick','javascript:afterDel();');
 }
}
function changeCss(){
 //$(document).ready(function(){
 if( ($('#ox_KaiJung_OrderStoreDetailOnly__view')==null) || ($('#ox_KaiJung_OrderStoreDetailOnly__view').length < 1) ){
     setTimeout( 'changeCss()', 50 );  
 }else{
     $('#ox_KaiJung_OrderStoreDetailOnly__view').css('width','800px');
 }
}

function updateOSD(){
	var result ;
//debug = 'debug: ';
		var trs = $('tr[id ^= "ox_KaiJung_OrderStoreDetailOnly__xava_collectionTab_details_"]');
//alert("module_orderStoreDetail.jsp: updateOSD: trs: "+ trs.length );
//debug += ", trs: "+ trs.length ;
		for(i=0;i<trs.length;i++)
		{
				var oid = $('#orderd_oid_'+i).val().trim(); 
//alert("module_orderStoreDetail.jsp: updateOSD: oid: "+ oid );
//alert("module_orderStoreDetail.jsp: updateOSD: modifyid: "+ $('#modifyid_'+i) );
//debug += ", modifyid: "+ $('#modifyid_'+i);
				var modifyid = $('#modifyid_'+i).val(); 
				var inputObj = $('#isCustOrder_'+i).checked;
				if( inputObj == true){
					var isCustOrder = 1;
				}else{
					var isCustOrder = 0;
				}
				var memo = $('#memo_'+i).val();
				var quantity = '{';
				var sizes = ['s24','s26','s28','s30','s32']; //尺寸數量暫定5組
//alert("module_orderStoreDetail.jsp: updateOSD: size: "+ i +": " + ('#'+ sizes[0] +'_'+ i ) );
//debug += ", size: "+ i +": " + ('#'+ sizes[0] +'_'+ i ) ;
				for(j=0;j<sizes.length;j++)
				{
					//qtyVal = 0;
					//if( $('#'+ sizes[j] +'_'+ i) ){  
						qtyVal = $('#'+ sizes[j] +'_'+ i ).val();
					//}
					quantity += '\"'+ sizes[j] + '\":' + qtyVal + ',';
				}
				quantity = quantity.substring( 0, quantity.length-1 ); //刪最後一個逗號
				quantity += '}';
//debug += quantity;//會把迴圈內的所有都印出來
				OrderStoreNew.update(oid, quantity, modifyid, isCustOrder, memo);
		}//for
//alert( debug );
    return 1;
}

openxava.refreshPage = function(result) { // override OpenXava
	var changed = "";	
	if (result.error != null) {		
		openxava.systemError(result);
		changed = " ERROR";
		return;
	}
	if (result.reload) {
		window.location.reload();
		return;
	}		
	if (result.forwardURL != null) {
		if (result.forwardInNewWindow) { 
			window.open(result.forwardURL);
			var form = openxava.getForm(result.application, result.module);
			if (form != null) { 
				form[openxava.decorateId(result.application, result.module, "xava_action")].value="";	
				form[openxava.decorateId(result.application, result.module, "xava_action_argv")].value="";
				form[openxava.decorateId(result.application, result.module, "xava_changed_property")].value="";
				form[openxava.decorateId(result.application, result.module, "xava_action_range")].value="";
			}
			window.location.reload();	
			return; 			
		}
		else {
			location.href=result.forwardURL;			
		}
	}
	else if (result.nextModule != null) {	
		openxava.updateRootIds(result.application, result.module, result.nextModule);
		document.getElementById("xava_last_module_change").value=result.module + "::" + result.nextModule;
		openxava.resetRequesting(result); 
		openxava.ajaxRequest(result.application, result.nextModule); 
		return;
	}	
	else {		
		if (result.showDialog){	
			openxava.disableElements(result);
		}
		else if (result.hideDialog) {
			var dialog = openxava.getDialog(result.application, result.module); 
			dialog.attr("application", ""); 
			dialog.attr("module", ""); 
			dialog.dialog('close');
		}
		openxava.dialogLevel = result.dialogLevel;
		var dialog;
		if (result.showDialog) {
			dialog = openxava.getDialog(result.application, result.module);
		}
		openxava.strokeActions = result.strokeActions;
		var changedParts = result.changedParts;
		for (var id in changedParts) {
			changed = changed + id + ", ";  			
			try {
				$("#" + id).html(changedParts[id]);
			}
			catch (ex) {
				changed = changed + " ERROR";
				alert("Error refreshing part: " + id);
				errors = true;
				break;
			}			
		}
		if (openxava.initTheme != null) openxava.initTheme();
		if (result.focusPropertyId != null) { 
			openxava.getElementById(result.application, result.module, "xava_focus_property_id").value = result.focusPropertyId;
			openxava.setFocus(result.application, result.module);		
		}
		if (result.showDialog){	
			dialog.attr("application", result.application); 
			dialog.attr("module", result.module); 
			dialog.dialog('option', 'title', result.dialogTitle);
			dialog.dialog('option', 'width', "auto"); // Because a bug of jQuery UI 1.7.2 + IE7
			dialog.dialog('open');
			dialog.dialog('option', 'width', dialog.parent().width()); // Because a bug of jQuery UI 1.7.2 + IE7
		}
			
	}		
	document.getElementById('xava_processing_layer').style.display='none';
	var form = openxava.getForm(result.application, result.module);	
	if (form != null) {  
		form[openxava.decorateId(result.application, result.module, "xava_action")].value=""; 
		form[openxava.decorateId(result.application, result.module, "xava_action_argv")].value="";
		form[openxava.decorateId(result.application, result.module, "xava_changed_property")].value="";
		form[openxava.decorateId(result.application, result.module, "xava_action_range")].value="";		
	}	
	openxava.getElementById(result.application, result.module, "loaded_parts").value=changed;
	openxava.getElementById(result.application, result.module, "loading").value=false;
	openxava.getElementById(result.application, result.module, "view_member").value=result.viewMember; 
	openxava.lastApplication=result.application;
	openxava.lastModule=result.module;
	openxava.hasOnSelectAll(result.application, result.module);
	openxava.showMessages(result); 
	openxava.resetRequesting(result); 
	document.body.style.cursor='auto';
    /* Author: Boris
     * OrderListOnly 清單頁用來更新明細的連結
      */
	editable();
	changeCss();
	changeLink();
}

