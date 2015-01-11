<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="inputform">
<form:form method="POST" action="${action}" modelAttribute="cgmodel">
   <table>
    <tr>
        <th><form:label path="immatriculation">Immatriculation</form:label></th>
        <td><form:input path="immatriculation"/></td>
    </tr>
    <tr>    
        <th><label>Num&eacute;ro de s&eacute;rie</label></th>
        <td><input name="ns" type="text" value=""/></td>
    </tr>
    <tr>     
        <th><label>Marque</label></th>
        <td><input name="mq" type="text" value=""/></td>
    </tr>
    <tr> 
        <th><label>Mod&egrave;le</label></th>
        <td><input name="md" type="text" value=""/></td>
    </tr>
    <tr> 
        <th><label>Type</label></th>
        <td><input name="tp" type="text" value=""/></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Valider" class="button"/>
        </td>
    </tr>
</table>  
</form:form>
</div>