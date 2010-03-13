
function clearForm ( form1 ){
  form1.reset();
}

/* Table 用的，隔行換色 */
function bgChange( tableName ){ 
  var tables = document.getElementById( tableName );
    var odd = false;
    trs = tables.getElementsByTagName("tr");
    for(var j=0; j<trs.length; j++){
      if(odd==true){
        trs[j].style.background = "#EEFEFF";
        odd = false;
      }else{
        trs[j].style.background = "#DDEEFF";
        odd = true;
      }
    }
}



