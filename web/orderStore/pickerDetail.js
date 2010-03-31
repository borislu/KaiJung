function afterSave(){
    $.cookie("JSESSIONID",null);
    parent.frames["frameEast"].openxava.executeAction('KaiJung', 'OrderPickerListOnly', '', false, 'List.goPage', 'page=1');
    parent.frames["frameEast"].window.location.reload();
}
function afterDel(){
	 //alert("trying: "+ $("#ox_KaiJung_PickerDetailOnly__messages_table").length>0);
	 if ($("#ox_KaiJung_OrderPickerDetailOnly__messages_table").length>0) {
	     parent.frames["frameEast"].window.location.reload();
        $.cookie("JSESSIONID", null);
	 }else {
		     setTimeout("afterDel()", 50);
	 }
}
function changeLink(){
    if( ($('a')==null) || ($('a').length < 1) ){
        setTimeout( 'changeLink()', 50 );  
    }else{
        $('#ox_KaiJung_OrderPickerDetailOnly__CRUD___save').attr('onclick','javascript:afterSave();');
        $('#ox_KaiJung_OrderPickerDetailOnly__CRUD___delete').attr('onclick','javascript:afterDel();');
     }
}
function changeCss(){
    //$(document).ready(function(){
    if( ($('#ox_KaiJung_OrderPickerDetailOnly__view')==null) || ($('#ox_KaiJung_OrderPickerDetailOnly__view').length < 1) ){
        setTimeout( 'changeCss()', 50 );  
    }else{
        $('#ox_KaiJung_OrderPickerDetailOnly__view').css('width','800px');
        $("img[src$='key.gif']").each(function(){ $(this).css('visibility','hidden'); });
     }
}

function editable(){ //將ox原先提供的惟讀模式改成可編輯模式
    var ids = ['s24', 's26', 's28', 's30', 's32'];
    var idm = ['m24', 'm26', 'm28', 'm30', 'm32'];
    var szs = ['3', '3', '3', '3', '3']; //var types = ['','','','','','','','','','','','','',''];
    var bgcolor = 'F0F5F7';
    trs = $("tr[id^='ox_KaiJung_OrderPickerDetailOnly__xava_collectionTab_details_']"	);
    trs.each(function(sn){
        //alert('module_pickerDetail.jsp: '+ $('#ox_KaiJung_OrderPickerDetailOnly__xava_collectionTab_details_'+ sn +' td:gt(1)' ) );
    	  if(sn%2==1){ bgcolor = 'D3DADD'; }//奇數列
        $(this).find('td').slice(5, 10).each(//訂單的尺寸數量(惟讀)
            function(i){
      	       $(this).append('<input id="'+ ids[i] +'_'+ sn + '" size="'+ szs[i] +'" readonly style="border:0;background:#'+ bgcolor +' none repeat scroll 0 0;">');
                 }
           );
        $(this).find('td').slice(19,24).each(//本揀貨單的尺寸數量(可編輯)
                function(i){
          	       $(this).append('<input id="'+ idm[i] +'_'+ sn + '" size="'+ szs[i] +'"/>');
                     }//
               );
    }); //trs.each
     // 用來將quantity的資料從訂貨單(json)取出，置入欄位中。
alert( 'pickerDetail.js: pickId: ' + $('input[name="ox_KaiJung_OrderPickerDetailOnly__oid"]').eq(1).val() );
    pickId = $('input[name="ox_KaiJung_OrderPickerDetailOnly__oid"]').eq(1).val();
    OrderPickerDwr.getOrderDByPick ( pickId, function(orderD_Set){ // argument: wareId , return: orderD_Set
//alert( 'pickerDetail.js: length: '+ orderD_Set.length + ' pickId: ' + pickId );
    		for (var i=0; i < orderD_Set.length; i++) {
//alert( 'pickerDetail.js: quantity: '+ orderD_Set[i].quantity );
    			var qtyobj = jQuery.parseJSON( orderD_Set[i].quantity );
//alert( 'pickerDetail.js: qtyobj: '+ qtyobj.s24 );
    			dwr.util.setValue( 's24_'+ i , qtyobj.s24 );
    			dwr.util.setValue( 's26_'+ i , qtyobj.s26 );
    			dwr.util.setValue( 's28_'+ i , qtyobj.s28 );
    			dwr.util.setValue( 's30_'+ i , qtyobj.s30 );
    			dwr.util.setValue( 's32_'+ i , qtyobj.s32 );
    		}//for
     });
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
	changeCss();
	changeLink();
	editable();
}

