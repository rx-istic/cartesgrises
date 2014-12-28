<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<%@include file="head.jsp" %>
    <title>D&eacute;tails Carte Grise - Choix du V&eacute;hicule</title>
</head>
<body>
<%@include file="navigation.jsp" %>

<div class="dark-matter">
	<h1>D&eacute;tails de la Carte Grise - Choix du V&eacute;hicule</h1>
	
    <table>
	    <tr>
	        <th>Immatriculation : </th>
	        <td>${carteGrise.getImmatriculation()}</td>
	    </tr>
	</table>  
</div>

<br/>
<div class="dark-matter">
	<h1>Rechercher des v&eacute;hicules</h1>
	<%@include file="tableVehicule.jsp" %>
</div>

<br/>
<div class="displaytable">

<c:if test="${not empty vehicules}">
<form:form method="POST" action="/cgassociervehicule">
   <table>
    <tr>
    	<th>Choix</th>
        <th>Num&eacute;ro de S&eacute;rie</th>
   
        <th>Marque</th>
   
        <th>Mod&egrave;le</th>
   
        <th>Type</th>
    </tr>
 

	<c:forEach var="vcl" items="${vehicules}">
		
		    <tr>
		    	<td><input type="radio" name="vcl" value="${vcl.getNumSerie()}"></td>
		        <td>${vcl.getNumSerie()}</td>
		   
		        <td>${vcl.getMarque()}</td>
		   
		        <td>${vcl.getModele()}</td>
		    
		        <td>${vcl.getType()}</td>
		    </tr>
		
 	</c:forEach>
	</table>
	<input id="im" name="im" value="${carteGrise.getImmatriculation()}" type="hidden"/>  
	<input type="submit" value="Associer" class="button"/>
</form:form>
</c:if>

<c:if test="${empty vehicules}">
	Aucun V&eacute;hicule disponible !
</c:if>
</div>


</body>
</html>