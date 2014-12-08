<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
	<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
    <title>Liste des Soci&eacute;tes</title>
</head>
<body>

<h2>Soci&eacute;tes</h2>
   <table>
    <tr>
        <th>ID</th>
   
        <th>Adresse</th>
   
        <th>Raison sociale</th>
   
        <th>Siret</th>
    </tr>
 

	<c:forEach var="ste" items="${societes}">
	    <tr>
	        <td>${ste.getId()}</td>
	   
	        <td>${ste.getAdresse()}</td>
	   
	        <td>${ste.getRaisonSociale()}</td>
	    
	        <td>${ste.getNumSiret()}</td>
	    </tr>
 	</c:forEach>
</table>  
</body>
</html>
