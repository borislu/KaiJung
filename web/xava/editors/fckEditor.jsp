<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
	<head>
		<script type="text/javascript" src="FCKeditor/fckeditor.js"></script>
		<title>Editor</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta name="robots" content="noindex, nofollow">
<% 
String nproperty=request.getParameter("nproperty");
String form=request.getParameter("form");
%>	
		<SCRIPT type="text/javascript">
	
			// User-defined callback called when form is submitted
			function doSave() {				
				var content = FCKeditorAPI.GetInstance('FCKeditor1').GetHTML();
				// Tell parent what the new content is
			    opener.document.<%=form%>.elements["<%=nproperty%>"].value = content;
			    opener.openxava.editors.html.showValue('<%=nproperty%>_html_editor_show_value', content);
			    self.close();			    
				return false;
			} 
	
			// FCK Callback called when FKKeditor is done starting
			function FCKeditor_OnComplete( editorInstance ) {					
				window.status = editorInstance.Description ;
				// assign initial content
				var content = opener.document.<%=form%>.elements["<%=nproperty%>"].value;
				editorInstance.SetHTML(content);			
				// assign callback function to be called on submit
				editorInstance.LinkedField.form.onsubmit = doSave;
			}
	
		</SCRIPT>
	</head>
	<body>
		<form name="fEditor" method="post" >
			<!-- FCK editor is part of form. Initial value is assigned in callback function -->
			<script type="text/javascript">
				var oFCKeditor = new FCKeditor('FCKeditor1');
				oFCKeditor.BasePath = "FCKeditor/";
				oFCKeditor.Height = '500';
				oFCKeditor.Config["AutoDetectLanguage"] = false;
				oFCKeditor.Config["DefaultLanguage"] = "<%=org.openxava.util.Locales.getCurrent().getLanguage()%>";				
				oFCKeditor.Create();
			</script>
			<br>
			<INPUT TYPE="submit" VALUE="<%=org.openxava.util.XavaResources.getString("ok")%>">
		</form>
	</body>
</html>
