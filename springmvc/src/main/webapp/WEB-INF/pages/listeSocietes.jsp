<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
	<%@include file="head.jsp" %>
    <title>Liste des Soci&eacute;tes</title>
</head>
<body>



<%@include file="navigation.jsp" %>

<div class="dark-matter">
	<h1>Rechercher des Soci&eacute;t&eacute;s</h1>
	<%@include file="tableSociete.jsp" %>
</div>

<br/>
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
	        
	         <form:form method="POST" action="/editersociete" modelAttribute="societemodel">
		        <td class="buttoncell">
		        	<form:hidden path="id" value="${ste.getId()}"/>
					<form:hidden path="adresse"   value="${ste.getAdresse()}"/>
					<form:hidden path="raisonSociale"   value="${ste.getRaisonSociale()}"/>
					<form:hidden path="numSiret"     value="${ste.getNumSiret()}"/>
		        	
		        	<input type="submit" value="&Eacute;diter" class="button"/>
		        </td>
		        </form:form>
		        
		        <form:form method="POST" action="/supprimersociete" modelAttribute="societemodel">
		        <td class="buttoncell">
			        <form:hidden path="id" value="${ste.getId()}"/>
					<form:hidden path="adresse"   value="${ste.getAdresse()}"/>
					<form:hidden path="raisonSociale"   value="${ste.getRaisonSociale()}"/>
					<form:hidden path="numSiret"     value="${ste.getNumSiret()}"/>
		        	<input type="submit" value="Supprimer" class="button"/>
		        </td>
		        </form:form>
	    </tr>
 	</c:forEach>
</table>
</div>  
</body>
</html>
