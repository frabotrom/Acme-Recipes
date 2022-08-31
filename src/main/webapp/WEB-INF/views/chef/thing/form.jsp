<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">

	<acme:input-textbox code="chef.thing.form.label.name" path="name"/>	
	<acme:input-textbox code="chef.thing.form.label.code" path="code"/>
	<acme:input-textarea code="chef.thing.form.label.description" path="description"/>
	<acme:input-money code="chef.thing.form.label.retailPrice" path="retailPrice"/>
	<acme:input-url code="chef.thing.form.label.info" path="info"/>
	
	<jstl:choose>
		<jstl:when test="${command == 'show'&& newRetailPrice.getCurrency()!=retailPrice.getCurrency()}">
            <acme:input-money code="chef.thing.form.label.retail-price-conversion" path="newRetailPrice" readonly="true"/>
    	</jstl:when>
    </jstl:choose>
    
	<acme:input-select code="chef.thing.form.label.thingType" path="thingType">
		<acme:input-option code="INGREDIENT" value="INGREDIENT" selected="${thingType == 'INGREDIENT'}"/>
		<acme:input-option code="KITCHEN UTENSIL" value="KITCHENUTENSIL" selected="${thingType == 'KITCHENUTENSIL'}"/>
	</acme:input-select>
	
	<jstl:choose>
		<jstl:when test="${command != 'create'}">
			<acme:input-checkbox readonly="true" code="chef.thing.form.label.published" path="published"/>
		</jstl:when>
	</jstl:choose>
	
	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish') && published == false}">
			<acme:submit code="chef.thing.form.button.update" action="/chef/thing/update"/>
			<acme:submit code="chef.thing.form.button.delete" action="/chef/thing/delete"/>
			<acme:submit code="chef.thing.form.button.publish" action="/chef/thing/publish"/>
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="chef.thing.form.button.create" action="/chef/thing/create"/>
		</jstl:when>
	</jstl:choose>
	
	
	
	
</acme:form>
