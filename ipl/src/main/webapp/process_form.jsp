<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<jsp:useBean id="player_bean" class="beans.PlayerBean" scope="session" />
<jsp:setProperty property="*" name="player_bean"/>
<body>
<h4>Player Registration Status : ${sessionScope.player_bean.validatePlayer()}</h4>
</body>
</html>