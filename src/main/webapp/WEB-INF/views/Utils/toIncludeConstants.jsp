<%@page import="com.indoCoupon.test.utils.Constants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<meta charset="ISO-8859-1">
<body>

<%-- <%@page  import="com.Indoera.ecomProject.Utils.Constants.storeStatus" %>
<%@page  import="com.Indoera.ecomProject.Utils.Constants.userStatus" %>
<%@page  import="com.Indoera.ecomProject.Utils.Constants.userRole" %>
<%@page  import="com.Indoera.ecomProject.Utils.Constants.Gender" %>


<c:set var="openStore" value="<%= storeStatus.OPEN%>" scope="page" />
<c:set var="closedStore" value="<%= storeStatus.CLOSED%>" scope="page" />
<c:set var="onHoldStore" value="<%= storeStatus.ONHOLD%>" scope="page" />
<c:set var="reopenSoonStore" value="<%= storeStatus.REOPENINGSOON%>" scope="page" />
 --%>
 
<%@page  import="com.indoCoupon.test.utils.Constants.userRole" %>
<%@page  import="com.indoCoupon.test.utils.Constants.couponStatus" %>
<%@page  import="com.indoCoupon.test.utils.Constants.BrandsList" %>


 
<script>
var _const_userRoles = {
		slt_admin : "<%=Constants.userRole.ADMIN%>",
		customer : "<%=Constants.userRole.CUSTOMER%>",
}

var _const_BrandsList = {

		AMAZON : "<%=Constants.BrandsList.AMAZON%>",
		Amnesty_International : "<%=Constants.BrandsList.Amnesty_International%>",
		Big_Bazar : "<%=Constants.BrandsList.Big_Bazar%>",
		Eco_Matcher : "<%=Constants.BrandsList.Eco_Matcher%>",
		Flipkart : "<%=Constants.BrandsList.Flipkart%>",
		Mastercard : "<%=Constants.BrandsList.Mastercard%>",
		Myntra : "<%=Constants.BrandsList.Myntra%>",
		Pantaloons : "<%=Constants.BrandsList.Pantaloons%>",
		Paypal : "<%=Constants.BrandsList.Paypal%>",
		Shoppers_Stop : "<%=Constants.BrandsList.Shoppers_Stop%>",
		UNICEF : "<%=Constants.BrandsList.UNICEF%>",
		WWF : "<%=Constants.BrandsList.WWF%>",
}

var _const_couponStatus = {

		ACTIVE : "<%=Constants.couponStatus.ACTIVE%>",
		EXPIRED : "<%=Constants.couponStatus.EXPIRED%>",
		SOLD : "<%=Constants.couponStatus.SOLD%>",
		USEDBYMYSELF : "<%=Constants.couponStatus.USEDBYSELF%>",		
}

<%-- var _bankAccountTypes ={
		savings_account : "<%=Constants.BankAccountTypes.SAVINGS%>",
		current_account : "<%=Constants.BankAccountTypes.CURRENT%>",
		recurring_account : "<%=Constants.BankAccountTypes.RECURRING%>",		
}

 --%>
</script>


</body>
</html>