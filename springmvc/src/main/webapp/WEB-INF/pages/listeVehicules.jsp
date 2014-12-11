<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
	<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
    <title>Liste des vehicules</title>
</head>
<body>

<h2>Vehicules</h2>
<a href="/">Retour</a>

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
		        
		        <form:form method="POST" action="/editervehicule" modelAttribute="vehiculemodel">
		        <td class="buttoncell">
		        	<form:hidden path="numSerie" value="${vcl.getNumSerie()}"/>
					<form:hidden path="marque"   value="${vcl.getMarque()}"/>
					<form:hidden path="modele"   value="${vcl.getModele()}"/>
					<form:hidden path="type"     value="${vcl.getType()}"/>
		        	
		        	<input type="submit" value="Editer"/>
		        </td>
		        </form:form>
		        
		        <form:form method="POST" action="/supprimervehicule" modelAttribute="vehiculemodel">
		        <td class="buttoncell">
			        <form:hidden path="numSerie" value="${vcl.getNumSerie()}"/>
					<form:hidden path="marque"   value="${vcl.getMarque()}"/>
					<form:hidden path="modele"   value="${vcl.getModele()}"/>
					<form:hidden path="type"     value="${vcl.getType()}"/>
		        	<input type="submit" value="Supprimer"/>
		        </td>
		        </form:form>
		        
		    </tr>
		
 	</c:forEach>
</table>  
</body>
</html>
