
function editable(){ //將ox原先提供的惟讀模式改成可編輯模式
    var ids = ['q24', 'q26', 'q28', 'q30', 'q32'];
//    var idm = ['s24', 's26', 's28', 's30', 's32'];
    var szs = ['3', '3', '3', '3', '3']; //var types = ['','','','','','','','','','','','','',''];
    var bgcolor = 'F0F5F7';
	 var oid_th = $("a[href^=\"javascript:openxava.executeAction('KaiJung', 'OrderMarkDetailOnly', '', false, 'List.orderBy', 'property=oid,collection=details')\"]");
    //alert('oid_th:'+ oid_th.text() );
    oid_th.parent().parent().remove();//移除最後的orderMark_oid欄位標題
    trs = $("tr[id^='ox_KaiJung_OrderMarkDetailOnly__xava_collectionTab_details_']"	);
    trs.each(function(sn){
           //將明細oid放入hidden input後移除原始的text
		  var oid = $(this).find("td:last");
		  oid_txt = oid.text().trim();
		  //alert('markDetail_U.js: oid_txt: '+ oid_txt);
        $(this).append('<input id="markd_oid_'+ sn + '" value="' + oid_txt + '" type="hidden"/>');//放在trs的迴圈內，只是想用trs的索引值，前後位置就不重要了。
        //alert('markDetail.js: '+ $('#ox_KaiJung_OrderMarkDetailOnly__xava_collectionTab_details_'+ sn +' td:gt(1)' ) );
    	  if(sn%2==1){ bgcolor = 'D3DADD'; }//奇數列
        $(this).find('td').slice(4, 9).each(//訂單的尺寸數量(惟讀)
            function(i){
      	       $(this).append('<input id="'+ ids[i] +'_'+ sn + '" size="'+ szs[i] +'" readonly style="border:0;background:#'+ bgcolor +' none repeat scroll 0 0;">');
                }
           );
//        $(this).find('td').slice(19,24).each(//本備貨單的尺寸數量(可編輯)
//            function(i){
//        	       $(this).append('<input id="'+ idm[i] +'_'+ sn + '" size="'+ szs[i] +'"/>');
//                }//
//           );
        oid.remove();//移除最後的orderMark_oid欄位
    }); //trs.each
     // 用來將quantity的資料從訂貨單(json)取出，置入欄位中。
//alert( 'markDetail.js: markId: ' + $('input[name="ox_KaiJung_OrderMarkDetailOnly__oid"]').eq(1).val() );
    markId = $('input[name="ox_KaiJung_OrderMarkDetailOnly__oid"]').eq(1).val();
    OrderMarkDwr.getMarksById ( markId, function(markD_Set){ // argument: wareId , return: markD_Set
//alert( 'markDetail.js: length: '+ markD_Set.length + ' markId: ' + markId );
    		for (var i=0; i < markD_Set.length; i++) {
//alert( 'markDetail.js: quantity: '+ markD_Set[i].quantity );
    			var qtyobj = jQuery.parseJSON( markD_Set[i].presetQty );
//alert( 'markDetail.js: qtyobj: '+ qtyobj.s24 );
    			dwr.util.setValue( 'q24_'+ i , qtyobj.s24 );
    			dwr.util.setValue( 'q26_'+ i , qtyobj.s26 );
    			dwr.util.setValue( 'q28_'+ i , qtyobj.s28 );
    			dwr.util.setValue( 'q30_'+ i , qtyobj.s30 );
    			dwr.util.setValue( 'q32_'+ i , qtyobj.s32 );
    		}//for
     });
     //顯示複製進貨數量的checkbox
    if( $('#copy').length < 1 ){
    $('#ox_KaiJung_OrderMarkDetailOnly__collection_details___').find('tr:first td')
      .append('<input id="copy" type="checkbox" onclick="copyOrder()"> 複製進貨數量');
      }
}

