<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>IndoEra</title>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- <%@include file="/WEB-INF/views/Constants/Constatnt.jsp" %> --%>

<%-- <%@include file="/WEB-INF/views/Utils/toIncludeConstant.jsp" %> --%>

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

<%-- <%@include file="/WEB-INF/views/Utils/toIncludeConstant.jsp"%>
 --%>

<link type="text/css" href="<c:url value="/CSS/inputField.css"/>" rel="stylesheet">
<link type="text/css" href="<c:url value="/CSS/loader.css"/>" rel="stylesheet">


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

.text-center {
	text-align: center;
}

body {
	background-color: #0a0a4b;
}

.logincard {
	width: 60vw;
	height: 84vh;
	position: absolute;
	top: 0;
	bottom: 0;
	left: 0;
	right: 0;
	margin: auto;
}

.header {
	background-color: white;
	color: #0a0a4b;
	font-weight: 800;
	font-size: 6vh;
}
</style>

</head>
<body>
<div class="loader"></div>
    <nav class="navbar fixed-top sticky-top" style="background-color: #0a0a4b; position: fixed;">
        <div class="container-fluid">
          <a class="navbar-brand" href="/indoCoupon/v1/home"> <img
				src="<c:url value="/images/logo.jpg"/>" alt="" height="50"
				class="d-inline-block align-text-top"></a>
        </div>
      </nav>
	  
	  <div class="container-fluid">
		<div class="logincard card shadow-lg p-3 bg-white rounded">
			<div class="row">
				<div class="col-md-12">
					<div class="header">
						Sign UP
					</div>
				</div>
			</div>

			<div class="row mt-2">
			  <div class="col-md-12">
			  <div class="input_div">
			  <input type="text" class="inputTxt" id="fullName" name="fullName" required="required" placeholder="">
			  <label for="" class="inputlab">Full Name</label>
			  </div>
			  </div>
			  <div class="col-md-12">
			  <div class="input_div">
			  <input type="text" class="inputTxt" id="userName" name="userName" required="required" placeholder="">
			  <label for="" class="inputlab">User Name</label>
			  </div>
			  </div>
			  <div class="col-md-12">
				<div class="input_div">
				<input type="email" class="inputTxt" id="email" name="email" required="required" placeholder="">
				<label for="" class="inputlab">Email</label>
				</div>
				</div>
				<div class="col-md-12">
					<div class="input_div">
					<input type="tel" class="inputTxt" id="phoneNumber" name="phoneNumber" required="required" placeholder="">
					<label for="" class="inputlab">Phone Number</label>
					</div>
					</div>
					<div class="col-md-12">
						<div class="input_div">
						<input type="password" class="inputTxt" id="password" name="password" required="required" placeholder="">
						<label for="" class="inputlab">Password</label>
						</div>
						</div>

			  </div>
			  <div class="row">
			  <div class="col-md-12">
			  </div>
			  <div class="col-md-12 mt-1">
				Already a user
				<a class="font-weight-bold anchor-link"  href="/indoCoupon/v1/login" style="cursor: pointer;" >Sign In</a>
				here.
			  </div>
			  </div>
		
			<button type="submit" onclick="registerUser();" class="btn btn-outline-primary mt-2">Sign UP</button>;

		</div>
	  </div>

<script>
function registerUser(){
	console.log("Register User MEthod");

	let formData = new FormData();
	formData.append("fullName", $("#fullName").val());
	formData.append("userName", $("#userName").val());
	formData.append("email", $("#email").val());
	formData.append("phoneNumber", $("#phoneNumber").val());
	formData.append("password", $("#password").val());

	var obj = new MasterAjax();
	obj.requestType = "POST";
	obj.url = "indoCoupon/v1/saveUser";
	obj.data = formData;
	obj.contentType = false;
    obj.processData = false;
	obj.dataType= "application/json";
	obj.requestData(function(responseData){
		if(responseData.status == "OK" || responseData.status == "ok"){
		swal({
				  title: "Congrats !",
  				  text: responseData.message,
  				  icon: "success",
 				  button: "OK",
		});
		sleep(10000);
			window.location = '/indoCoupon/v1/login';
		
		}else{
			console.log(responseData)
			swal({
				  title: "OOPS !",
				  text: responseData.message,
				  icon: "Danger",
				  button: "OK",
		});

		}
	});	
}


</script>



	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</body>
</html>