<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<%@include file="head.jsp" %>
    <title>D&eacute;tails Carte Grise</title>
</head>
<body>
<%@include file="navigation.jsp" %>

<div class="dark-matter">
	<h1>D&eacute;tails de la Carte Grise</h1>
	
    <table>
	    <tr>
	        <th>Immatriculation : </th>
	        <td>${carteGrise.getImmatriculation()}</td>
	    </tr>
	</table>  
</div>

<div class="displaytable">
<h2>V&eacute;hicule associ&eacute;</h2>
<table>
    <tr>
        <th>Num&eacute;ro de S&eacute;rie</th>
   
        <th>Marque</th>
   
        <th>Mod&egrave;le</th>
   
        <th>Type</th>
    </tr>
 
    <tr>
        <td>${vehicule.getNumSerie()}</td>
   
        <td>${vehicule.getMarque()}</td>
   
        <td>${vehicule.getModele()}</td>
    
        <td>${vehicule.getType()}</td>
    </tr>
</table>

<h2>Historique</h2>
<table>
    <tr>
        <th>Date d&eacute;but</th>
   
        <th>Date Fin</th>
   
        <th>Propri&eacute;taire</th>
    </tr>
 
	 <c:forEach var="eh" items="${historique}">
	    <tr>
	        <td>${eh.getDateDebut()}</td>
	   
	        <td>${eh.getDateFin()}</td>
	        
	   		<c:if test="${not empty eh.getRefProrietaire()}">
	   			<c:if test="${eh.getRefProrietaire().getTypeProprietaire()==1}">
	        		<td onclick="document.location = '/chercherparticulier?pid=${eh.getRefProrietaire().getId()}';">${eh.getRefProrietaire().getNom()} ${eh.getRefProrietaire().getPrenom()}</td>
	        	</c:if>
	        	<c:if test="${eh.getRefProrietaire().getTypeProprietaire()==2}">
	        		<td onclick="document.location = '/cherchersociete?pid=${eh.getRefProrietaire().getId()}';">${eh.getRefProrietaire().getRaisonSociale()} ${eh.getRefProrietaire().getNumSiret()}</td>
	        	</c:if>
			</c:if>
			<c:if test="${empty eh.getRefProrietaire()}">
	        	<td>Pas de propri&eacute;taire</td>
			</c:if>		    
	        
	    </tr>
    </c:forEach>
</table>
</div>
</body>
</html>