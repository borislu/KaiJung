
function editable(){ //將ox原先提供的惟讀模式改成可編輯模式
    var ids = ['batch','cart','article','color','price','qsz24', 'qsz26', 'qsz28', 'qsz30', 'qsz32', 'sum', 'priority', 'ssz24', 'ssz26', 'ssz28', 'ssz30', 'ssz32', 'sum2', 'creater', 'ware', 'shelf', 'x', 'y', 'createTime', 'memo', 'status'];
    var szs = ['8','8','8','3','5','3', '3', '3', '3', '3', '6', '3', '3', '3', '3', '3', '3', '6', '8', '8', '8', '4', '4', '10', '20', '8']; 
    var types = ['readonly','readonly','readonly','readonly','readonly','readonly','readonly','readonly','readonly','readonly','readonly','readonly','','','','','','readonly','readonly','readonly','readonly','readonly','readonly','readonly','','readonly'];
    var bgcolor = 'F0F5F7';//偶數列底色
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
    	  if(sn%2==1){ bgcolor = 'D3DADD'; }//奇數列底色
        $(this).find('td:gt(0)').each(//備貨單的欄位
            function(i){
            	 var celColor;
        	       var data = $.trim(this.childNodes[0].data);
                if(!data){ data = ''; }
  	             //$(this).find(':first-child').remove();
  	             this.removeChild(this.childNodes[0]);//移除原來的label資料
  	             if( !( types[i] == 'readonly')){ celColor = 'FFFFFF'; }else{ celColor = bgcolor; }
      	       $(this).append('<input id="'+ ids[i] +'_'+ sn + '" value="' + data +'" size="'+ szs[i] +'" '+ types[i] +'  style="border:0;background:#'+ celColor +' none repeat scroll 0 0;">');
                }
           );
//        $(this).find('td').slice(19,24).each(//本備貨單的尺寸數量(可編輯)
//            function(i){
//        	       $(this).append('<input id="'+ idm[i] +'_'+ sn + '" size="'+ szs[i] +'"/>');
//                }//
//           );
        oid.remove();//移除最後的orderMark_oid欄位
    }); //trs.each
     // 用來將quantity的資料從備貨單(json)取出，置入欄位中。
//alert( 'markDetail.js: markId: ' + $('input[name="ox_KaiJung_OrderMarkDetailOnly__oid"]').eq(1).val() );
    markId = $('input[name="ox_KaiJung_OrderMarkDetailOnly__oid"]').eq(1).val();
    OrderMarkDwr.getMarksById ( markId, function(markD_Set){ // argument: wareId , return: markD_Set
//alert( 'markDetail.js: length: '+ markD_Set.length + ' markId: ' + markId );
    		for (var i=0; i < markD_Set.length; i++) {//迴圈數=tr的個數
//alert( 'markDetail.js: quantity: '+ markD_Set[i].quantity );
    			var qtyobj = jQuery.parseJSON( markD_Set[i].presetQty );
//alert( 'markDetail.js: qtyobj: '+ qtyobj.s24 );
    			dwr.util.setValue( 'qsz24_'+ i , qtyobj.s24 );
    			dwr.util.setValue( 'qsz26_'+ i , qtyobj.s26 );
    			dwr.util.setValue( 'qsz28_'+ i , qtyobj.s28 );
    			dwr.util.setValue( 'qsz30_'+ i , qtyobj.s30 );
    			dwr.util.setValue( 'qsz32_'+ i , qtyobj.s32 );
    		   var sum = 0;
    		   $("input[id^=\"qsz\"][id$=\""+ i +"\"]").each( function(){//開頭qsz是尺寸數量，i是列索引
//    		    	 alert( 'sum: '+ sum );
    		       sum += parseInt ( $(this).val(), 10 );
    		    });
    		   $('#sum_'+ i).val( sum );
    		}//for
     });
     //小計
     //顯示複製進貨數量的checkbox
    if( $('#copy').length < 1 ){
        $('#ox_KaiJung_OrderMarkDetailOnly__collection_details___').find('tr:first td')
          .append('<input id="copy" type="checkbox" onclick="copyOrder()"> 複製進貨數量');
      }
}

