<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="administrator.bulletin.form.label.heading" path="heading"/>
	<acme:input-textarea code="administrator.bulletin.form.label.text" path="text"/>
	<acme:input-url code="administrator.bulletin.form.label.link" path="link" placeholder="https://example.com"/>	
	<acme:input-checkbox code="administrator.bulletin.form.label.critical" path="critical"/>

	<jstl:if test="${!readonly}">
		<acme:input-checkbox code="administrator.bulletin.form.label.confirmation" path="confirmation"/>
		<acme:submit code="administrator.bulletin.form.button.create" action="/administrator/bulletin/create"/>
	</jstl:if>
</acme:form> 