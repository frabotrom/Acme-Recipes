<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="administrator.systemconfiguration.form.label.system-currency" path="systemCurrency"/>
	<acme:input-textarea code="administrator.systemconfiguration.form.label.accepted-currencies" path="acceptedCurrencies"/>
	<acme:input-textarea code="administrator.systemconfiguration.form.label.spam-terms-en" path="spamTermsEn"/>
	<acme:input-textarea code="administrator.systemconfiguration.form.label.spam-terms-es" path="spamTermsEs"/>
	<acme:input-double code="administrator.systemconfiguration.form.label.spam-threshhold" path="spamThreshold"/>

	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update')}">
			<acme:submit code="administrator.systemconfiguration.form.button.update" action="/administrator/system-configuration/update"/>
		</jstl:when>
	</jstl:choose>

</acme:form>