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
      	  //強制div的寬度，避免換行
        $('#ox_KaiJung_OrderPickerDetailOnly__view').css('width','800px');
          //隱藏openxava預設的pk圖示
        $("img[src$='key.gif']").each(function(){ $(this).css('visibility','hidden'); });
          //隱藏openxava預設的加號
        $("a[href=\"javascript:openxava.executeAction('KaiJung', 'OrderPickerDetailOnly', '', false, 'Collection.new', 'viewObject=xava_view_details')\"]").css('visibility','hidden');
        //隱藏openxava預設的明細第一欄
//      alert('col: '+ $("#ox_KaiJung_OrderPickerDetailOnly__filter_link_details").parent().attr('class') );
        //去除明細的第一欄
      $("#ox_KaiJung_OrderPickerDetailOnly__filter_link_details").parent().remove();//css('visibility','hidden');
      $("tr[id^='ox_KaiJung_OrderPickerDetailOnly__xava_collectionTab_details_']").each(function (i) {
     	 $(this).find('td:eq(0)').remove();
      });//css('visibility','hidden');
      $('#ox_KaiJung_OrderPickerDetailOnly__button_bar').remove();
     }
}
function copyOrder(){
    trs = $("tr[id^='ox_KaiJung_OrderPickerDetailOnly__xava_collectionTab_details_']"	);
    trs.each(function(sn){
//alert('pickerDetail.js: copyOrder: '+ $('#ox_KaiJung_OrderPickerDetailOnly__xava_collectionTab_details_'+ sn) );
        $(this).find('td').slice(18,23).each(//本揀貨單的尺寸數量(可編輯)
            function(i){
               var sval = trs.eq(sn).find('td').eq(i+4).find('input:first').val();//sn:列索引
            	$(this).find('input:first').val( sval );
                }//
           );
    }); //trs.each
}
function editable(){ //將ox原先提供的惟讀模式改成可編輯模式
    var ids = ['q24', 'q26', 'q28', 'q30', 'q32'];
    var idm = ['s24', 's26', 's28', 's30', 's32'];
    var szs = ['3', '3', '3', '3', '3']; //var types = ['','','','','','','','','','','','','',''];
	 var oid_th = $("a[href^=\"javascript:openxava.executeAction('KaiJung', 'OrderPickerDetailOnly', '', false, 'List.orderBy', 'property=oid,collection=details')\"]");
    //alert('oid_th:'+ oid_th.text() );
    oid_th.parent().parent().remove();//移除最後的orderPicker_oid欄位標題
    trs = $("tr[id^='ox_KaiJung_OrderPickerDetailOnly__xava_collectionTab_details_']"	);
    trs.each(function(sn){
        var bgcolor = 'F0F5F7';
           //將明細oid放入hidden input後移除原始的text
		  var oid = $(this).find("td:last");
		  oid_txt = oid.text().trim();
//alert('pickerDetail.js: oid_txt: '+ oid_txt);
        $(this).append('<input id="pickerd_oid_'+ sn + '" value="' + oid_txt + '" type="hidden"/>');//放在trs的迴圈內，只是想用trs的索引值，前後位置就不重要了。
        //alert('pickerDetail.js: '+ $('#ox_KaiJung_OrderPickerDetailOnly__xava_collectionTab_details_'+ sn +' td:gt(1)' ) );
    	  if(sn%2==1){ bgcolor = 'D3DADD'; }//奇數列
        $(this).find('td').slice(4, 9).each(//訂單的尺寸數量(惟讀)
            function(i){
      	       $(this).append('<input id="'+ ids[i] +'_'+ sn + '" size="'+ szs[i] +'" readonly style="border:0;background:#'+ bgcolor +' none repeat scroll 0 0;">');
                }
           );
        $(this).find('td').slice(18,23).each(//本揀貨單的尺寸數量(可編輯)
            function(i){
        	       $(this).append('<input id="'+ idm[i] +'_'+ sn + '" size="'+ szs[i] +'"/>');
                }//
           );
        oid.remove();//移除最後的orderPicker_oid欄位
    }); //trs.each
    
    pickId = $('input[name="ox_KaiJung_OrderPickerDetailOnly__oid"]').eq(1).val();
    
     // 將quantity的資料從訂貨單(json)取出，置入惟讀欄位中。
    //alert( 'pickerDetail.js: pickId: ' + $('input[name="ox_KaiJung_OrderPickerDetailOnly__oid"]').eq(1).val() );
    OrderPickerDwr.getOrderDByPick ( pickId, function(orderD_Set){ // argument: wareId , return: orderD_Set
         //alert( 'pickerDetail.js: length: '+ orderD_Set.length + ' pickId: ' + pickId );
    		for (var i=0; i < orderD_Set.length; i++) {
            //alert( 'pickerDetail.js: quantity: '+ orderD_Set[i].quantity );
    			var qtyobj = jQuery.parseJSON( orderD_Set[i].quantity );
            //alert( 'pickerDetail.js: qtyobj: '+ qtyobj.s24 );
    			dwr.util.setValue( 'q24_'+ i , qtyobj.s24 );
    			dwr.util.setValue( 'q26_'+ i , qtyobj.s26 );
    			dwr.util.setValue( 'q28_'+ i , qtyobj.s28 );
    			dwr.util.setValue( 'q30_'+ i , qtyobj.s30 );
    			dwr.util.setValue( 'q32_'+ i , qtyobj.s32 );
    		}//for
     });

     // 將quantity的資料從揀貨單(json)取出，置入可編輯欄位中。
    OrderPickerDwr.getPickerDByPick ( pickId, function(pickD_Set){ // argument: wareId , return: orderD_Set
    		for (var i=0; i < pickD_Set.length; i++) {
    			var qtyobj = jQuery.parseJSON( pickD_Set[i].quantity );
    			dwr.util.setValue( 's24_'+ i , qtyobj.s24 );
    			dwr.util.setValue( 's26_'+ i , qtyobj.s26 );
    			dwr.util.setValue( 's28_'+ i , qtyobj.s28 );
    			dwr.util.setValue( 's30_'+ i , qtyobj.s30 );
    			dwr.util.setValue( 's32_'+ i , qtyobj.s32 );
    		}//for
     });

    //顯示複製訂貨數量的checkbox
    if( $('#copy').length < 1 ){
    $('#ox_KaiJung_OrderPickerDetailOnly__collection_details___').find('tr:first td')
      .append('<input id="copy" type="checkbox" onclick="copyOrder()"> 複製訂貨數量');
      }
}
function submit (){
	//從畫面上取得揀貨單編號
	var pickId = $("input[name='ox_KaiJung_OrderPickerDetailOnly__oid']").val();
//	alert( 'pickId: '+ pickId );
	OrderPickerDwr.submit( pickId );
}
function updatePick(){
//debug = 'debug: ';
    trs = $("tr[id^='ox_KaiJung_OrderPickerDetailOnly__xava_collectionTab_details_']"	);
    var sizes = ['s24','s26','s28','s30','s32']; //尺寸數量暫定5組
    trs.each(function(sn){
   	   var quantity = '{';
		   var oid = $('#pickerd_oid_'+sn).val().trim(); 
         //alert('pickerDetail.js: '+ $('#ox_KaiJung_OrderPickerDetailOnly__xava_collectionTab_details_'+ sn +' td:gt(1)' ) );
			for(j=0;j<sizes.length;j++)
			{
				qtyVal = 0;
				if( $('#'+ sizes[sn] +'_'+ sn) ){
					qtyVal = $('#'+ sizes[j] +'_'+ sn).val();
					if( qtyVal == '' || qtyVal == null ){
						qtyVal = '0';
					}
				}
				quantity += '\"'+ sizes[j] + '\":' + qtyVal + ',';
			}
			quantity = quantity.substring( 0, quantity.length-1 );//去除最後的逗號
			quantity += '}';
//debug += quantity;//會把迴圈內的所有都印出來
			OrderPickerDwr.update(oid, quantity, 'memo');
    }); //trs.each
//alert( debug );
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

