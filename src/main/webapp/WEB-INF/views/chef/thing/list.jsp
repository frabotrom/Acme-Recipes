<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="chef.thing.list.label.name" path="name" width="20%"/>
	<acme:list-column code="chef.thing.list.label.code" path="code" width="20%"/>
	<acme:list-column code="chef.thing.list.label.description" path="description" width="40"/>
	<acme:list-column code="chef.thing.list.label.retailPrice" path="newRetailPrice" width="20%"/>
</acme:list>

<acme:button code="chef.thing.list.button.create" action="/chef/thing/create"/>
