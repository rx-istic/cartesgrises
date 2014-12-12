<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<%@include file="head.jsp" %>
    <title>Ajouter v&eacute;hicule</title>
</head>
<body>
<%@include file="navigation.jsp" %>
<div class="inputform">
<form:form method="POST" action="${action}" modelAttribute="vehiculemodel">
   <table>
    <tr>
        <th><form:label path="numSerie">Num&eacute;ro de s&eacute;rie</form:label></th>
        <td><form:input path="numSerie"/></td>
    </tr>
    <tr>
        <th><form:label path="marque">Marque</form:label></th>
        <td><form:input path="marque" /></td>
    </tr>
    <tr>
        <th><form:label path="modele">Mod&egrave;le</form:label></th>
        <td><form:input path="modele" /></td>
    </tr>
    <tr>
        <th><form:label path="type">Type</form:label></th>
        <td><form:input path="type" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  
</form:form>
</div>
</body>
</html>