//Last Coding By Jason
function submitOSD(){
//debug = 'debug: ';
	var headId = $("input[name='ox_KaiJung_OrderStoreHead__oid']").val();
	for(i=1;i<=index;i++)
	{
//alert("orderStoreNew.js: submitOSD: row"+ i +" exist: "+ $('row'+i) + " headId: " + headId );
		if( $('row'+i) ){
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
				}
				quantity += '\"'+ sizes[j-1] + '\":' + qtyVal + ',';
			}
			quantity = quantity.substring( 0, quantity.length-1 );
			quantity += '}'
//debug += quantity;//會把迴圈內的所有都印出來
			OrderStoreNew.insert(quantity, modifyid, isCustOrder, memo, headId);
		}
	}
//alert( debug );
}

function autoExport(){
alert("OrderStoreNew.js: autoExport: index="+index);
	for(z=1;z<=index;z++){
//        alert( "barcode"+ z + ': '+ $( ('#barcode'+z) ) );
        try{
			if( document.getElementById(('barcode'+z)) != null || $(('#barcode'+z)) != 'undefined' ){
				barcode = document.getElementById(('barcode'+z)).value;
//				alert("barcode="+barcode);
				OrderStoreNew.autoExport(barcode,function(item){
					y=z-1;
					
//					alert(item.articleno + " , " + item.price);
//					alert('articleno: '+ y + ": "+ document.getElementById(('articleno'+y)));
					$(('#articleno'+y)).value = item.articleno;
					$(('#price'+y)).value = item.price;
//					alert("price="+$('#price'+y).value);
//					$('#color'+y).value = item.colorId;
//					alert("color="+document.getElementById('color'+y).value);
//					OrderStoreNew.getColorName(document.getElementById('color'+y).value,function(colorName){
//						document.getElementById('color'+y).value = colorName;
//						alert("colorName="+document.getElementById('color'+y).value);
//					});
				});
			}
		}catch(err){
		
		}
	}
}

function beginLoad(){ //orderStoreSuggest.jsp 在初始的時侯讀入建議訂單 
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
		}
//alert( debug );
	});
}

function getOrderD( headId ){ //module_orderStoreDetail.jsp 讀入所屬的訂單明細 
	OrderStoreNew.getOrderD ( 1, function(orderStoreD_Set){ // argument: wareId , return: orderStoreD_Set
		//alert('orderStoreD_Set length: '+ orderStoreD_Set.length );
//var debug = 'orderStoreD_Set: ';
		for (var i=0; i < orderStoreD_Set.length; i++) {
//debug += ' , reason: ' + orderStoreD_Set[i].reason ;
			dwr.util.setValue( 'articleno_'+ (i+1) , orderStoreD_Set[i].item.articleno );
			//dwr.util.setValue( 'color_'+ (i+1) , orderStoreD_Set[i].item.color.sName );
//debug += ' , qtyobj json: ' + jQuery.parseJSON( orderStoreD_Set[i].suggestQty );
			var qtyobj = jQuery.parseJSON( orderStoreD_Set[i].quantity );
//debug += ' , qtyobj: ' + qtyobj ;
			dwr.util.setValue( 'size24_'+ (i+1) , qtyobj.s24 );
			dwr.util.setValue( 'size26_'+ (i+1) , qtyobj.s26 );
			dwr.util.setValue( 'size28_'+ (i+1) , qtyobj.s28 );
			dwr.util.setValue( 'size30_'+ (i+1) , qtyobj.s30 );
			dwr.util.setValue( 'size32_'+ (i+1) , qtyobj.s32 );
		}
//alert( debug );
	});
}
