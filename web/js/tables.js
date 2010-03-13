$('iframe').ready(function() {
    alert( $('#frameWest').contents().find("#mainTable").css('color' , 'red') );

    $('table', $('#frameWest').contents()).find("#mainTable tr").mouseover(function(){ //適用本頁面的所有表格
        $(this).addClass("portlet-section-body-hover results-row hover null");
    }).mouseout(function(){ //滑鼠離開時執行
        $(this).addClass("portlet-section-body results-row null");
    }) 
//    ("#frameWest").contents().find("#mainTable tr:odd").addClass("portlet-section-alternate results-row alt null"); //表格的奇数行添加class值
//   ("#frameWest").contents().find("#mainTable tr:even").addClass("portlet-section-body results-row null"); //表格的偶数行添加class值

    // bind 'myForm' and provide a simple callback function 
    $('#myForm').ajaxForm(function() { 
        alert("Thank you for your comment!"); 
    }); 
});

