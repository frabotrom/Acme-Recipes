<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>	

	

	<acme:input-textbox code="chef.fine-dish.form.label.code" path="code"/>
	<acme:input-select code="chef.fine-dish.form.label.status" path="status">
		<acme:input-option code="PROPOSED" value="PROPOSED" selected="${status == 'PROPOSED'}"/>
		<acme:input-option code="ACCEPTED" value="ACCEPTED" selected="${status == 'ACCEPTED'}"/>
		<acme:input-option code="DENIED" value="DENIED" selected="${status == 'DENIED'}"/>
	</acme:input-select>
	<acme:input-textarea code="chef.fine-dish.form.label.request" path="request" />
	<acme:input-money code="chef.fine-dish.form.label.budget" path="budget"/>
    
    <jstl:choose>
    	<jstl:when test="${command != 'create'}">
			<acme:input-moment code="chef.fine-dish.form.label.creation-moment" path="creationMoment" readonly="true"/>
		</jstl:when>
	</jstl:choose>
	<acme:input-moment code="chef.fine-dish.form.label.start-date" path="startDate"/>
	<acme:input-moment code="chef.fine-dish.form.label.end-date" path="endDate"/>
	<acme:input-url code="chef.fine-dish.form.label.more-info" path="moreInfo"/>
	<acme:input-textbox code="chef.fine-dish.form.label.published" path="published"/>
	
	 <hr>
	 
		<h2> Epicure </h2>
		
		<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish')}">

			<acme:input-textbox code="chef.fine-dish.label.epicure-full-name" path="epicureFullName" readonly="true"/>
			<acme:input-textbox code="chef.fine-dish.label.epicure-email" path="epicureEmail" readonly="true"/>
			<acme:input-textbox code="chef.fine-dish.label.epicure-organisation" path="epicureOrganisation" readonly="true"/>
			<acme:input-textbox code="chef.fine-dish.label.epicure-assertion" path="epicureAssertion" readonly="true"/>
		</jstl:when>
		</jstl:choose>
		
		<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update') && status =='PROPOSED'}">
			<acme:submit code="chef.fine-dish.form.button.updateStatus" action="/chef/fine-dish/update-status"/>
			
		</jstl:when>
		
		</jstl:choose>
		

		<acme:button code="chef.fine-dish.form.button.memorandum" action="/chef/memorandum/list?masterId=${id}"/>			
	

			
	 
</acme:form>
