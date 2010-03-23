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
			var sizes = ['24','26','28','30','32']; //尺寸數量暫定5組
			for(j=1;j<=sizes.length;j++)
			{
				qtyVal = 0;
				if( $('#r'+j+'_'+i) ){  
					qtyVal = $('#r'+j+'_'+i).val();
				}
				quantity += sizes[j-1] + ':' + qtyVal + ',';
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

function beginLoad(){
	//alert('in findSuggestList()');
	OrderStoreNew.findSuggestList ( 1, function(results){ // argument: wareId , return: results <OrderSuggest>
		alert('results length: '+ results.length );
		for (var i = 0;i < results.length;i++) {
			alert( 'results: '+ results[i] + ' , reason: ' + results[i].reason );
		}
	});
}

