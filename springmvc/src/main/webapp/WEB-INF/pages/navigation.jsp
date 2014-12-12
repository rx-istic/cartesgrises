<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<%@include file="head.jsp" %>
</head>
<body>
	<h1>Accueil principal</h1>
	
<ul>
<c:forEach var="lnk" items="${links}">
    <li><a href="${lnk}">${lnk}</a>
</c:forEach> 
</ul>
	
</body>
</html> 