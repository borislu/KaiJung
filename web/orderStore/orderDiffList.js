var len;//總欄數
var rowIndex = 0;
function changeLink(){        	    //alert('module_markList.jsp: after changeLink');  
  if( $('a').length < 1 ){
      setTimeout( 'changeLink()', 50 );
  }else{
	  $("a[href^=\"javascript:openxava.executeAction('KaiJung', 'OrderDiffListOnly', '', false, 'List.viewDetail'\"]").each(
    	    function() {
        	    this.href="#" ;
        	    $(this).find('img').attr('src','/KaiJung/xava/images/edit_none.gif');
        	 }//function
	  );
	  
	  len = $('#ox_KaiJung_OrderDiffListOnly__list>tbody>tr:first>th').length ;//標題欄數
	  
	  $('#nodata').remove(); // 移除「目前沒有資料」
  }//else
}

function newRow (){
	$('#ox_KaiJung_OrderDiffListOnly__list>tbody').append("<tr id='tr_"+ rowIndex +"' class='portlet-section-body results-row null'>");
	for( i=0; i<len; i++ ){
		$('#tr_' + rowIndex).append("<td id='td_"+ i +"' class='liferay-xava-cell-wrapper' style='vertical-align: middle;'><input id='inp_"+ rowIndex +"_"+ i +"' size='3' readonly>");
	}
	rowIndex ++;
}

function load(){//讀取訂單和對應的統計資料
//    OrderStoreNew.selectAllDetails ( 'none', function(diff_set){ 
    OrderDiffDwr.getOrderDiff ( '100', function(diff_set){ 
    		for (var i=0; i < diff_set.length; i++) {//迴圈數=tr的個數
				newRow();//填入資料前，新增一列空白列
				
    			$('#inp_' + i + '_2' ).val( diff_set[i].wareName ); //專櫃
    			$('#inp_' + i + '_3' ).val( diff_set[i].articleno ); //貨號
    			$('#inp_' + i + '_4' ).val( diff_set[i].colorName ); //顏色
    			
    			var stockQty = eval( '('+ diff_set[i].stockQty +')' ); // 將 string 轉成 json object, 以取得 keys
//alert( 'diff_set[i].constructor: '+ diff_set[i].constructor );
				//alert( 'diff_set[i]: '+ diff_set[i].quantity );
				//alert( typeof diff_set[i].quantity );
    			var orderQty0 = jQuery.parseJSON( diff_set[i].orderQty );//訂貨尺寸數量
				//alert( 'orderQty0: '+ $.dump( orderQty0 ) );
				var orderQty = eval( '('+ diff_set[i].orderQty +')' ); // 將 string 轉成 json object, 以取得 keys
				//alert( 'orderQty: '+ $.dump( orderQty ) );
				
				var arrSize = 0;
				for (var key in orderQty){ // 計算尺寸數量總數
	    			arrSize ++;
				}
				for (k=0; k<arrSize; k++){ // 庫存尺寸數量
	    			$('#inp_' + i + '_' + ( k + 5 ) ) //在 newRow() 23 行，動態產生的 input 已設定的 id, 5 是要跳過 尺寸數量 之前的幾個欄位
	    			  .val( eval ( 'stockQty.' + key ) );
				}
				for (o=0; o<arrSize; o++){ // 訂貨尺寸數量
					//alert('key: '+ key);
	    			//alert( eval ( 'orderQty0.' + key ) );
	    			$('#inp_' + i + '_' + ( o + arrSize*2 +5 ) ) //在 newRow() 23 行，動態產生的 input 已設定的 id, 5 是要跳過 尺寸數量 之前的幾個欄位
	    			  .val( eval ( 'orderQty0.' + key ) );
				}
    			//dwr.util.setValue( 'qsz24_'+ i , orderQty0.s24 );//需要知道 json 的 key 才能使用的方法，現已不用
    		    //小計
    		   var sum = 0;
    		   $("input[id^=\"qsz\"][id$=\""+ i +"\"]").each( function(){//開頭qsz是庫位尺寸及數量，i是列索引
    		       sum += parseInt ( $(this).val(), 10 );
    		    });
    		   $('#sum_'+ i).val( sum );
    		}//for
    });
}

openxava.refreshPage = function(result) {
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
	changeLink();
	load();
}

