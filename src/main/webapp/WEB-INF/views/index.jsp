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

<style>
.text-center{
	text-align: center;
}

.cImg{
  float: right;
    width: 100%;
    height: 23vh;
    background-size: cover;
}

.card-img-pos{
  width: 85%;
    margin-left: auto;
    margin-right: auto;
    padding: 8px;
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


<div class="row title text-center" style="margin-top: 2vh;">
  <div class="col-md-3"></div>
  <div class="col-md-7">
  <h1 style="font-family: serif; color: #0a0a4b;"> You Shop You Save We Give </h1>
</div>
<div class="col-md-2">

<div class="input_div">
  <select class="form-select" id="searchBrandDropDown" onchange="searchByBrand();" name="searchBrandDropDown" aria-label="Default select example">
  	<option selected>Search by Brand</option>
                   <option value= "${AMAZON}" >Amazon</option>
                   <option value= "${Amnesty_International}" >Amnesty International</option>
                   <option value= "${Big_Bazar}" >Big Bazar</option>
                   <option value= "${Eco_Matcher}" >Eco Matcher</option>
                   <option value= "${Flipkart}" >Flipkart</option>
                   <option value= "${Mastercard}" >Mastercard</option>
                   <option value= "${Myntra}" >Myntra</option>
                   <option value= "${Pantaloons}" >Pantaloons</option>
                   <option value= "${Paypal}" >Paypal</option>
                   <option value= "${Shoppers_Stop}" >Shoppers Stop</option>
                   <option value= "${UNICEF}" >UNICEF</option>
                   <option value= "${WWF}" >WWF</option>
		</select>
		</div>
  </div>
</div>

	  <div  class="container card shadow" style="margin-top: 5vh;background-color: #e2dada;">

		<div class="row coupon-Cards">
		</div>

	  </div>

<script>

/* $('select[name="searchBrandDropDown"]').change(function(){
	var brand = $(this).val();  
	console.log(brand)
	var ajaxObject = new MasterAjax();
	ajaxObject.requestType = "POST";
	ajaxObject.url = "coupon/listbyBrand/"+brand+";
	ajaxObject.contentType = false;
	ajaxObject.enctype = false;
	ajaxObject.requestData(function(responseData) {
		if (responseData.status == "OK" || responseData.status == "ok" ) {
		let brandCoupons = JSON.parse(responseData.data);	
		console.log(brandCoupons);
		} else {
			console.log(responseData);
//			checkUser(null);
		}
	});


});
 */
 
 function searchByBrand(){
	 let value = $("#searchBrandDropDown").val();
		var ajaxObject = new MasterAjax();
		ajaxObject.requestType = "GET";
		ajaxObject.url = "coupon/listbyBrand/"+value+"";
		ajaxObject.contentType = false;
		ajaxObject.enctype = false;
		ajaxObject.requestData(function(responseData) {
			if (responseData.status == "OK" || responseData.status == "ok" ) {
			let brandCoupons = JSON.parse(responseData.data);	
			console.log(brandCoupons);
			homecardsbrandWise(brandCoupons);
			} else {
				console.log(responseData);
				homecardsbrandWise(null);
			}
		});
 }
 
 
</script>

<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
		
</body>
</html>