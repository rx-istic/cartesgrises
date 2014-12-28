<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<%@include file="head.jsp" %>
	<script src="/resources/js/jquery.js"></script>
	<script src="/resources/js/jquery-ui.js"></script>
	<link rel="stylesheet" href="/resources/css/jquery-ui.css">
	<script>
		$(function() {
			$( ".selectDate" ).datepicker({ dateFormat: 'dd/mm/yy' });
		});
	</script>
    <title>D&eacute;tails Carte Grise - Ajout d'un Propri&eacute;taire</title>
</head>
<body>
<%@include file="navigation.jsp" %>

<div class="dark-matter">
	<h1>D&eacute;tails de la Carte Grise - Ajout d'un Propri&eacute;taire</h1>
	
    <table>
	    <tr>
	        <th>Immatriculation : </th>
	        <td>${carteGrise.getImmatriculation()}</td>
	    </tr>
	</table>  
</div>

<div class="displaytable">
<h2>V&eacute;hicule associ&eacute;</h2>

<c:if test="${not empty vehicule}">
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
</c:if>
<c:if test="${empty vehicule}">
	Aucun V&eacute;hicule associ&eacute;
</c:if>
</div>


<div class="displaytable">

<c:if test="${not empty particuliers and not empty societes}">
<form:form method="POST" action="/cgdoaddproprietaire">

<h2>P&eacute;riode</h2>
 


  Date d&eacute;but :
  <input class="selectDate" type="date" name="dDebut">
  
  Date de fin :
  <input class="selectDate" type="date" name="dFin">



<h2>Choix du Propri&eacute;taire</h2>
<!-- include here search forms for societe/particuliers -->



<c:if test="${not empty particuliers}">
   <h3>Particuliers</h3>
   <table>
    <tr>
    	<th>Choix</th>
        <th>Adresse</th>
   
        <th>Nom</th>
   
        <th>Pr&eacute;nom</th>
    </tr>
 

	<c:forEach var="pcl" items="${particuliers}">
		
		    <tr>
		    	<td><input type="radio" name="pid" value="${pcl.getId()}"></td>
		        <td>${pcl.getAdresse()}</td>
		   
		        <td>${pcl.getNom()}</td>
		   
		        <td>${pcl.getPrenom()}</td>
		    </tr>
		
 	</c:forEach>
	</table>
</c:if>

<c:if test="${not empty societes}">	
	<h3>Soci&eacute;t&eacute;s</h3>
   	<table>
    <tr>
    	<th>Choix</th>
        <th>Adresse</th>
   
        <th>RaisonSociale</th>
   
        <th>SIRET</th>
    </tr>
 

	<c:forEach var="ste" items="${societes}">
		
		    <tr>
		    	<td><input type="radio" name="pid" value="${ste.getId()}"></td>
		        <td>${ste.getAdresse()}</td>
		   
		        <td>${ste.getRaisonSociale()}</td>
		   
		        <td>${ste.getNumSiret()}</td>
		    </tr>
		
 	</c:forEach>
	</table>
</c:if>
	
	<input id="im" name="im" value="${carteGrise.getImmatriculation()}" type="hidden"/>  
	<input type="submit" value="Choisir" class="button"/>
</form:form>
</c:if>

<c:if test="${empty particuliers and empty societes}">
	Aucun Propri&eacute;taire disponible.
</c:if>

</body>
</html>