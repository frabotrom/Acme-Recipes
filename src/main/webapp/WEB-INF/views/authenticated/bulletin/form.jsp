<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	<acme:input-textbox code="authenticated.bulletin.form.label.heading" path="heading"/>
	<acme:input-textarea code="authenticated.bulletin.form.label.text" path="text"/>
	<acme:input-moment code="authenticated.bulletin.form.label.instantiationMoment" path="instantiationMoment"/>
	<acme:input-textbox code="authenticated.bulletin.form.label.critical" path="critical"/>
	<acme:input-url code="authenticated.bulletin.form.label.link" path="link"/>
</acme:form>