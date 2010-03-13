if (openxava == null) var openxava = {};
if (openxava.editors == null) openxava.editors = {};

// htmlEditor
if (openxava.editors.html == null) openxava.editors.html = {};
openxava.editors.html.showValue = function(place, content){
  	document.getElementById(place).innerHTML=content; 
  	return true;
}
openxava.editors.html.openWindow = function(url){
 	window.open(url,'window','status=no,scrollbars=yes,menubar=no,location=no,resizable=no,height=600,width=750');
}

