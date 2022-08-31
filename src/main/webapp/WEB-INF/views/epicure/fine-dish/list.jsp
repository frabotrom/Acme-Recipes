<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="epicure.fine-dish.list.label.code" path="code" />
	<acme:list-column code="epicure.fine-dish.list.label.status" path="status" />
	<acme:list-column code="epicure.fine-dish.list.label.budget" path="newBudget"/>
	<acme:list-column code="epicure.fine-dish.list.label.creation-moment" path="creationMoment"/>
	<acme:list-column code="epicure.fine-dish.list.label.published" path="published"/>
</acme:list>

<acme:button code="epicure.fine-dish.list.button.create" action="/epicure/fine-dish/create"/>