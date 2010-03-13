<%@ include file="imports.jsp"%>
<table>
<tr>
<td>User ID: </td>
<td>
<input type="text" name="LOGIN_ID"/>
</td>
</tr>
<tr>
<td>Password: </td>
<td>
<input type="password" name="LOGIN_PWD"/>
</td>
</tr>
<tr>
<td colspan="2" >
<xava:action action="galaxur_user_manager.login" argv="resetAfter=true"/>
<input type="reset" />
</td>
</tr>
</table> 
