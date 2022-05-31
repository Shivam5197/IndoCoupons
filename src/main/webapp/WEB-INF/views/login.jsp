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

<%-- <%@include file="/WEB-INF/views/Utils/toIncludeConstant.jsp"%>
 --%>

<link type="text/css" href="<s:url value="/CSS/inputField.css"/>"
	rel="stylesheet">

<script src="https://use.fontawesome.com/4803dd6473.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.js"></script>


<!-- Internal JS files -->
<script type="text/javascript" charset="utf8"
	src="<s:url value="/JS/UtilsJs/MasterAjax.js"/>"></script>
<script type="text/javascript" charset="utf8"
	src="<s:url value="/JS/UtilsJs/utils.js"/>"></script>
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
	height: 70vh;
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

	<div class="modal fade rounded " id="main-model">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">

				<!--  Modal Header     -->
				<div class="modal-header p-3">
					<h6 class="modal-title" id="main-model-title">
						<i class="fas fa-table text-primary"></i> Select Table Columns
					</h6>
<!-- 					<button type="button" class="close" data-dismiss="modal">&times;</button> -->
					<button style="color: #fff;" type="button" class="close"
						onclick="resetPosition()" data-dismiss="modal">
						<i class="fas fa-times"></i>
					</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body" id="main-model-body">Please wait.....
				</div>

				<!--  Modal footer -->
				<div class="modal-footer">
					<div id="main-modal-handler"></div>
					<button type="button" class="btn btn-secondary"
						onclick="resetPosition()" data-dismiss="modal">
						<span id="main-model-close-btn-text">Close</span>
					</button>
				</div>

			</div>
		</div>
	</div>





	<nav class="navbar fixed-top sticky-top"
		style="background-color: #0a0a4b; position: fixed;">
		<div class="container-fluid">
			<a class="navbar-brand" href="/indoCoupon/v1/home"> <img
				src="<s:url value="/images/logo.jpg"/>" alt="" height="50"
				class="d-inline-block align-text-top">
			</a>
		</div>
	</nav>

	<div class="container-fluid">
		<div class="logincard card shadow-lg p-3 bg-white rounded">
			<div class="row">
				<div class="col-md-12">
					<div class="header">Sign In</div>
				</div>
			</div>

			<div class="row mt-2">
				<div class="col-md-12">
					<div class="input_div">
						<input type="text" class="inputTxt" id="userNameLog"
							name="userNameLog" required="required" placeholder=""> <label
							for="" class="inputlab">User Name</label>
					</div>
				</div>
				<div class="col-md-12">
					<div class="input_div">
						<input type="password" class="inputTxt" id="passwordLog"
							name="passwordLog" required="required" placeholder=""> <label
							for="" class="inputlab">Password</label>
					</div>
				</div>
			<div class="col-md-12">
					<div class="input_div">
			<input type="checkbox" onclick="showPassword()"> Show Password
					</div>
				</div>
				
			</div>
			<div class="row">
				<div class="col-md-12"></div>
				<div class="col-md-12 mt-1">
					<a onclick="forgotPassWordPopup();"  
						style="cursor: pointer; color: blue;">Forgot Password ??</a>
				</div>
				<div class="col-md-12 mt-1">
					New to IndoCoupon? <a class="font-weight-bold anchor-link"
						href="/indoCoupon/v1/sign-up" style="cursor: pointer;">Sign Up</a>
					here.
				</div>
			</div>

			<button type="submit" onclick="validateLogin();"
				class="btn btn-outline-primary mt-2">Login</button>

		</div>
	</div>


	<script>
	
	function showPassword(){
		  var x = document.getElementById("passwordLog");
		  if (x.type === "password") {
		    x.type = "text";
		  } else {
		    x.type = "password";
		  }
	}
	
	function forgotPassWordPopup(){
		console.log("Check UI ");
		let header = "<h4>Forgot Password </h4>";
	 	let ui = "<div class=\"col-md-12\">"
		+"<div class=\"row mt-2\">"
		+"<div class=\"col-md-12\">"
		+"<div class=\"input_div\">"
		+"<input type=\"text\" class=\"inputTxt\" id=\"userNameFLog\""+
			"name=\"userNameLog\" required=\"required\" placeholder=\"\"> <label"+
			"for=\"\" class=\"inputlab\">User Name</label>"+
	"</div>"+
	"</div>"+
	 	"</div>";

	    let footer =  "<button type=\"submit\" onclick=\"sendForgotPasswordRequest();\" class=\"btn btn-info\">Send Request</button>";
	    
	    let popUp = new MainPopUpModal(header, ui, footer);
	    popUp.show();
	}
	

	function sendForgotPasswordRequest(){		
	let name =	$("#userNameFLog").val()
	
	if(name == null && name == ""){
			swal({
					title : "Failed !",
					text : "Please Enter couponValue",
					icon : "warning",
					dangerMode : true,
					button : "OK",
			});
		}
	
	var ajaxObject = new MasterAjax();
	ajaxObject.requestType = "POST";
	ajaxObject.url = "indoCoupon/v1/sendForgotPasswordto/"+name+"";
	ajaxObject.contentType = false;
	ajaxObject.enctype = false;
	ajaxObject.requestData(function(responseData) {
		if (responseData.status == "OK" || responseData.status == "ok" ) {
			swal({
				title : "success !",
				text : responseData.message,
				icon : "success",
				button : "OK",
		});
		    let popUp = new MainPopUpModal(header, ui, footer);
		    popUp.hide();

		} else {
			swal({
				title : "Failed !",
				text : responseData.message,
				icon : "warning",
				dangerMode : true,
				button : "OK",
		});

		}
	});
}
	
		function validateLogin() {
			let formData = new FormData();

			formData.append("userName", $("#userNameLog").val());
			formData.append("password", $("#passwordLog").val());
			var obj = new MasterAjax();
			obj.requestType = "POST";
			obj.url = "indoCoupon/v1/validateUser";
			obj.data = formData;
			obj.contentType = false;
			obj.processData = false;
			obj.dataType = "application/json";
			obj.requestData(function(responseData) {
						//		console.log("REspons :  : "+JSON.parse(responseData.data));
						if (responseData.status == "OK"
								|| responseData.status == "ok") {
							let user =JSON.parse(responseData.data);
							swal({
								title : "Congrats !",
								text : responseData.message,
								icon : "success",
								button : "OK",
							});

							sleep(5000);
//							console.log("User role from backend : " + user.role)
//							console.log("User role from frondEnd : " + _const_userRoles.slt_admin)
 							if (user.role == _const_userRoles.slt_admin) {
								window.location = '/indoCoupon/v1/adminDashboard';
							} else {
								window.location = '/indoCoupon/v1/home';
							}
 
						} else {
							console.log(responseData)
							swal({
								title : "Failed !",
								text : responseData.message,
								icon : "warning",
								dangerMode : true,
								button : "OK",
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