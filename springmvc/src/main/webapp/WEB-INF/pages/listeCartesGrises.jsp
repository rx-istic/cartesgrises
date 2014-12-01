<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.ArrayList" %>
<%@ page import="fr.istic.cg.persistance.Vehicule" %>

<html>
<head>
    <title>Liste des cartes grises</title>
</head>
<body>

<h2>Cartes Grises</h2>
<%-- <form:form method="POST" action="/listCartesGrises" modelAttribute="cartegrisemodel"> --%>
   <table>
    <tr>
        <th>NumSerie</th>
   
        <th>Marque</th>
   
        <th>Modele</th>
   
        <th>Type</th>
    </tr>
 

	<c:forEach var="vcl" items="${vehicules}">
	    <tr>
	        <td>${vcl.getNumSerie()}</td>
	   
	        <td>${vcl.getMarque()}</td>
	   
	        <td>${vcl.getModele()}</td>
	    
	        <td>${vcl.getType()}</td>
	    </tr>
 	</c:forEach>
</table>  
</body>
</html>
