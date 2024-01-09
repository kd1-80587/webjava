<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<jsp:useBean id="pb" class="com.app.beans.PlayerBean" scope="session" />
<body>
	<jsp:setProperty property="*" name="pb" />
	<%-- <h4>Player Registration Status: ${pb.validateAndAddPlayer()} </h4> --%>
	<h4>Player Registration Status: ${session.getAttributes("pb").validateAndAddPlayer()} </h4>
</body>
</html>