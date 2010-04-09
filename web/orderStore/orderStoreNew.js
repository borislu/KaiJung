//Last Coding By Jason
function insert(){//寫入明細檔，配合表頭的部份用openxava的功能寫入
//debug = 'orderStoreNew.js insert: debug: ';
	var headId = $('#ox_KaiJung_OrderStoreHead__oid').val();
	for(i=1;i<=index;i++)
	{
//alert("orderStoreNew.js: insert: row"+ i +" exist: "+ $('row'+i) + " headId: " + headId );
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
//debug += quantity;//會把迴圈內的所有都印出來 $("select[@name='option_layout']")
			OrderStoreNew.deleteDetails( headId);
			OrderStoreNew.insert( barcode, quantity, modifyid, isCustOrder, memo, headId);//寫入OrderStoreD
		}
	}
//alert( debug );
}
function insert2(){//寫入 OrderStore 和 OrderStoreD
	var headId = $('#ox_KaiJung_OrderStoreHead__oid').val();
	var warehouseId = $('#ox_KaiJung_OrderStoreHead__reference_editor_warehouse')
	    .find('select[name="ox_KaiJung_OrderStoreHead__warehouse___oid"]').val(); 
	//alert('orderStoreNew.js insert2: warehouse: '+ warehouseId);
	OrderStoreNew.insert2(headId, warehouseId);//寫入OrderStore
	insert();//寫入OrderStoreD
}

function submit (){
	//從畫面上取得訂貨單編號
	var headId = $('#ox_KaiJung_OrderStoreHead__oid').val();
	OrderStoreNew.isSaved( headId, function( returnValue ){
		//alert('orderStoreNew.js submit: headId: '+ headId + ' exist: ' + returnValue );
		if( ! returnValue ){
			insert2();
		}
//		else{
//			update();
//		}
	});
	//先檢查此訂貨單編號是否已經存入DB,若無則寫入
	//送出
	OrderStoreNew.submit( headId );
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
