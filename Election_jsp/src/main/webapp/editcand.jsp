<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Candidate</title>
</head>
<body>
Hello, ${lb.user.firstName} ${lb.user.lastName} <hr/>
<h3>Edit candidate:</h3>
<jsp:useBean id="fcb" class="com.sunbeam.beans.FindCandidateBean" />
<jsp:setProperty property="candId" name="fcb" param="id" />
${fcb.fetchCandidate()}
<form action="updatecand.jsp" method="post">
Id: <input type="number" name="id" value="${fcb.candidate.id}" readonly><br> <br>
Name: <input type="text" name="name" value="${fcb.candidate.name}"> <br> <br>
Party: <input type="text" name="party" value="${fcb.candidate.party}"> <br> <br>
Votes: <input type="number" name="votes" value="${fcb.candidate.votes}" readonly> <br> <br>
<input type="submit" value="Update Candidate">
</form>
</body>
</html>