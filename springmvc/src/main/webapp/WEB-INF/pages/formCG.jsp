<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<%@include file="head.jsp" %>
    <title>Ajouter Carte Grise</title>
</head>
<body>
<%@include file="navigation.jsp" %>

<div class="dark-matter">
	<h1>Cr&eacute;er une nouvelle Carte Grise</h1>
	<%@include file="tableCarteGrise.jsp" %>
</div>
</body>
</html>