
function submitPick(){
//debug = 'debug: ';
	var headId = $('#ox_KaiJung_OrderStoreHead__oid').val();
	for(i=1;i<=index;i++)
	{
//alert("orderStoreNew.js: submitOSD: row"+ i +" exist: "+ $('row'+i) + " headId: " + headId );
		if( $('row'+i) ){
			var barcode = $('#barcode'+i).val(); 
			var modifyid = $('#modifyid'+i).val(); 
			var inputObj = $('#isCustOrder'+i).checked;
			if( inputObj == true){
				var isCustOrder = 1;
			}else{
				var isCustOrder = 0;
			}
			var memo = $('#memo'+i).val();
			var quantity = '{';
			var sizes = ['s24','s26','s28','s30','s32']; //尺寸數量暫定5組
			for(j=1;j<=sizes.length;j++)
			{
				qtyVal = 0;
				if( $('#r'+j+'_'+i) ){  
					qtyVal = $('#r'+j+'_'+i).val();
					if( qtyVal == '' || qtyVal == null ){
						qtyVal = '0';
					}
				}
				quantity += '\"'+ sizes[j-1] + '\":' + qtyVal + ',';
			}
			quantity = quantity.substring( 0, quantity.length-1 );
			quantity += '}'
//debug += quantity;//會把迴圈內的所有都印出來
			OrderPickerDwr.insert(barcode, quantity, modifyid, isCustOrder, memo, headId);
		}
	}
//alert( debug );
}

function getOrderD(){ // 在初始的時侯讀入欲揀貨的訂貨明細
	OrderStoreNew.findSuggestList ( 1, function(orderSuggestD_Set){ // argument: wareId , return: orderSuggestD_Set
		//alert('orderSuggestD_Set length: '+ orderSuggestD_Set.length );
//var debug = 'orderSuggestD_Set: ';
		for (var i=0; i < orderSuggestD_Set.length; i++) {
//debug += ' , reason: ' + orderSuggestD_Set[i].reason ;
			dwr.util.setValue( 'reason_'+ (i+1) , orderSuggestD_Set[i].reason );
			dwr.util.setValue( 'articleno_'+ (i+1) , orderSuggestD_Set[i].item.articleno );
			//dwr.util.setValue( 'color_'+ (i+1) , orderSuggestD_Set[i].item.color.sName );
//debug += ' , qtyobj json: ' + jQuery.parseJSON( orderSuggestD_Set[i].suggestQty );
			var qtyobj = jQuery.parseJSON( orderSuggestD_Set[i].suggestQty );
//debug += ' , qtyobj: ' + qtyobj ;
			dwr.util.setValue( 'size24_'+ (i+1) , qtyobj.s24 );
			dwr.util.setValue( 'size26_'+ (i+1) , qtyobj.s26 );
			dwr.util.setValue( 'size28_'+ (i+1) , qtyobj.s28 );
			dwr.util.setValue( 'size30_'+ (i+1) , qtyobj.s30 );
			dwr.util.setValue( 'size32_'+ (i+1) , qtyobj.s32 );
		}//for
//alert( debug );
	});
}
