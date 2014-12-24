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
    </tr>
 

	<c:forEach var="cgs" items="${cartesGrises}">
		
		    <tr>
		        <td>${cgs.getImmatriculation()}</td>
		   
		        <form:form method="POST" action="/editercg" modelAttribute="cgmodel">
		        <td class="buttoncell">
		        	<form:hidden path="immatriculation" value="${cgs.getImmatriculation()}"/>
					
		        	<input type="submit" value="&Eacute;diter" class="button"/>
		        </td>
		        </form:form>
		        
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
