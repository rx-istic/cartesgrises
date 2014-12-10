<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
	<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
    <title>Liste des vehicules</title>
</head>
<body>

<h2>Vehicules</h2>
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
