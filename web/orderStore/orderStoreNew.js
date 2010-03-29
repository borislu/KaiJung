//Last Coding By Jason
function submitOSD(){
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
			OrderStoreNew.insert(barcode, quantity, modifyid, isCustOrder, memo, headId);
		}
	}
//alert( debug );
}

function getByBarcode(el){
//alert("OrderStoreNew.js: getByBarcode: el="+ el.id);
    sn = el.id.substring( 7 );
    var barcode = $( '#barcode'+sn );
    if( barcode ){
        barcode_val = barcode.val();
//alert("barcode="+ barcode_val);
				OrderStoreNew.getByBarcode ( barcode_val, function(item){
//alert( "OrderStoreNew.js: getByBarcode: articleno: "+ item.articleno + " , price: " + item.price);
					dwr.util.setValue( 'articleno'+ sn , item.articleno );
					dwr.util.setValue( 'price'+ sn , item.price );
					dwr.util.setValue( 'color'+ sn , item.color.sName );
				});
    }
}
function getByArticleno(el){
//alert("OrderStoreNew.js: getByArticleno: el="+ el.id );
    sn = el.id.substring( 9 );
    var articleno = $( '#articleno'+sn );
    if( articleno ){
	        articleno_val = articleno.val();
//alert("articleno="+ articleno_val);
		     OrderStoreNew.getByArticleno ( articleno_val, function(item){
//alert( "OrderStoreNew.js: getByArticleno: articleno: "+ item.articleno + " , price: " + item.price);
				dwr.util.setValue( 'barcode'+ sn , item.barcode );
				dwr.util.setValue( 'price'+ sn , item.price );
				dwr.util.setValue( 'color'+ sn , item.color.sName );
			});
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
		}//for
//alert( debug );
	});
}
/*
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
*/
