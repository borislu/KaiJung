
function submitPick(){
//debug = 'debug: ';
	var headId = $('#ox_KaiJung_OrderStoreHead__oid').val();
	for(i=1;i<=index;i++)
	{
//alert("pickerNew.js: submitOSD: row"+ i +" exist: "+ $('row'+i) + " headId: " + headId );
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

