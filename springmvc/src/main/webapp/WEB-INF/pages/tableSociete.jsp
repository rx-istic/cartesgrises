<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="inputform">
<form:form method="POST" action="${action}" modelAttribute="societemodel">
   <table>
   <form:hidden path="id"/>
    <tr>
        <th><form:label path="adresse">Adresse</form:label></th>
        <td><form:input path="adresse"/></td>
    </tr>
    <tr>
        <th><form:label path="raisonSociale">Raison sociale</form:label></th>
        <td><form:input path="raisonSociale" /></td>
    </tr>
    <tr>
        <th><form:label path="numSiret">Siret</form:label></th>
        <td><form:input path="numSiret" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Valider" class="button"/>
        </td>
    </tr>
</table>  
</form:form>
</div>
