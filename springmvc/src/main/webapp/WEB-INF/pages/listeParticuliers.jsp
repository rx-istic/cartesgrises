<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
	<%@include file="head.jsp" %>
    <title>Liste des Particuliers</title>
</head>
<body>



<%@include file="navigation.jsp" %>

<div class="dark-matter">
	<h1>Rechercher des Particuliers</h1>
	<%@include file="tableParticulier.jsp" %>
</div>

<br/>
<div class="displaytable">
   <table>
    <tr>
        <th>ID</th>
   
        <th>Adresse</th>
   
        <th>Nom</th>
   
        <th>Pr&eacute;nom</th>
    </tr>
 

	<c:forEach var="par" items="${particuliers}">
	    <tr>
	        <td>${par.getId()}</td>
	   
	        <td>${par.getAdresse()}</td>
	   
	        <td>${par.getNom()}</td>
	    
	        <td>${par.getPrenom()}</td>
	        
	         <form:form method="POST" action="/editerparticulier" modelAttribute="particuliermodel">
		        <td class="buttoncell">
		        	<form:hidden path="id" value="${par.getId()}"/>
					<form:hidden path="adresse"   value="${par.getAdresse()}"/>
					<form:hidden path="nom"   value="${par.getNom()}"/>
					<form:hidden path="prenom"     value="${par.getPrenom()}"/>
		        	
		        	<input type="submit" value="&Eacute;diter" class="button"/>
		        </td>
		        </form:form>
		        
		        <form:form method="POST" action="/supprimerparticulier" modelAttribute="particuliermodel">
		        <td class="buttoncell">
			        <form:hidden path="id" value="${par.getId()}"/>
					<form:hidden path="adresse"   value="${par.getAdresse()}"/>
					<form:hidden path="nom"   value="${par.getNom()}"/>
					<form:hidden path="prenom"     value="${par.getPrenom()}"/>
		        	<input type="submit" value="Supprimer" class="button"/>
		        </td>
		        </form:form>
	    </tr>
 	</c:forEach>
</table>
</div>  
</body>
</html>
