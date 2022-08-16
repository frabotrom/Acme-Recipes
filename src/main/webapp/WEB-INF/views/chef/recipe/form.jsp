<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<acme:form>
	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show,delete,publish,update') }">
			<acme:input-textbox code="chef.recipe.form.label.code" path="code" readonly="true"/>	
		</jstl:when>
		<jstl:when test="${acme:anyOf(command, 'create') }">
			<acme:input-textbox code="chef.recipe.form.label.code" path="code"/>	
		</jstl:when>
	</jstl:choose>	 
	
		<acme:input-textarea code="chef.recipe.form.label.heading" path="heading"/>
		<acme:input-textarea code="chef.recipe.form.label.description" path="description"/>
		<acme:input-textarea code="chef.recipe.form.label.preparation-notes" path="preparationNotes"/>
		<acme:input-url code="chef.recipe.form.label.more-info" path="moreInfo"/>
	
	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show,delete,publish') }">
			<acme:input-money code="chef.recipe.form.label.total-price" path="totalPrice" readonly="true"/>
		</jstl:when>
	</jstl:choose>	 
	
	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show,update,delete,publish') }">
			<acme:input-checkbox code="chef.recipe.form.label.published" path="published" readonly="true"/>
		</jstl:when>
	</jstl:choose>	 
	
	<jstl:choose>	 
		<jstl:when test="${command == 'create'}">
			<acme:submit code="chef.recipe.form.button.create" action="/chef/recipe/create"/>
		</jstl:when>	
		<jstl:when test="${acme:anyOf(command,'show,update,delete,publish') && published == false}">
			<acme:submit code="chef.recipe.form.button.delete" action="/chef/recipe/delete"/>
			<acme:submit code="chef.recipe.form.button.publish" action="/chef/recipe/publish"/>
			<acme:submit code="chef.recipe.form.button.update" action="/chef/recipe/update"/>
			<jstl:if test="${isEmpty == false }">
				<acme:button code="chef.recipe.form.button.quantities" action="/chef/amount/list?id=${id}"/>
			</jstl:if>
			<acme:button code="chef.amount.form.button.quantities.create" action="/chef/amount/create?id=${id}"/>
		</jstl:when>	
			<jstl:when test="${command == 'show' && published == true}">
				<acme:button code="chef.recipe.form.button.amount" action="/chef/amount/list?id=${id}"/>
			</jstl:when>
	</jstl:choose>
		
	
</acme:form>