<html>
<head>
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="pragma" content="no-cache">
	<title>Chat Room</title>
	<style>
		.menu-head {font-size: 10pt; font-weight: bold; color: black; }
		.menu-item {font-size: 10pt;  color: black }
    </style>
</head>

<body>
<table cellpadding="4" cellspacing="0">
    <tr>
	    <!-- Banner row across the top -->
        <td width="40" bgcolor="#F5F4BE">
        </td>
        
        <td bgcolor="#F5F4BE">&nbsp;
        </td>
        
        <td width="400" bgcolor="#F5F4BE">
            <p align="center">

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<c:choose>
		<c:when test="${not empty title}">
			<font size="5">Hello, ${title}!   </font>
			<span class="menu-item"><a href="logout.do"><font size="3">Logout</font></a></span>
		</c:when>
		<c:otherwise>
			<font size="5">Welcome to Amazing Chat Room!</font>
		</c:otherwise>
	</c:choose>

			</p>
		</td>
    </tr>
	
	<!-- Spacer row -->
	<tr>
		<td bgcolor="#F5F4BE" style="font-size:5px">&nbsp;</td>
		<td colspan="2" style="font-size:5px">&nbsp;</td>
	</tr>
	
	<tr>
		<c:choose>
		<c:when test="${not empty title}">
			<td bgcolor="#F5F4BE" valign="top" height="500">
		</c:when>
		<c:otherwise>
			<td bgcolor="#F5F4BE" valign="top" height="80">
		</c:otherwise>
		</c:choose>
		
			<!-- Navigation bar is one table cell down the left side -->
            <p align="left">
	
	<span class="menu-item">&nbsp;</span><br/>
	
			</p>
		</td>
		
		<td>
			<!-- Padding (blank space) between navbar and content -->
		</td>
		<td  valign="top">
