<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="epicure.memorandum.form.label.serial-number" path="serialNumber" readonly="true"/>
	<acme:input-moment code="epicure.memorandum.form.label.instantiation-moment" path="instantiationMoment" readonly="true"/>
	<acme:input-textarea code="epicure.memorandum.form.label.report" path="report"/>
	<acme:input-url code="epicure.memorandum.form.label.info" path="info"/>
	
	
	<jstl:choose>	
		<jstl:when test="${command == 'create'}">
			<acme:input-checkbox code="epicure.memorandum.form.label.confirmation" path="confirmation"/>
			<acme:submit code="epicure.memorandum.form.button.create" action="/epicure/memorandum/create?masterId=${masterId}"/>
		</jstl:when>
	</jstl:choose>	
			
</acme:form>