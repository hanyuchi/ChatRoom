<jsp:include page="template-top.jsp" />
<jsp:include page="error-list.jsp" />
<p>
	<form method="post" action="login.do">
		<table>
			<tr>
				<td> User Name: </td>
				<td ><input type="text" name="userName" value="${form.userName}"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" name="button" value="Enter"/>
				</td>
			</tr>
		</table>
	</form>
</p>

<jsp:include page="template-bottom.jsp" />
