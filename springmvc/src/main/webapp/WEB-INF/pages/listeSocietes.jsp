<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
	<%@include file="head.jsp" %>
    <title>Liste des Soci&eacute;tes</title>
</head>
<body>

<h2>Soci&eacute;tes</h2>

<%@include file="navigation.jsp" %>
<div class="displaytable">
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
</div>  
</body>
</html>
