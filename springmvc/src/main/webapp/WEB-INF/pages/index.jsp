<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<%@include file="head.jsp" %>
	<title>Accueil principal</title>
</head>
<body>
<%@include file="navigation.jsp" %>

	<h1>Accueil principal</h1>

	Projet r&eacute;alis&eacute; par <b>R&eacute;mi FELIX</b> et <b>St&eacute;phane CAPMARTI</b> dans le cadre du module GLA.<br/>
	<br/>
	Ce portail permet de g&eacute;rer un ensemble de cartes grises &agrave; travers une IHM WEB.  
	
	Le portail permet la cr&eacute;ation, l'&eacute;dition, la suppression et la recherche de : <br/>
	<ul>
	<li>V&eacute;hicules</li>
	<li>Particuliers</li>
	<li>Soci&eacute;t&eacute;s</li>
	<li>Cartes grises</li>
	</ul>
	<br/>
	
	Il est possible de naviguer entre ces diff&eacute;rentes fonctionnalit&eacute;s en utilisant le panneau lat&eacute;ral gauche.<br/>
	Pour certaines donn&eacute;es, notamment les cartes grises, il est possible d'acc&eacute;der aux d&eacute;tails de l'objet li&eacute; en cliquant sur les champs (par exemple un &eacute;l&eacute;ment historique). 
	<br/>
	<br/>
	Dans les fonctionnalit&eacute;s disponibles, nous avons : <br/>
	<ol>
		<li>Saisie d'un nouveau v&eacute;hicule et d'une nouvelle carte grise, avec un propri&eacute;taire existant.</li>
		<li>Saisie d'un nouveau propri&eacute;taire.</li>
		<li>Recherche d'une carte grise selon plusieurs crit&egrave;res.</li>
		<li>Recherche d'un propri&eacute;taire selon plusieurs crit&egrave;res.</li>
		<li>Changement de propri&eacute;taire d'une carte grise existante.</li>
	</ol>
	
	<br/>
	<br/>
	
	
	Le saviez-vous ?!<br/>
	La directive europ&eacute;enne 1999/37/CE du 29 avril 1999 d&eacute;finit les dispositions l&eacute;gales du certificat d'immatriculation europ&eacute;en.<br/>
	Merci.
</body>
</html> 