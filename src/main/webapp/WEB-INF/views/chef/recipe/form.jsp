<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<acme:form>
	<acme:input-textbox code="chef.recipe.form.label.code" path="code"/>	
	<acme:input-textarea code="chef.recipe.form.label.heading" path="heading"/>
	<acme:input-textarea code="chef.recipe.form.label.description" path="description"/>
	<acme:input-textarea code="chef.recipe.form.label.preparation-notes" path="preparationNotes"/>
	<acme:input-url code="chef.recipe.form.label.more-info" path="moreInfo"/>
	<acme:input-money code="chef.recipe.form.label.total-price" path="totalPrice"/>
	
	<jstl:if test="${command == 'show'}">
			<acme:button code="chef.recipe.form.button.amount" action="/chef/amount/list?id=${id}"/>		
	</jstl:if>
	
</acme:form>