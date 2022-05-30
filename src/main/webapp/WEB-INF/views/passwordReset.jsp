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


<link type="text/css" href="<s:url value="/CSS/inputField.css"/>"
	rel="stylesheet">


<!-- Internal JS files -->
<script type="text/javascript" charset="utf8"
	src="<c:url value="/JS/UtilsJs/MasterAjax.js"/>"></script>
<script type="text/javascript" charset="utf8"
	src="<c:url value="/JS/UtilsJs/utils.js"/>"></script>
<script type="text/javascript" charset="utf8"
	src="<c:url value="/JS/indexJs.js"/>"></script>
<script type="text/javascript" charset="utf8"
	src="<c:url value="/JS/payment.js"/>"></script>

<!-- Internal JS files -->

<!-- Input field CSS to be Applied all over the project -->
<%-- <link type="text/css" href="<s:url value="/CSS/Header/inputField.css"/>" rel="stylesheet">
 --%>

<script type="text/javascript">
	var global_contextPath = "${pageContext.request.contextPath}";
</script>

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

    <nav class="navbar fixed-top sticky-top" style="background-color: #0a0a4b; position: fixed;">
        <div class="container-fluid">
          <a class="navbar-brand" href="/indoCoupon/v1/home">				
			 <img src="<s:url value="/images/logo.jpg"/>" alt=""  height="50" class="d-inline-block align-text-top">

          </a>
        </div>
      </nav>

	<div class="container"  style="margin-top: 11vh;">
		<div class="card shadow-lg text-center ">
			<div class="card-header">Reset Your Password</div>
			<div class="card-body">

				<form class="resetForm">
					<div class="row">
						<div class="col-md-12">
							<div class="input_div ">
								<input type="password" class="inputTxt" id="newPassword"
									name="newPassword" required="required" placeholder="">
								<label for="" class="inputlab">New Password</label>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="input_div">
								<!-- style="margin-top: 11%;" -->
								<input type="text" class="inputTxt" id="confirmPass"
									name="confirmPass" required="required"
									placeholder=""> <label
									for="" class="inputlab">Confirm Password</label>
							</div>
						</div>
					</div>

					<div class="buot">
						<button type="button" onclick="setPass(${userId});" class="btn btn-primary float-right" >Reset
							Password</button>
					</div>
				</form>

			</div>
			<div class="card-footer text-muted">-------</div>
		</div>
	</div>


 

<script>

function setPass(userId){	
	
	  if (document.getElementById('newPassword').value ==
		    document.getElementById('confirmPass').value){

			var pass= document.getElementById("newPassword").value;
			var obj = new MasterAjax();
			obj.requestType = "POST";
			obj.url = "indoCoupon/v1/reset/"+userId+"";
			obj.data = pass;
			obj.contentType = false;
			obj.processData = false;
			obj.dataType= "application/json";
			obj.requestData(function(responseData) {
				if (responseData.status == "OK" || responseData.status == "ok" ) {
					console.log(responseData.data);
					swal({
						  title: "Congrats !",
		  				  text: responseData.message,
		  				  icon: "success",
		 				  button: "OK",
					});
					
					let LoginBtn = "<button type=\"button\" href=\"/indoCoupon/v1/login\" class=\"btn btn-primary\">Go to Login Page</button>";
					
					let popUp = new MainPopUpModal("","You will be redirected to Login page",LoginBtn);
					popUp.show();
					
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
		  
	  }else{
			swal({
				title : "Failed !",
				text : "Confirm password do not match with New Password",
				icon : "warning",
				dangerMode : true,
				button : "OK",
		});
			
	  }
}

</script>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
		
</body>
</html>