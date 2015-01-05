<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
	<%@include file="head.jsp" %>
    <title>Liste des Cartes Grises</title>
</head>
<body>


<%@include file="navigation.jsp" %>

<div class="dark-matter">
	<h1>Rechercher des Cartes Grises</h1>
	<%@include file="tableCarteGrise.jsp" %>
</div>

<br/>
<div class="displaytable">
   <table>
    <tr>
        <th>Immatriculation</th>
        <th>Marque</th>
        <th>Mod&egrave;le</th>
        <th>Type</th>
        <th>Propri&eacute;taire Actuel</th>
    </tr>
 

	<c:forEach var="cgs" items="${cartesGrises}">
		
		    <tr style="cursor:pointer;" onclick="document.location = '/cgdetails?im=${cgs.getImmatriculation()}';">
		    	
		        <td>
		        	${cgs.getImmatriculation()}
		        </td>
		   		
		   		<c:if test="${not empty cgs.getRefVehicule()}">		
			   		<td>
			        	${cgs.getRefVehicule().getMarque()}
			        </td>
			   		
			   		<td>
			        	${cgs.getRefVehicule().getModele()}
			        </td>
			        
			        <td>
			        	${cgs.getRefVehicule().getType()}
			        </td>
		   		</c:if>
		   		
		   		<c:if test="${empty cgs.getRefVehicule()}">		
			   		<td>
			        	
			        </td>
			   		
			   		<td>
			        	
			        </td>
			        
			        <td>
			        	
			        </td>
		   		</c:if>
		   		
		   		<td>
		   				<c:set var="idimmat" value="${cgs.getImmatriculation()}"/>
			        	<c:out value="${proprietaires[idimmat]}" />
			     </td>
		        
		        <form:form method="POST" action="/supprimercg" modelAttribute="cgmodel">
		        <td class="buttoncell">
			        <form:hidden path="immatriculation" value="${cgs.getImmatriculation()}"/>
		        	<input type="submit" value="Supprimer" class="button"/>
		        </td>
		        </form:form>
		        
		    </tr>
		
 	</c:forEach>
</table>  
</div>

<br/>


</body>
</html>
