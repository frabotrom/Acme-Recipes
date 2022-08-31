<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="chef.amount.form.label.recipe.heading" path="recipe.heading" readonly="true"/>
	<jstl:choose>
		<jstl:when test="${command == 'create'}">
			<acme:input-select path="thingId" code="chef.amount.form.label.thing.name">
				<jstl:forEach var="thing" items="${publishedItems}">
				<acme:input-option  code="${thing.name} - ${thing.thingType}" value="${thing.id}"/>
				</jstl:forEach>
			</acme:input-select>
		
		</jstl:when>

		<jstl:otherwise>
			<acme:input-textbox code="chef.amount.form.label.thing.name" path="thing.name" readonly="true"/>
			<acme:input-textbox code="chef.amount.form.label.thing.code" path="thing.code" readonly="true"/>
			<acme:input-textbox code="chef.amount.form.label.thing.info" path="thing.info" readonly="true"/>
			<acme:input-textbox code="chef.amount.form.label.thing.description" path="thing.description" readonly="true"/>
			<acme:input-money code="chef.amount.form.label.thing.retaiLPrice" path="thing.retailPrice" readonly="true"/>
			
			<jstl:choose>
				<jstl:when test="${command == 'show'&& newRetailPrice.getCurrency()!=retailPrice.getCurrency()}">
            		<acme:input-money code="chef.amount.form.label.retail-price-conversion" path="newRetailPrice" readonly="true"/>
    			</jstl:when>
    		</jstl:choose>
			
			<acme:input-textbox code="chef.amount.form.label.thing.thingType" path="thing.thingType" readonly="true"/>
			
		</jstl:otherwise>	
	</jstl:choose>
	<acme:input-integer code="chef.amount.form.label.quantity" path="quantity"/>
	<acme:input-textbox code="chef.amount.form.label.unit" path="unit"/>
	
	
	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete') && published == false}">
			<acme:submit code="chef.amount.form.button.update" action="/chef/amount/update"/>
			<acme:submit code="chef.amount.form.button.delete" action="/chef/amount/delete"/>
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="chef.amount.form.button.create" action="/chef/amount/create?id=${id}"/>
		</jstl:when>		
	</jstl:choose>		
</acme:form>