<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>IndoCoupon</title>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- <%@include file="/WEB-INF/views/Constants/Constatnt.jsp" %> --%>

<%@include file="/WEB-INF/views/Utils/toIncludeConstants.jsp"%>

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">

<%-- Sweet Alert CDN --%>
<link rel='stylesheet'
	href='https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.min.css'></link>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<%-- Sweet Alert CDN --%>

<link rel="shortcut icon" href="">
<%-- <%@include file="/WEB-INF/views/Utils/toIncludeConstant.jsp"%>
 --%>

<script src="https://use.fontawesome.com/4803dd6473.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.js"></script>


<!-- Internal JS files -->
<script type="text/javascript" charset="utf8"
	src="<c:url value="/JS/UtilsJs/MasterAjax.js"/>"></script>
<script type="text/javascript" charset="utf8"
	src="<c:url value="/JS/UtilsJs/utils.js"/>"></script>
<script type="text/javascript" charset="utf8"
	src="<c:url value="/JS/indexJs.js"/>"></script>
<script type="text/javascript" charset="utf8"
	src="<c:url value="/JS/accountSettings.js"/>"></script>

<!-- Internal JS files -->

<!-- Input field CSS to be Applied all over the project -->
<%-- <link type="text/css" href="<s:url value="/CSS/Header/inputField.css"/>" rel="stylesheet">
 --%>

<script type="text/javascript">
	var global_contextPath = "${pageContext.request.contextPath}";
</script>

<style>
.text-center {
	text-align: center;
}
</style>

</head>
<body>
	<nav class="navbar fixed-top sticky-top"
		style="background-color: #0a0a4b; position: fixed;">
		<div class="container-fluid">
			<a class="navbar-brand" href="#"> <img
				src="<s:url value="/images/logo.jpg"/>" alt="" height="50"
				class="d-inline-block align-text-top">

			</a>
			<form class="justify-content-end">
				<a type="button" href="#" class="btn btn-outline-primary">Logout</a>
			</form>
		</div>
	</nav>


	<div class="container card shadow" style="margin-top: 11vh;">
		<div class="row">
			<!-- 		<div class="col-md-12"> -->
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<nav class="nav nav-pills nav-justified" id="myTab">
					<a class="nav-link active" aria-current="page" href="#">General Settings</a> 
					<a class="nav-link" href="#">Reset Password</a> 
					<a class="nav-link" href="#">Manage Cards</a> 
					<a class="nav-link" href="#" tabindex="-1" aria-disabled="true">Coupons Purchased</a>
				</nav>
			</div>
			<div class="col-md-2"></div>
		</div>
	</div>

	<div class="container card shadow" style="margin-top: 5vh;">
		<div class="row">

		</div>
	</div>



	<script>
		var triggerTabList = [].slice.call(document
				.querySelectorAll('#myTab a'))
		triggerTabList.forEach(function(triggerEl) {
			var tabTrigger = new bootstrap.Tab(triggerEl)

			triggerEl.addEventListener('click', function(event) {
				event.preventDefault()
				tabTrigger.show()
			})
		})
	</script>


	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</body>
</html>