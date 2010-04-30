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
				//alert( typeof diff_set[i].quantity );
    			var orderQty0 = jQuery.parseJSON( diff_set[i].orderQty );//訂貨尺寸數量
				//alert( 'orderQty0: '+ $.dump( orderQty0 ) );
				var orderQty = eval( '('+ diff_set[i].orderQty +')' ); // 將 string 轉成 json object, 以取得 keys
				//alert( 'orderQty: '+ $.dump( orderQty ) );
				var sendQty = eval( '('+ diff_set[i].sendQty +')' ); // 將 string 轉成 json object, 以取得 keys
//alert( 'diff_set[i]: stockQty: '+ diff_set[i].stockQty +', orderQty: '+ diff_set[i].orderQty + ', sendQty: ' + diff_set[i].sendQty + ', preMarkQty: ' + diff_set[i].preMarkQty );
				var placeQty = eval( '('+ diff_set[i].placeQty +')' ); // 將 string 轉成 json object, 以取得 keys
				var markQty = eval( '('+ diff_set[i].markQty +')' ); // 將 string 轉成 json object, 以取得 keys
				var preMarkQty = eval( '('+ diff_set[i].preMarkQty +')' ); // 將 string 轉成 json object, 以取得 keys
				
				var arrSize = 0;
				for (var key in orderQty){ // 計算尺寸數量總數
	    			arrSize ++;
				}
				
				var k=0;
				for (var key in stockQty){ // 庫存尺寸數量
					k++;
	    			$('#inp_' + i + '_' + ( k + 4 ) ) //在 newRow() 23 行，動態產生的 input 已設定的 id, 5 是要跳過 尺寸數量 之前的幾個欄位
	    			  .val( eval ( 'stockQty.' + key ) );
				}
				var o=0, o_sum = 0;
				for (var key in orderQty){ // 訂貨尺寸數量
					//alert('key: '+ key);
	    			//alert( eval ( 'orderQty0.' + key ) );
					o++;
					var qt = eval ( 'orderQty.' + key );
	    			$('#inp_' + i + '_' + ( o + arrSize*2 +4 ) ) //在 newRow() 23 行，動態產生的 input 已設定的 id, ( o + arrSize*2 +4 ) 是要跳過 尺寸數量 之前的幾個欄位
	    			  .val( qt );
	    			o_sum += parseInt ( qt );
				}
    		    //訂貨數量小計
    			$('#inp_' + i + '_' + ( arrSize*3 +5 ) ).val( o_sum );
				
				var s=0, s_sum = 0;
				for (var key in sendQty){ // 出貨尺寸數量
					s++;
					var qt = eval ( 'sendQty.' + key );
	    			$('#inp_' + i + '_' + ( s + arrSize*3 +6 ) ) //在 newRow() 23 行，動態產生的 input 已設定的 id, 5 是要跳過 尺寸數量 之前的幾個欄位
	    			  .val( qt );
	    			s_sum += parseInt ( qt );
				}
    		    //出貨數量小計
    			$('#inp_' + i + '_' + ( arrSize*4 +7 ) ).val( s_sum );
    			
				var p=0, p_sum = 0;
				for (var key in placeQty){ // 庫架尺寸數量
					p++;
					var qt = eval ( 'placeQty.' + key );
	    			$('#inp_' + i + '_' + ( p + arrSize*4 +7 ) ) //在 newRow() 23 行，動態產生的 input 已設定的 id, 5 是要跳過 尺寸數量 之前的幾個欄位
	    			  .val( qt );
	    			p_sum += parseInt ( qt );
				}
    		    //庫架數量小計
    			$('#inp_' + i + '_' + ( arrSize*5 +8 ) ).val( p_sum );
    			
				var m=0, m_sum = 0;
				for (var key in markQty){ // 備貨尺寸數量
					m++;
					var qt = eval ( 'markQty.' + key );
	    			$('#inp_' + i + '_' + ( m + arrSize*5 +8 ) ) //在 newRow() 23 行，動態產生的 input 已設定的 id, 5 是要跳過 尺寸數量 之前的幾個欄位
	    			  .val( qt );
	    			m_sum += parseInt ( qt );
				}
    		    //備貨數量小計
    			$('#inp_' + i + '_' + ( arrSize*6 +9 ) ).val( m_sum );
    			
				var r=0, r_sum = 0;
				for (var key in preMarkQty){ // 待備貨尺寸數量
					r++;
					var qt = eval ( 'preMarkQty.' + key );
	    			$('#inp_' + i + '_' + ( r + arrSize*6 +9 ) ) //在 newRow() 23 行，動態產生的 input 已設定的 id, 5 是要跳過 尺寸數量 之前的幾個欄位
	    			  .val( qt );
	    			r_sum += parseInt ( qt );
				}
    		    //待備貨數量小計
    			$('#inp_' + i + '_' + ( arrSize*7 +10 ) ).val( r_sum );
    			
				
				
    			$('#inp_' + i + '_' + ( arrSize*7 +12 ) ) //在 newRow() 23 行，動態產生的 input 已設定的 id, ( arrSize*7 +11 ) 是要跳過 尺寸數量 之前的欄位
    			  .val( diff_set[i].salesRank ); // 銷售排名
				
    			$('#inp_' + i + '_' + ( arrSize*7 +13 ) ) //在 newRow() 23 行，動態產生的 input 已設定的 id, ( arrSize*7 +11 ) 是要跳過 尺寸數量 之前的欄位
    			  .val( diff_set[i].orderRank ); // 訂貨排名
				
    			//dwr.util.setValue( 'qsz24_'+ i , orderQty0.s24 );//需要知道 json 的 key 才能使用的方法，現已不用
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

