<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="chef.memorandum.form.label.serial-number" path="serialNumber"/>
	<acme:input-moment code="chef.memorandum.form.label.instantiation-moment" path="instantiationMoment"/>
	<acme:input-textarea code="chef.memorandum.form.label.report" path="report"/>
	<acme:input-url code="chef.memorandum.form.label.info" path="info"/>
	
			
</acme:form>