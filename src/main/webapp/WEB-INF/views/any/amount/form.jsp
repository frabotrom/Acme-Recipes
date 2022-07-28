<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="any.amount.form.label.recipe.heading" path="recipe.heading" readonly="true"/>

		<acme:input-textbox code="any.amount.form.label.thing.name" path="thing.name" readonly="true"/>
		<acme:input-textbox code="any.amount.form.label.thing.code" path="thing.code" readonly="true"/>
		<acme:input-url code="any.amount.form.label.item.info" path="thing.info" readonly="true"/>
		<acme:input-textbox code="any.amount.form.label.item.description" path="thing.description" readonly="true"/>
		<acme:input-money code="any.amount.form.label.item.retaiLPrice" path="thing.retailPrice" readonly="true"/>
		
		<jstl:choose>
			<jstl:when test="${command == 'show'&& newRetailPrice.getCurrency()!=retailPrice.getCurrency()}">
           		<acme:input-money code="any.amount.form.label.retail-price-conversion" path="newRetailPrice" readonly="true"/>
   			</jstl:when>
   		</jstl:choose>
		
		<acme:input-textbox code="any.recipe.form.label.thing.thingType" path="thing.thingType" readonly="true"/>			

	<acme:input-integer code="any.amount.form.label.quantity" path="quantity"/>
	<acme:input-textbox code="any.amount.form.label.unit" path="unit" readonly="true"/>
		
</acme:form>