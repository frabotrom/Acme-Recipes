

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<acme:form>
	<acme:input-textbox code="any.recipe.form.label.code" path="code"/>	
	<acme:input-textarea code="any.recipe.form.label.heading" path="heading"/>
	<acme:input-textarea code="any.recipe.form.label.description" path="description"/>
	<acme:input-textarea code="any.recipe.form.label.preparation-notes" path="preparationNotes"/>
	<acme:input-url code="any.recipe.form.label.more-info" path="moreInfo"/>
	<acme:input-money code="any.recipe.form.label.total-price" path="totalPrice"/>
	
	<acme:button code="any.recipe.form.button.things" action="/any/thing/list-recipe-things?id=${id}"/>
</acme:form>

