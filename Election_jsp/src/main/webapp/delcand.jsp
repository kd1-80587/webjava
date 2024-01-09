<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete candidate</title>
</head>
<body>
<jsp:useBean id="dcb" class="com.sunbeam.beans.DeleteCandidateBean" scope="request"></jsp:useBean>
<jsp:setProperty property="candId" name="dcb" param="id" />
${dcb.deleteCandidate()}
<jsp:forward page="result.jsp"/>
</body>
</html>