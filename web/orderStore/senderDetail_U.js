function editable(){ //將ox原先提供的惟讀模式改成可編輯模式
    var ids = ['q24', 'q26', 'q28', 'q30', 'q32'];
    var idm = ['s24', 's26', 's28', 's30', 's32'];
    var szs = ['3', '3', '3', '3', '3']; //var types = ['','','','','','','','','','','','','',''];
	 var oid_th = $("a[href^=\"javascript:openxava.executeAction('KaiJung', 'OrderSenderDetailOnly', '', false, 'List.orderBy', 'property=oid,collection=details')\"]");
    //alert('oid_th:'+ oid_th.text() );
    oid_th.parent().parent().remove();//移除最後的orderSender_oid欄位標題
    trs = $("tr[id^='ox_KaiJung_OrderSenderDetailOnly__xava_collectionTab_details_']"	);
    trs.each(function(sn){
        var bgcolor = 'F0F5F7';
           //將明細oid放入hidden input後移除原始的text
		  var oid = $(this).find("td:last");
		  oid_txt = oid.text().trim();
//alert('senderDetail.js: oid_txt: '+ oid_txt);
        $(this).append('<input id="senderd_oid_'+ sn + '" value="' + oid_txt + '" type="hidden"/>');//放在trs的迴圈內，只是想用trs的索引值，前後位置就不重要了。
        //alert('senderDetail.js: '+ $('#ox_KaiJung_OrderSenderDetailOnly__xava_collectionTab_details_'+ sn +' td:gt(1)' ) );
    	  if(sn%2==1){ bgcolor = 'D3DADD'; }//奇數列
          $(this).find('td').slice(4, 9).each(//訂單的尺寸數量(惟讀)
              function(i){
        	       $(this).append('<input id="'+ ids[i] +'_'+ sn + '" size="'+ szs[i] +'" readonly style="border:0;background:#'+ bgcolor +' none repeat scroll 0 0;">');
                  }
             );
//          $(this).find('td').slice(18,23).each(//本揀貨單的尺寸數量(可編輯)
//              function(i){
//          	       $(this).append('<input id="'+ idm[i] +'_'+ sn + '" size="'+ szs[i] +'"/>');
//                  }//
//             );
        oid.remove();//移除最後的orderSender_oid欄位
    }); //trs.each
     // 用來將quantity的資料從訂貨單(json)取出，置入欄位中。
//alert( 'senderDetail.js: senderId: ' + $('input[name="ox_KaiJung_OrderSenderDetailOnly__oid"]').eq(1).val() );
    var senderid = $('input[name="ox_KaiJung_OrderSenderDetailOnly__oid"]').eq(0).val();
    OrderSenderDwr.getDetailByHeadId ( senderid, function(sendD_Set){ // argument: wareId , return: sendD_Set
//alert( 'senderDetail.js: length: '+ sendD_Set.length + ' senderid: ' + senderid );
    		for (var i=0; i < sendD_Set.length; i++) {
//alert( 'senderDetail.js: quantity: '+ sendD_Set[i].expectedQty );
    			var qtyobj = jQuery.parseJSON( sendD_Set[i].expectedQty );
//alert( 'senderDetail.js: qtyobj: '+ qtyobj.s24 );
    			dwr.util.setValue( 'q24_'+ i , qtyobj.s24 );
    			dwr.util.setValue( 'q26_'+ i , qtyobj.s26 );
    			dwr.util.setValue( 'q28_'+ i , qtyobj.s28 );
    			dwr.util.setValue( 'q30_'+ i , qtyobj.s30 );
    			dwr.util.setValue( 'q32_'+ i , qtyobj.s32 );
    		}//for
     });
}
