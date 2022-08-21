<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="epicure.memorandum.list.label.report" path="report"/>

</acme:list>

<acme:button test="${showCreate}" code="epicure.memorandum.list.button.create" action="/epicure/memorandum/create?masterId=${masterId}"/>
