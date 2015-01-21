<html>
<head>
<%--<meta http-equiv="refresh" content="5"/> --%>
<link rel="stylesheet" type="text/css" href="/ChatRoom/CSS/style.css"/>
<link rel="stylesheet" type="text/css" href="/ChatRoom/CSS/reset.css"/>
</head>
<script type='text/javascript' src="/ChatRoom/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript">
var textArea = document.getElementById('responsetext');
    textArea.scrollTop = textArea.scrollHeight;
</script>
<script type="text/javascript">
    setTimeout(function(){ 
    	document.getElementById('usrform').submit(); 
    	}, 5000);
</script>

<body>
<!--Wrapper-->   
<div id="wrapper">
	<div class="portfolio">
		<font size="5">Hello, ${title}!</font>
		<div class="portfolio-container">
				<p id="responsetext"><font size="3">
					<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
					<c:forEach var="u" items="${list}">
					<table>
						<tr><td>${u.getUserName()}&emsp;&emsp;&emsp;&emsp;</td>
						<td>${u.getTime()}</td>
						<td>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
						&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
						&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</td>
						</tr>
						<tr><td>&emsp;</td></tr>
						<tr><td colspan="10">${u.getContent()}</td></tr>
						<tr><td>&emsp;</td></tr>
					</table>
					</c:forEach>
				</font></p>
				
					<%@ page import="java.util.List"%>
					<%String s = (String) request.getAttribute("original");%>
			<textarea type="text" name="content" class="mytext" form="usrform"><%if(s != null) out.print(s);%></textarea>
			<table>
				<tr>
					<td>
						<form method="post" action="manage.do" id="usrform">
							<input type="submit" value="Send" class="button" name="send">
						</form>
					</td> 
					<td>
						<form method="post" action="logout.do">
							<input name="button" type="submit" value="Logout" class="button">
						</form>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<div id="footer">
		<p>Copyright &copy; 2015 Yuchi Han, Carnegie Mellon University</p>
	</div>
</div>
</body>
</html>