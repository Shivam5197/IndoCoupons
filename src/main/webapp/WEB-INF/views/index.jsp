<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>IndoCoupons</title>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- Sweet Alert CDN --%>
<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.min.css'></link> 
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<%-- Sweet Alert CDN --%>

<link type="text/css" href="<s:url value="/CSS/registerLogin.css"/>" rel="stylesheet">
<!-- /indoCoupon_02/src/main/resources/static/CSS/.css --> 
<!-- Font Icon -->
<!-- <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">
 -->
<!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous"> -->

<!-- Font Awesome -->
<link
  href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
  rel="stylesheet"
/>
<!-- Google Fonts -->
<link
  href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
  rel="stylesheet"
/>
<!-- MDB -->
<link
  href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/3.11.0/mdb.min.css"
  rel="stylesheet"
/>

<!-- Internal JS files -->
<script type="text/javascript" charset="utf8"
	src="<s:url value="/JS/UtilsJs/MasterAjax.js"/>"></script>
<script type="text/javascript" charset="utf8"
	src="<s:url value="/JS/UtilsJs/utils.js"/>"></script>
<!-- Internal JS files -->
<script type="text/javascript">
	var global_contextPath = "${pageContext.request.contextPath}";
</script>

</head>
<body>

<div class="container">
  <section class="mx-auto my-5" style="max-width: 23rem;">
      
    <div class="card card-form mt-2 mb-4">
      <div class="card-body rounded-top pink darken-4">
        <h3 class="font-weight-bold text-center text-uppercase text-white my-4">Sign up</h3>
        <form class="pb-5 px-2" _lpchecked="1">
          <!-- Full name -->
          <div class="d-flex justify-content-start align-items-center mb-4">
            <i class="far fa-user fa-lg text-white fa-fw me-3"></i>
            <div class="form-outline form-white w-100">
              <input type="text" id="form1Example1" class="form-control" />
              <label class="form-label" for="form1Example1">Full name</label>
            </div>
          </div>
          <!-- Username -->
          <div class="d-flex justify-content-start align-items-center mb-4">
            <i class="far fa-hand-point-right fa-lg text-white fa-fw me-3"></i>
            <div class="form-outline form-white w-100">
              <input type="text" id="form1Example2" class="form-control" data-mdb-toggle="tooltip" title="Username Must be unique" />
              <label class="form-label" for="form1Example2">User name</label>
            </div>
          </div>
          <!-- Email -->
          <div class="d-flex justify-content-start align-items-center mb-4">
            <i class="far fa-envelope fa-lg text-white fa-fw me-3"></i>
            <div class="form-outline form-white w-100">
              <input type="text" id="form1Example3" class="form-control" />
              <label class="form-label" for="form1Example3">E-mail</label>
            </div>
          </div>
          <!-- Telephone -->
          <div class="d-flex justify-content-start align-items-center mb-4">
            <i class="fas fa-phone fa-lg text-white fa-fw me-3"></i>
            <div class="form-outline form-white w-100">
              <input type="tel" id="form1Example3" class="form-control" />
              <label class="form-label" for="form1Example3">Phone number </label>
            </div>
          </div>

          <div class="d-flex justify-content-start align-items-center pb-3">
            <i class="far fa-star fa-lg text-white fa-fw me-3"></i>
            <div class="form-outline form-white w-100">
              <input type="password" id="form1Example4" class="form-control" />
              <label class="form-label" for="form1Example4">Password</label>
              <div class="form-helper text-white">At least 8 characters and 1 digit</div>
            </div>
          </div>
        </form>
      </div>
      <div class="card card-form-2 mb-0 z-depth-0">
        <div class="card-body">
          <form class="text-center">
            <button class="btn btn-outline-danger btn-rounded btn-block my-4 z-depth-0"
              type="submit">Sign in</button>
             <hr>
            <p>Already our user
              <em>please</em>
              <a class="pink-accent-text" href="indoCoupon/v1/login" target="_blank">Login</a> 
          </form>
        </div>
      </div>
    </div>
  </section>
</div>


<!-- MDB -->
<script
  type="text/javascript"
  src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/3.11.0/mdb.min.js"
></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<%-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script> --%>
</body>
</html>






















<!-- <script>
function validateLogin(){
	console.log("Validate MEthod");
	let formData = new FormData();

	formData.append("userName", $("#userNameLog").val());
	formData.append("password", $("#passwordLog").val());
	
	var obj = new MasterAjax();
	obj.requestType = "POST";
	obj.url = "indoCoupon/v1/validateUser";
	obj.data = formData;
	obj.contentType = false;
    obj.processData = false;
	obj.dataType= "application/json";
	obj.requestData(function(responseData){
		if(responseData.status == "OK" || responseData.status == "ok"){
		let user = JSON.parse(responseData.data);
		swal({
				  title: "Congrats !",
  				  text: responseData.message,
  				  icon: "success",
 				  button: "OK",
		});	
		}else{
			console.log(responseData)
		swal({
				  title: "Failed !",
  				  text: responseData.message,
  				  icon: "warning",
 			   	 dangerMode: true,	
				  button: "OK",
		});	
		}
	});	
	
}
</script> -->