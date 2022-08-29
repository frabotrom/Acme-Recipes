<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">


	<acme:input-textbox code="any.thing.form.label.name" path="name"/>	
	<acme:input-textbox code="any.thing.form.label.code" path="code"/>
	<acme:input-textarea code="any.thing.form.label.description" path="description"/>
	<acme:input-money code="any.thing.form.label.retailPrice" path="retailPrice"/>
	<acme:input-url code="any.thing.form.label.info" path="info"/>
	
	<jstl:choose>
		<jstl:when test="${command == 'show'&& newRetailPrice.getCurrency()!=retailPrice.getCurrency()}">
            <acme:input-money code="any.thing.form.label.retail-price-conversion" path="newRetailPrice" readonly="true"/>
    	</jstl:when>
    </jstl:choose>
	
	
	<acme:input-select code="any.thing.form.label.thingType" path="thingType">
		<acme:input-option code="INGREDIENT" value="INGREDIENT" selected="${thingType == 'INGREDIENT'}"/>
		<acme:input-option code="KITCHEN UTENSIL" value="KITCHENUTENSIL" selected="${thingType == 'KITCHENUTENSIL'}"/>
	</acme:input-select>
	<acme:input-textbox code="any.thing.form.label.published" path="published"/>
	
	
	
	
</acme:form>
