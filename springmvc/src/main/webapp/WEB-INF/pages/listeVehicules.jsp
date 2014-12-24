<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
	<%@include file="head.jsp" %>
    <title>Liste des v&eacute;hicules</title>
</head>
<body>


<%@include file="navigation.jsp" %>

<div class="dark-matter">
	<h1>Rechercher des v&eacute;hicules</h1>
	<%@include file="tableVehicule.jsp" %>
</div>

<br/>
<div class="displaytable">
   <table>
    <tr>
        <th>Num&eacute;ro de S&eacute;rie</th>
   
        <th>Marque</th>
   
        <th>Mod&egrave;le</th>
   
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
		        	
		        	<input type="submit" value="&Eacute;diter" class="button"/>
		        </td>
		        </form:form>
		        
		        <form:form method="POST" action="/supprimervehicule" modelAttribute="vehiculemodel">
		        <td class="buttoncell">
			        <form:hidden path="numSerie" value="${vcl.getNumSerie()}"/>
					<form:hidden path="marque"   value="${vcl.getMarque()}"/>
					<form:hidden path="modele"   value="${vcl.getModele()}"/>
					<form:hidden path="type"     value="${vcl.getType()}"/>
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
