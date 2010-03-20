<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ include file="../xava/imports.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style type="text/css">
<!--
#content1 {
	width: 700px;
}

#table1 {
	float: left;
	width: 700px;
}

#table2 {
	float: left;
	top: 100px;
	height: 100px;
	width: 700px;
}

#column1 {
	float: left;
}

#column2 {
	float: left;
}

#column3 {
	float: left;
}
-->
</style>
</head>

<body>
<form id="headForm" action="controller" method="post">
<div id="content1">
 <div id="table1">
  <div id="column1">
   <div>訂單編號 <xava:editor property="readCode" /></div>
   <div>訂貨時間 <xava:editor property="createTime" /></div>
   <div>訂貨專櫃 <xava:descriptionsList reference="warehouse" /></div>
   <div>訂貨人員 <xava:descriptionsList reference="employee" /></div>
  </div>
  <div id="column2">
   <div>揀貨單編號 <xava:editor property="pickerId" /></div>
   <div>揀貨時間 <xava:editor property="pickerTime" /></div>
   <div>揀貨人員 <xava:editor property="pickerBy" /></div>
  </div>
  <div id="column3">
   <div>撥出單編號 <xava:editor property="senderId" /></div>
   <div>撥出時間 <xava:editor property="senderTime" /></div>
  <div>撥出人員 <xava:editor property="senderBy" /></div>
 </div>
 </div>
</div>
</form> 
</body>
</html>
