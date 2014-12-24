<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="inputform">
<form:form method="POST" action="${action}" modelAttribute="particuliermodel">
   <table>
   <form:hidden path="id"/>
    <tr>
        <th><form:label path="adresse">Adresse</form:label></th>
        <td><form:input path="adresse"/></td>
    </tr>
    <tr>
        <th><form:label path="nom">Nom</form:label></th>
        <td><form:input path="nom" /></td>
    </tr>
    <tr>
        <th><form:label path="prenom">Pr&eacute;nom</form:label></th>
        <td><form:input path="prenom" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Valider" class="button"/>
        </td>
    </tr>
</table>  
</form:form>
</div>
