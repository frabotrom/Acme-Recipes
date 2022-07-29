<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>	

	

	<acme:input-textbox code="epicure.fine-dish.form.label.code" path="code"/>
	<acme:input-select code="epicure.fine-dish.form.label.status" path="status">
		<acme:input-option code="PROPOSED" value="PROPOSED" selected="${status == 'PROPOSED'}"/>
		<acme:input-option code="ACCEPTED" value="ACCEPTED" selected="${status == 'ACCEPTED'}"/>
		<acme:input-option code="DENIED" value="DENIED" selected="${status == 'DENIED'}"/>
	</acme:input-select>
	<acme:input-textarea code="epicure.fine-dish.form.label.request" path="request" />
	<acme:input-money code="epicure.fine-dish.form.label.budget" path="budget"/>
    
    <jstl:choose>
    	<jstl:when test="${command != 'create'}">
			<acme:input-moment code="epicure.fine-dish.form.label.creation-moment" path="creationMoment" readonly="true"/>
		</jstl:when>
	</jstl:choose>
	<acme:input-moment code="epicure.fine-dish.form.label.start-date" path="startDate"/>
	<acme:input-moment code="epicure.fine-dish.form.label.end-date" path="endDate"/>
	<acme:input-url code="epicure.fine-dish.form.label.more-info" path="moreInfo"/>
	
	 <hr>
	 
		<h2> Chef </h2>
		
		<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish')}">

			<acme:input-textbox code="epicure.fine-dish.label.chef-full-name" path="chefFullName" readonly="true"/>
			<acme:input-textbox code="epicure.fine-dish.label.chef-email" path="chefEmail" readonly="true"/>
			<acme:input-textbox code="epicure.fine-dish.label.chef-organisation" path="chefOrganisation" readonly="true"/>
			<acme:input-textbox code="epicure.fine-dish.label.chef-assertion" path="chefAssertion" readonly="true"/>
		</jstl:when>
		</jstl:choose>
		
		<acme:button code="epicure.fine-dish.form.button.memorandum" action="/epicure/memorandum/list?masterId=${id}"/>			
		

	

		
	
		
	 
</acme:form>
