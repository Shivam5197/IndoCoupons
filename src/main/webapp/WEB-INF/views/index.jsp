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

<%@include file="/WEB-INF/views/Utils/toIncludeConstants.jsp" %>

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">

<%-- Sweet Alert CDN --%>
<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.min.css'></link> 
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

<!-- Internal JS files -->

<!-- Input field CSS to be Applied all over the project -->
<%-- <link type="text/css" href="<s:url value="/CSS/Header/inputField.css"/>" rel="stylesheet">
 --%>

<script type="text/javascript">
	var global_contextPath = "${pageContext.request.contextPath}";
</script>

<style>
.text-center{
	text-align: center;
}

</style>

</head>
<body>
    <nav class="navbar fixed-top sticky-top" style="background-color: #0a0a4b; position: fixed;">
        <div class="container-fluid">
          <a class="navbar-brand" href="#">				
			 <img src="<s:url value="/images/logo.jpg"/>" alt=""  height="50" class="d-inline-block align-text-top">

          </a>
          <form class="justify-content-end" id="login-SignUp_Button_Div">
        </form>
        </div>
      </nav>
	  
	  <div class="row" style="margin-top: 11vh;">
		<div class="col text-center" style="border: 1px solid #d8cece; font-weight: bold;">
		 <a href="">Coupons</a> 
		</div>
		<div class="col text-center" style="border: 1px solid #d8cece; font-weight: bold;">
			<a href="">Stores</a> 
		</div>
		<div class="col text-center" style="border: 1px solid #d8cece; font-weight: bold;" >
			<a href="">Research/Tools</a> 
		</div>
	  </div>


	  <div class="title text-center" style="margin-top: 2vh;">
		  <h1 style="font-family: serif; color: #0a0a4b;"> You Shop You Save We Give </h1>
	  </div>

	  <div class="container card shadow" style="margin-top: 5vh;">
		<h2 class="text-center" style="color: #0a0a4b;"> Trending Coupons </h2>

		<table class="table mt-3">
			<tbody>
			  <tr>
				<th scope="row">Amazon</th>
				<td class="text-center">789456123455645</td>
				<td>500Rs</td>
				<td><input type="button" class="btn btn-primary" value="Get Pin"></td>
			  </tr>
			  <tr>
				<th scope="row">Flipkart</th>
				<td class="text-center">789456123455645</td>
				<td>500Rs</td>
				<td><input type="button" class="btn btn-primary" value="Get Pin"></td>
			  </tr>
			  <tr>
				<th scope="row">Nyka</th>
				<td class="text-center">789456123455645</td>
				<td>500Rs</td>
				<td><input type="button" class="btn btn-primary" value="Get Pin"></td>
			  </tr>
			  <tr>
				<th scope="row">Walmart</th>
				<td class="text-center">789456123455645</td>
				<td>500Rs</td>
				<td><input type="button" class="btn btn-primary" onclick="check()" value="Get Pin"></td>
			  </tr>

			</tbody>
		  </table>



	  </div>




	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
		
</body>
</html>