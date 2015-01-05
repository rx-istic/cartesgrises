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

<br/>
<form:form method="GET" action="/cgeditvehicule">
	<input id="im" name="im" value="${carteGrise.getImmatriculation()}" type="hidden"/>
	<input type="submit" value="Modifier V&eacute;hicule" class="button"/>
</form:form>

<form:form method="POST" action="/cgremovevehicule">
	<input id="im" name="im" value="${carteGrise.getImmatriculation()}" type="hidden"/>
	<input type="submit" value="Supprimer Association V&eacute;hicule" class="button"/>
</form:form>

<div class="displaytable">
<h2>Historique</h2>

<c:if test="${not empty historique}">
<table>
    <tr>
        <th>Date d&eacute;but</th>
   
        <th>Date Fin</th>
   
        <th>Propri&eacute;taire</th>
    </tr>
 
	 <c:forEach var="eh" items="${historique}">
	 
	 		<c:if test="${not empty eh.getRefProrietaire()}">
	   			<c:if test="${eh.getRefProrietaire().getTypeProprietaire()==1}">
	    			<tr style="cursor:pointer;" onclick="document.location = '/chercherparticulier?pid=${eh.getRefProrietaire().getId()}';">
	    		</c:if>
	    		<c:if test="${eh.getRefProrietaire().getTypeProprietaire()==2}">
	    			<tr style="cursor:pointer;" onclick="document.location = '/chercherparticulier?pid=${eh.getRefProrietaire().getId()}';">
	    		</c:if>
	    	</c:if>
			<c:if test="${empty eh.getRefProrietaire()}">
				<tr>
			</c:if>
	    			
	        <td>${eh.getDateDebut()}</td>
	   
	        <td>${eh.getDateFin()}</td>
	        
	   		<c:if test="${not empty eh.getRefProrietaire()}">
	   			<c:if test="${eh.getRefProrietaire().getTypeProprietaire()==1}">
	        		<td>${eh.getRefProrietaire().getNom()} ${eh.getRefProrietaire().getPrenom()}</td>
	        	</c:if>
	        	<c:if test="${eh.getRefProrietaire().getTypeProprietaire()==2}">
	        		<td onclick="document.location = '/cherchersociete?pid=${eh.getRefProrietaire().getId()}';">${eh.getRefProrietaire().getRaisonSociale()} ${eh.getRefProrietaire().getNumSiret()}</td>
	        	</c:if>
			</c:if>
			<c:if test="${empty eh.getRefProrietaire()}">
	        	<td>Pas de propri&eacute;taire</td>
			</c:if>
					    
	        <form:form method="POST" action="/supprimerelementhistorique" modelAttribute="elementmodel">
		        <td class="buttoncell">
			        <form:hidden path="id" value="${eh.getId()}"/>
			        <input id="im" name="im" value="${carteGrise.getImmatriculation()}" type="hidden"/>
		        	<input type="submit" value="Supprimer" class="button"/>
		        </td>
		    </form:form>
	    </tr>
    </c:forEach>
</table>
</c:if>

<c:if test="${empty historique}">
	Aucun Propri&eacute;taire associ&eacute;
</c:if>
</div>

<br/>
<form:form method="GET" action="/cgaddproprietaire">
	<input id="im" name="im" value="${carteGrise.getImmatriculation()}" type="hidden"/>
	<input type="submit" value="Ajouter un propri&eacute;taire" class="button"/>
</form:form>

</body>
</html>