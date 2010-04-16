function afterSave(){
    $.cookie("JSESSIONID",null);
    parent.frames["frameEast"].openxava.executeAction('KaiJung', 'OrderMarkListOnly', '', false, 'List.goPage', 'page=1');
    parent.frames["frameEast"].window.location.reload();
}
function afterDel(){
	 //alert("trying: "+ $("#ox_KaiJung_MarkDetailOnly__messages_table").length>0);
	 if ($("#ox_KaiJung_OrderMarkDetailOnly__messages_table").length>0) {
	     parent.frames["frameEast"].window.location.reload();
        $.cookie("JSESSIONID", null);
	 }else {
		     setTimeout("afterDel()", 50);
	 }
}
//function clickFilter(){//把過濾的icon放到上面
////	if( $('#ox_KaiJung_OrderMarkDetailOnly__filter_link_details').length > 0){
//	alert( $('#ox_KaiJung_OrderMarkDetailOnly__tr_list_filter_details').css('display') );
//	    if( $('#ox_KaiJung_OrderMarkDetailOnly__tr_list_filter_details').css('display') == 'none' ){
//	    	  $("ox_KaiJung_OrderMarkDetailOnly__filter_link_details").remove();
//	        $("#ox_KaiJung_OrderMarkDetailOnly__collection_details___ td").first()
//	          .append("<a id=\"ox_KaiJung_OrderMarkDetailOnly__filter_link_details\" title=\"顯示過濾器\" href=\"javascript:openxava.manageFilterRow('KaiJung', 'OrderMarkDetailOnly', 'details', 'xava_collectionTab_details')\">" +
//	          "<img id=\"ox_KaiJung_OrderMarkDetailOnly__filter_image_list\" border=\"0\" align=\"middle\" src=\"/KaiJung/xava/images/show-filter.gif\"/></a>");
//	    }else{
//	   	  $("ox_KaiJung_OrderMarkDetailOnly__filter_link_details").remove();
//	        $("#ox_KaiJung_OrderMarkDetailOnly__collection_details___ td").first()
//	          .append("<a id=\"ox_KaiJung_OrderMarkDetailOnly__filter_link_details\" title=\"隱藏過濾器\" href=\"javascript:openxava.manageFilterRow('KaiJung', 'OrderMarkDetailOnly', 'details', 'xava_collectionTab_details')\">" +
//	          "<img id=\"ox_KaiJung_OrderMarkDetailOnly__filter_image_list\" border=\"0\" align=\"middle\" src=\"/KaiJung/xava/images/hide-filter.gif\"/></a>");
//	     }
////	}
//}
function createRecord(){
//	$("tr[id^='ox_KaiJung_OrderMarkDetailOnly__xava_collectionTab_details_']");
}
function changeLink(){
    if( ($('a')==null) || ($('a').length < 1) ){
        setTimeout( 'changeLink()', 50 );  
    }else{
        $('#ox_KaiJung_OrderMarkDetailOnly__CRUD___save').attr('onclick','javascript:afterSave();');
        $('#ox_KaiJung_OrderMarkDetailOnly__CRUD___delete').attr('onclick','javascript:afterDel();');
          //強制隱藏過濾器
	    if( ! $('#ox_KaiJung_OrderMarkDetailOnly__tr_list_filter_details').css('display') == 'none' ){
	    	  $('#ox_KaiJung_OrderMarkDetailOnly__tr_list_filter_details').css('display','none');
	    }//if
     }
}
function changeCss(){
    //$(document).ready(function(){
    if( ($('#ox_KaiJung_OrderMarkDetailOnly__view')==null) || ($('#ox_KaiJung_OrderMarkDetailOnly__view').length < 1) ){
        setTimeout( 'changeCss()', 50 );  
    }else{
      	  //強制div的寬度，避免換行
        $('#ox_KaiJung_OrderMarkDetailOnly__view').css('width','800px');
          //隱藏openxava預設的pk圖示
        $("img[src$='key.gif']").each(function(){ $(this).css('visibility','hidden'); });
          //隱藏openxava預設的加號
        //$("a[href=\"javascript:openxava.executeAction('KaiJung', 'OrderMarkDetailOnly', '', false, 'Collection.new', 'viewObject=xava_view_details')\"]").css('visibility','hidden');
          //去除明細的第一欄
        $("#ox_KaiJung_OrderMarkDetailOnly__filter_link_details").parent().remove();//css('visibility','hidden');
        $("tr[id^='ox_KaiJung_OrderMarkDetailOnly__xava_collectionTab_details_']").each(function (i) {
          $(this).find('td:eq(0)').remove();
        });//css('visibility','hidden');
        $('#ox_KaiJung_OrderMarkDetailOnly__button_bar').remove();
     }
}
function copyOrder(){//新增和修改才會用到
    trs = $("tr[id^='ox_KaiJung_OrderMarkDetailOnly__xava_collectionTab_details_']"	);
    trs.each(function(sn){
//alert('markDetail.js: copyOrder: '+ $('#ox_KaiJung_OrderMarkDetailOnly__xava_collectionTab_details_'+ sn) );
        $(this).find("input[id^=\"ssz\"]").each(//本備貨單的尺寸數量(可編輯)
            function(i){
               var sval = trs.eq(sn).find('td').eq(i+6).find('input:first').val();
            	$(this).val( sval );
                }//
           );
    }); //trs.each
}
function sum2(){//新增和修改才會用到
	$("tr[id^=\"ox_KaiJung_OrderMarkDetailOnly__xava_collectionTab_details_\"]").each(function(i){
	   var sum2 = 0;
	   $("input[id^=\"ssz\"][id$=\""+ i +"\"]").each( function(){//開頭ssz是尺寸數量，i是列索引
	       sum2 += parseInt ( $(this).val(), 10 );
	    });
	   $('#sum2_'+ i).val( sum2 );
	});
}
function updateMark(){//按鈕呼叫(markLayout_R.html)
//debug = 'debug: ';
    trs = $("tr[id^='ox_KaiJung_OrderMarkDetailOnly__xava_collectionTab_details_']"	);
    var sizes = ['s24','s26','s28','s30','s32']; //尺寸數量暫定5組
    trs.each(function(sn){
   	   var quantity = '{';
		   var oid = $('#markd_oid_'+sn).val().trim(); 
         //alert('markDetail.js: '+ $('#ox_KaiJung_OrderMarkDetailOnly__xava_collectionTab_details_'+ sn +' td:gt(1)' ) );
			for(j=1;j<=sizes.length;j++)
			{
				qtyVal = 0;
				if( $('#'+ sizes[sn] +'_'+ sn) ){
					qtyVal = $('#'+ sizes[j] +'_'+ sn).val();
					if( qtyVal == '' || qtyVal == null ){
						qtyVal = '0';
					}
				}
				quantity += '\"'+ sizes[j-1] + '\":' + qtyVal + ',';
			}
			quantity = quantity.substring( 0, quantity.length-1 );//去除最後的逗號
			quantity += '}';
//debug += quantity;//會把迴圈內的所有都印出來
			OrderMarkDwr.update(oid, quantity, 'memo');
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
//	changeLink2();//將openxava預設的新增按鈕，改成新增一列
}

