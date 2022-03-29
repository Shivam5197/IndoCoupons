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
<link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

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

    <div class="main">
        <!-- Sing in  Form -->
        <section class="sign-in">
            <div class="container">
                <div class="signin-content">
                    <div class="signin-image">
                        <figure><img src="images/signin-image.jpg" alt="sing in image"></figure>
                        <a href="#" class="signup-image-link">Create an account</a>
                    </div>

                    <div class="signin-form">
                        <h2 class="form-title">Sign up</h2>
                        <form method="POST" class="register-form" id="login-form">
                            <div class="form-group">
                                <label for="your_name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="text" name="your_name" id="your_name" placeholder="Your Name"/>
                            </div>
                            <div class="form-group">
                                <label for="your_pass"><i class="zmdi zmdi-lock"></i></label>
                                <input type="password" name="your_pass" id="your_pass" placeholder="Password"/>
                            </div>
                            <div class="form-group">
                                <input type="checkbox" name="remember-me" id="remember-me" class="agree-term" />
                                <label for="remember-me" class="label-agree-term"><span><span></span></span>Remember me</label>
                            </div>
                            <div class="form-group form-button">
                                <input type="submit" name="signin" id="signin" class="form-submit" value="Log in"/>
                            </div>
                        </form>
                        <div class="social-login">
                            <span class="social-label">Or login with</span>
                            <ul class="socials">
                                <li><a href="#">Registered Email</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </section>

</div>    

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
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