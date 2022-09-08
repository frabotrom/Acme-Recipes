<%--
- form.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>

	<jstl:if test="${acme:anyOf(command, 'show, update, delete')}">
	<acme:input-textbox code="chef.pimpam.form.label.code" path="code" placeholder ="AAA" readonly="true"/>
	</jstl:if>
	<jstl:if test="${acme:anyOf(command, 'create')}">
	<acme:input-textbox code="chef.pimpam.form.label.code" path="code" placeholder ="AAA"/>
	</jstl:if>
	<acme:input-textbox code="chef.pimpam.form.label.title" path="title"/>
	<acme:input-textarea code="chef.pimpam.form.label.description" path="description"/>		
	<acme:input-money code="chef.pimpam.form.label.budget" path="budget"/>
	
	<%-- Para que no se muestre a crearlo--%>
	<jstl:if test="${acme:anyOf(command, 'show, update, delete')}">
		<acme:input-moment code="chef.pimpam.form.label.instantiationMoment" path="instantiationMoment" readonly="true"/>
	</jstl:if>
	
	<acme:input-moment code="chef.pimpam.form.label.startDate" path="startDate"/>
	<acme:input-moment code="chef.pimpam.form.label.endDate" path="endDate"/>	
	<acme:input-url code="chef.pimpam.form.label.link" path="link"/>
	
	<%-- Para que no se muestre a crearlo--%>
	<jstl:if test="${acme:anyOf(command, 'show, update, delete')}">
		<acme:input-integer code="chef.pimpam.form.label.period" path="period" readonly="true"/>	
	</jstl:if>	
	
	
	<%-- Artefact Profile--%>
	<%-- Para que no se muestre a crearlo--%>
	<jstl:if test="${acme:anyOf(command, 'show, update, delete')}">	
		<hr>
	    <br>
	    <h3><acme:message code="chef.pimpam.form.label.artefact.title"/></h3>
		<acme:input-textbox code="chef.pimpam.form.label.artefactName" path="artefactName" readonly="true"/>
		<acme:input-textbox code="chef.pimpam.form.label.artefactCode" path="artefactCode" readonly="true"/>
		<acme:input-textarea code="chef.pimpam.form.label.artefactDescription" path="artefactDescription" readonly="true"/>
		<acme:input-money code="chef.pimpam.form.label.artefactRetailPrice" path="artefactRetailPrice" readonly="true"/>
		<acme:input-url code="chef.pimpam.form.label.artefactLink" path="artefactLink" readonly="true"/>
		<acme:input-textbox code="chef.pimpam.form.label.artefactType" path="artefactType" readonly="true"/>
		<acme:input-select code="chef.pimpam.form.label.artefactVisible" path="artefactVisible" readonly="true">
			<acme:input-option code="Not Published" value="FALSE" selected="${artefactVisible == false}"/>
			<acme:input-option code="Published" value="TRUE" selected="${artefactVisible == true}"/>
		</acme:input-select>	
	</jstl:if>
	
	
	<jstl:if test="${acme:anyOf(command, 'create')}">
		<acme:submit code="chef.pimpam.form.button.create" action="/chef/pimpam/create?thingId=${thingId}"/>
	</jstl:if>
	<jstl:if test="${acme:anyOf(command, 'show, update, delete') && artefactVisible == false}">
		<acme:submit code="chef.pimpam.form.button.update" action="/chef/pimpam/update"/>
		<acme:submit code="chef.pimpam.form.button.delete" action="/chef/pimpam/delete"/>
	</jstl:if>
	
	
	
</acme:form>

