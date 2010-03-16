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