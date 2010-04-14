function editable(){ //將ox原先提供的惟讀模式改成可編輯模式
    var ids = ['qware', 'qshelf', 'qx', 'qy'];
    var idm = ['sware', 'sshelf', 'sx', 'sy', 'smemo', 'sgo'];
    var szs = ['8', '6', '3', '3', '12', '0']; 
    var types = ['','','','','','type="checkbox"'];
	 var oid_th = $("a[href^=\"javascript:openxava.executeAction('KaiJung', 'OrderPlaceDetailOnly', '', false, 'List.orderBy', 'property=oid,collection=details')\"]");
    //alert('oid_th:'+ oid_th.text() );
    oid_th.parent().parent().remove();//移除最後的orderPlace_oid欄位標題
    trs = $("tr[id^='ox_KaiJung_OrderPlaceDetailOnly__xava_collectionTab_details_']"	);
    trs.each(function(sn){
        var bgcolor = 'F0F5F7';
           //將明細oid放入hidden input後移除原始的text
		  var oid = $(this).find("td:last");
		  oid_txt = oid.text().trim();
//alert('placeDetail.js: oid_txt: '+ oid_txt);
        $(this).append('<input id="placed_oid_'+ sn + '" value="' + oid_txt + '" type="hidden"/>');//放在trs的迴圈內，只是想用trs的索引值，前後位置就不重要了。
        //alert('placeDetail.js: '+ $('#ox_KaiJung_OrderPlaceDetailOnly__xava_collectionTab_details_'+ sn +' td:gt(1)' ) );
    	  if(sn%2==1){ bgcolor = 'D3DADD'; }//奇數列
        $(this).find('td').slice(11, 15).each(//訂單的尺寸數量(惟讀)
            function(i){
         	    var data = $.trim(this.childNodes[0].data);
                if(!data){ data = ''; }
  	             //$(this).find(':first-child').remove();
  	             this.removeChild(this.childNodes[0]);//移除原來的label資料
      	       $(this).append('<input id="'+ ids[i] +'_'+ sn + '" value="' + data +'" size="'+ szs[i] +'" readonly style="border:0;background:#'+ bgcolor +' none repeat scroll 0 0;">');
                }
           );
        $(this).find('td').slice(22,28).each(//本上架單的尺寸數量(可編輯)
            function(i){
 	             this.removeChild(this.childNodes[0]);//移除原來的label資料
        	       $(this).append('<input id="'+ idm[i] +'_'+ sn + '" '+ types[i] +' size="'+ szs[i] +'"/>');
                }//
           );
        oid.remove();//移除最後的orderPlace_oid欄位
    }); //trs.each
     // 用來將quantity的資料從訂貨單(json)取出，置入欄位中。
//alert( 'placeDetail.js: placeId: ' + $('input[name="ox_KaiJung_OrderPlaceDetailOnly__oid"]').eq(1).val() );
    var placeId = $('input[name="ox_KaiJung_OrderPlaceDetailOnly__oid"]').eq(1).val();
    OrderPlaceDwr.getStocksByPlaceId ( placeId, function(orderD_Set){ // argument: wareId , return: orderD_Set
//alert( 'placeDetail.js: length: '+ orderD_Set.length + ' placeId: ' + placeId );
    		for (var i=0; i < orderD_Set.length; i++) {
//alert( 'placeDetail.js: quantity: '+ orderD_Set[i].quantity );
    			var qtyobj = jQuery.parseJSON( orderD_Set[i].quantity );
//alert( 'placeDetail.js: qtyobj: '+ qtyobj.s24 );
    			dwr.util.setValue( 'q24_'+ i , qtyobj.s24 );
    			dwr.util.setValue( 'q26_'+ i , qtyobj.s26 );
    			dwr.util.setValue( 'q28_'+ i , qtyobj.s28 );
    			dwr.util.setValue( 'q30_'+ i , qtyobj.s30 );
    			dwr.util.setValue( 'q32_'+ i , qtyobj.s32 );
    		}//for
     });
}
