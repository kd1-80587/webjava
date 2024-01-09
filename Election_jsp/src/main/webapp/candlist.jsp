<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Candidate List</title>
</head>
<body>
	Hello, ${lb.user.firstName} ${lb.user.lastName} <hr />
	<jsp:useBean id="clb" class="com.sunbeam.beans.CandidateListBean"></jsp:useBean>
	${clb.fetchCandidateList()}
	<form method="post" action="vote.jsp">
		<c:forEach var="c" items="${clb.candlist}">
			<input type="radio" name="candidate" value="${c.id}" /> ${c.name} - ${c.party} <br/>
		</c:forEach>
		<input type="submit" value="Vote" />
	</form>
</body>
</html>