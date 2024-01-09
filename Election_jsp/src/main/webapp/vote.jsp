<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Vote</title>
</head>
<body>
<jsp:useBean id="vb" class="com.sunbeam.beans.VoteBean" />
<jsp:setProperty name="vb" property="candId" param="candidate" />
<jsp:setProperty name="vb" property="userId" value="${lb.user.id}" />
${vb.registerVote()}
<h3>${vb.message}</h3>
<a href="signout.jsp">Sign Out</a>
</body>
</html>