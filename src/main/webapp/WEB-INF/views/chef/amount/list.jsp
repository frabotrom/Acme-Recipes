<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="chef.quantity.list.label.item.code" path="thing.code"/>
	<acme:list-column code="chef.quantity.list.label.item.name" path="thing.name"/>
	<acme:list-column code="chef.quantity.list.label.item.thingType" path="thing.thingType"/>
	<acme:list-column code="chef.quantity.list.label.item.retailPrice" path="newRetailPrice"/>
	<acme:list-column code="chef.quantity.list.label.item.quantity" path="quantity"/>
	
</acme:list>