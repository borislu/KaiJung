//Last Coding By Jason
function submitOSD(){
	alert("submitOSD Start!");
	alert("index="+index);
	for(i=1;i<=index;i++)
	{
		y=i+1;
		if(document.getElementById('row'+i) != null){
			for(j=1;j<=5;j++)
			{
				var r=document.getElementById('r'+j+'_'+i).value;
				var modifyid = document.getElementById('modifyid'+i).value;
				var inputObj = document.getElementById('isCustOrder'+i).checked;
				if(inputObj == true){
					var isCustOrder = 1;
				}else{
					var isCustOrder = 0;
				}
				var memo = document.getElementById('memo'+i).value;
				if(r != ""){
					quantity = parseInt(r);
					OrderStoreNewD.insert(quantity, modifyid, isCustOrder, memo);
				}else{
					quantity = 0;
					OrderStoreNewD.insert(quantity, modifyid, isCustOrder, memo);
				}				
			}
		}
	}	
}

function autoExport(){
alert("index="+index);
	for(z=1;z<=index;z++){
//        alert( "barcode"+ z + ': '+ $( ('#barcode'+z) ) );
        try{
			if( document.getElementById(('barcode'+z)) != null || $(('#barcode'+z)) != 'undefined' ){
				barcode = document.getElementById(('barcode'+z)).value;
//				alert("barcode="+barcode);
				OrderStoreNewD.autoExport(barcode,function(item){
					y=z-1;
					
//					alert(item.articleno + " , " + item.price);
//					alert('articleno: '+ y + ": "+ document.getElementById(('articleno'+y)));
					$(('#articleno'+y)).value = item.articleno;
					$(('#price'+y)).value = item.price;
//					alert("price="+$('#price'+y).value);
//					$('#color'+y).value = item.colorId;
//					alert("color="+document.getElementById('color'+y).value);
//					OrderStoreNewD.getColorName(document.getElementById('color'+y).value,function(colorName){
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
	OrderStoreNewD.findSuggestOid(1,function(suggestOid){
		for(var i=0; i<suggestOid.length ; i++){
			var tempOid = suggestOid[i];
			var oid = tempOid.oid;
			
			OrderStoreNewD.getSuggestDByOid(oid, function(suggestD){
				
			});
		}
	});

}

