//Last Coding By Jason
function submitOSD(){
debug = 'debug: ';
	var headId = $("input[name='ox_KaiJung_OrderStoreHead__oid']").val();
	for(i=1;i<=index;i++)
	{
alert("orderStoreNew.js: submitOSD: row"+ i +" exist: "+ $('row'+i) + " headId: " + headId );
		if( $('row'+i) ){
			for(j=1;j<=5;j++)
			{
				var modifyid = $('#modifyid'+i).val(); 
				var inputObj = $('#isCustOrder'+i).checked;
				if( inputObj == true){
					var isCustOrder = 1;
				}else{
					var isCustOrder = 0;
				}
				var memo = $('#memo'+i).val();
				var q = $('#r'+j+'_'+i).val();
				if( q != '' ){
debug += ', 非空, q: '+ q;
					quantity = parseInt(q);
					OrderStoreNew.insert(quantity, modifyid, isCustOrder, memo, headId);
				}else{
debug += ', 空值, q: '+ q;
					quantity = 0;
					OrderStoreNew.insert(quantity, modifyid, isCustOrder, memo, headId);
				}				
			}
		}
	}
alert( debug );
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
	OrderStoreNew.findSuggestOid(1,function(suggestOid){
		for(var i=0; i<suggestOid.length ; i++){
			var tempOid = suggestOid[i];
			var oid = tempOid.oid;
			
			OrderStoreNew.getSuggestDByOid(oid, function(suggestD){
				
			});
		}
	});

}

