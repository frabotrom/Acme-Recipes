<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	<acme:input-textbox code="authenticated.systemconfiguration.form.label.system-currency" path="systemCurrency"/>
    <acme:input-textbox code="authenticated.systemconfiguration.form.label.accepted-currencies" path="acceptedCurrencies"/>
    
</acme:form>