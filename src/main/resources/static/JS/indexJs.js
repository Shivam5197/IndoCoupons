$(document).ready(function(){
	
	var ajaxObject = new MasterAjax();
	ajaxObject.requestType = "POST";
	ajaxObject.url = "indoCoupon/v1/checkUser";
	ajaxObject.contentType = false;
	ajaxObject.enctype = false;
	ajaxObject.requestData(function(responseData) {
		if (responseData.status == "OK" || responseData.status == "ok" ) {
		let user = JSON.parse(responseData.data);	
			checkUser(user);
		} else {
			console.log(responseData);
			checkUser(null);
		}
	});
});


function checkUser(user){
	
	let nav ="";
	if(user !=null && user !=""){
//	console.log("USER:  "+ user.role + "Constant :" +  _const_userRoles.slt_admin);		
	if(user.role != _const_userRoles.slt_admin){
	nav = `
			<a style="color:white; font-weight: bold;">`+user.fullName+`</a>
			<a href="/indoCoupon/v1/customer_account_settings"><svg xmlns="http://www.w3.org/2000/svg" width="70" height="40" fill="White" class="bi bi-person-circle" viewBox="0 0 16 16">
				<path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
				<path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
			  </svg></a>	

	`;
	}else{
			nav = `
			<a style="color:white; font-weight: bold;">`+user.fullName+`</a>
			<a onclick="adminAccountpopup(${user.userId})"><svg xmlns="http://www.w3.org/2000/svg" width="70" height="40" fill="White" class="bi bi-person-circle" viewBox="0 0 16 16">
				<path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
				<path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
			  </svg></a>	

	`;
	}		
	}else{
	nav = `
	        <a type="button" href="/indoCoupon/v1/sign-up" class="btn btn-outline-primary">Sign UP</a>
            <a type="button" href="/indoCoupon/v1/login" class="btn btn-outline-primary">Login</a>
	`;	
	}
	$("#login-SignUp_Button_Div").html(nav);
	activeCoupons();
}

function adminAccountpopup(userId){

	var ajaxObject = new MasterAjax();
	ajaxObject.requestType = "POST";
	ajaxObject.url = "indoCoupon/v1/checkUser";
	ajaxObject.contentType = false;
	ajaxObject.enctype = false;
	ajaxObject.requestData(function(responseData) {
		if (responseData.status == "OK" || responseData.status == "ok" ) {
		let user = JSON.parse(responseData.data);	
	
	let header = "Hey "+user.fullName+"";
	
	let body = "<div class=\"row\">"
				+"<div class=\"col-md-12\">"
				+"<div class=\"header\"> Details </div>"
				+"</div>"
			+"</div>"
			+"<div class=\"row mt-2\">"
			  +"<div class=\"col-md-12\">"
			  +"<div class=\"input_div\">"
			  +"<input type=\"text\" class=\"inputTxt\" id=\"fullName\" name=\"fullName\" required=\"required\" value=\""+user.fullName+"\" />"
			  +"<label for=\"\" class=\"inputlab\">Full Name</label>"
			  +"</div>"
			  +"</div>"
			  +"<div class=\"col-md-12\">"
			  +"<div class=\"input_div\">"
			  +"<input type=\"text\" class=\"inputTxt\" id=\"userName\" name=\"userName\" required=\"required\" value=\""+user.userName+"\" disabled>"
			  +"<label for=\"\" class=\"inputlab\">User Name</label>"
			  +"</div>"
			  +"</div>"
			  +"<div class=\"col-md-12\">"
				+"<div class=\"input_div\">"
				+"<input type=\"email\" class=\"inputTxt\" id=\"email\" name=\"email\" required=\"required\" value=\""+user.email+"\" />"
				+"<label for=\"\" class=\"inputlab\">Email</label>"
				+"</div>"
				+"</div>"
				+"<div class=\"col-md-12\">"
					+"<div class=\"input_div\">"
					+"<input type=\"tel\" class=\"inputTxt\" id=\"phoneNumber\"  name=\"phoneNumber\" required=\"required\" value=\""+user.phoneNumber+"\" />"
					+"<label for=\"\" class=\"inputlab\">Phone Number</label>"
					+"</div>"
					+"</div>"
			  +"</div>"
			  +"<div class=\"row\">"
			  +"<div class=\"col-md-12\">"
			  +"</div>"
			  +"</div>"

	let footer = "<a type=\"button\" href=\"/indoCoupon/v1/logout\" class=\"btn btn-danger mr-2\">Logout</a>"
	+"<button type=\"submit\" onclick=\"updateAdminProfile("+user.userId+")\" class=\"btn btn-outline-primary mr-2\">Save Details</button>";
	
	let popUp = new MainPopUpModal(header, body, footer);
    popUp.show();			
		} else {
			console.log(responseData);
		}
	});
}

function updateAdminProfile(userId){	
	let formData = new FormData();
	formData.append("fullName", $("#fullName").val());
	formData.append("email", $("#email").val());
	formData.append("phoneNumber", $("#phoneNumber").val());

	var obj = new MasterAjax();
	obj.requestType = "POST";
	obj.url = "indoCoupon/v1/updateDetails/"+userId;
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
			let popUp = new MainPopUpModal();
    popUp.hide();			

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




function activeCoupons(){
	
	var ajaxObject = new MasterAjax();
	ajaxObject.requestType = "POST";
	ajaxObject.url = "coupon/getActiveCoupons";
	ajaxObject.contentType = false;
	ajaxObject.enctype = false;
	ajaxObject.requestData(function(responseData) {
		if (responseData.status == "OK" || responseData.status == "ok" ) {
		let couponList = JSON.parse(responseData.data);	
			getActiveCouponListCard(couponList);
		} else {
			console.log(responseData);
			Swal.fire({
			  icon: 'error',
			  title: 'Oops...',
			  text: 'Something went wrong!',
			  footer: '<a href="">Report to Admin?</a>'
			})
		}
	});
}

function getActiveCouponListCard(couponList){
	
	let cards = ``;
	
	if(couponList != 0||couponList !=null){
	for (var i = 0; i < couponList.length; i++) {

	cards += `<div class="col-lg-4 mb-3 mt-4">
            <div class="card">
              <div class="card-img-pos">`;
				
//              <img src="images/flipkart.png" alt="" class="card-img-top cImg">
				if (couponList[i].brand == _const_BrandsList.AMAZON) {
						cards += 	`<img src="/images/amazon.png" alt="" class="card-img-top cImg">`;
					}else if(couponList[i].brand == _const_BrandsList.Big_Bazar){
						cards += 	`<img src="/images/big_bazar.png" alt="" class="card-img-top cImg">`;						
					}else if(couponList[i].brand == _const_BrandsList.Flipkart){
						cards += 	`<img src="/images/flipkart.png" alt="" class="card-img-top cImg">`;						
					}else if(couponList[i].brand == _const_BrandsList.Mastercard){
						cards += 	`<img src="/images/mastercard.png" alt="" class="card-img-top cImg">`;						
					}else if(couponList[i].brand == _const_BrandsList.Myntra){
						cards += 	`<img src="/images/myntra.png" alt="" class="card-img-top cImg">`;						
					}else if(couponList[i].brand == _const_BrandsList.Pantaloons){
						cards += 	`<img src="/images/pantaloons.jpg" alt="" class="card-img-top cImg">`;						
					}else if(couponList[i].brand == _const_BrandsList.Paypal){
						cards += 	`<img src="/images/paypal.png" alt="" class="card-img-top cImg">`;						
					}else if(couponList[i].brand == _const_BrandsList.Shoppers_Stop){
						cards += 	`<img src="/images/shoperStop.png" alt="" class="card-img-top cImg">`;						
					}else{
						cards += 	`<img src="#" alt="No Image Found" class="card-img-top cImg">`;						
					}
					
        cards += `</div>
              <div class="card-body">`;

				if (couponList[i].brand == _const_BrandsList.AMAZON) {
						cards += 	`<h5 class="card-title"> Amazon </h5>`;
					}else if(couponList[i].brand == _const_BrandsList.Big_Bazar){
						cards += 	`<h5 class="card-title"> Big Bazar </h5>`;						
					}else if(couponList[i].brand == _const_BrandsList.Flipkart){
						cards += 	`<h5 class="card-title"> Flipkart </h5>`;						
					}else if(couponList[i].brand == _const_BrandsList.Mastercard){
						cards += 	`<h5 class="card-title"> Mastecard </h5>`;						
					}else if(couponList[i].brand == _const_BrandsList.Myntra){
						cards += 	`<h5 class="card-title"> Myntra </h5>`;						
					}else if(couponList[i].brand == _const_BrandsList.Pantaloons){
						cards += 	`<h5 class="card-title"> Pantaloons </h5>`;						
					}else if(couponList[i].brand == _const_BrandsList.Paypal){
						cards += 	`<h5 class="card-title"> Paypal </h5>`;						
					}else if(couponList[i].brand == _const_BrandsList.Shoppers_Stop){
						cards += 	`<h5 class="card-title"> Shopper's Stop </h5>`;						
					}

             cards += `<p class="card-text">
                  <p>You will be saving <i class="fas fa-rupee-sign"></i> <a style="color: red; font-weight: 800;"> ${couponList[i].couponValue} </a> on your purchase by just paying <i class="fas fa-rupee-sign"></i> <a style="color: forestgreen; font-weight: 800;"> ${couponList[i].couponPrice} </a></p>
                  <p>Expiry Date : ${couponList[i].couponExpiryDate}</p>
                </p>
               <a onclick="paymentsPage(${couponList[i].couponId});" class="btn btn-outline-success btn-sm">Use Coupon</a>
               <a onclick="usagePopup();" class="btn  btn-outline-danger btn-sm">How to use <i class="fa fa-info-circle" aria-hidden="true"></i></a>
              </div>
             </div>
            </div>`;
		}
	}else{
	cards +=`<h1>No Coupons added yet !!</h1>`;
	}
	$(".coupon-Cards").html(cards);

}//End of Function

function usagePopup(){
	
	let header = "Instructions to use Coupons ";
	let body = "	<ul>"
			+ "		<li><h5 style=\"color: #514a9b; font-weight: bold;\">Step 1</h5>If you have not register Please Registered.</li>"
			+ "		<li><h5 style=\"color: #514a9b; font-weight: bold;\">Step 2</h5>Login in application with valid username and password.</li>"
			+ "		<li><h5 style=\"color: #514a9b; font-weight: bold;\">Step 3</h5>Search for the Coupon you want !!</li>"
			+ "		<li><h5 style=\"color: #514a9b; font-weight: bold;\">Step 4</h5>Click on Use Coupon Button to make it yours</li>"
			+ "		<li><h5 style=\"color: #514a9b; font-weight: bold;\">Step 5</h5>You have three options to pay "
			+ "		<ul>"
			+ "			<li>Credit/Debit card (Coming soon)</li>"
			+ "			<li>Wallet or UPI </li>"
			+ "			<li>Net Banking (Coming Soon)</li>"
			+ "		</ul>"
			+ "		</li>"
			+ "		<li><h5 style=\"color: #514a9b; font-weight: bold;\">Step 6</h5> Once the amount recieved by the admin You will recieve the coupon Details on your registered email account !</li>"
			+ "	</ul>"
			+ "<p>Still Have Doubts?</p>"
			+ "<p>Chat with admin. Click on Whatsapp Icon <a href=\"http://wa.me/+919818535782\" target=\"_blank\"><svg style=\" max-width: 20px;\" xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 448 512\"><path d=\"M380.9 97.1C339 55.1 283.2 32 223.9 32c-122.4 0-222 99.6-222 222 0 39.1 10.2 77.3 29.6 111L0 480l117.7-30.9c32.4 17.7 68.9 27 106.1 27h.1c122.3 0 224.1-99.6 224.1-222 0-59.3-25.2-115-67.1-157zm-157 341.6c-33.2 0-65.7-8.9-94-25.7l-6.7-4-69.8 18.3L72 359.2l-4.4-7c-18.5-29.4-28.2-63.3-28.2-98.2 0-101.7 82.8-184.5 184.6-184.5 49.3 0 95.6 19.2 130.4 54.1 34.8 34.9 56.2 81.2 56.1 130.5 0 101.8-84.9 184.6-186.6 184.6zm101.2-138.2c-5.5-2.8-32.8-16.2-37.9-18-5.1-1.9-8.8-2.8-12.5 2.8-3.7 5.6-14.3 18-17.6 21.8-3.2 3.7-6.5 4.2-12 1.4-32.6-16.3-54-29.1-75.5-66-5.7-9.8 5.7-9.1 16.3-30.3 1.8-3.7.9-6.9-.5-9.7-1.4-2.8-12.5-30.1-17.1-41.2-4.5-10.8-9.1-9.3-12.5-9.5-3.2-.2-6.9-.2-10.6-.2-3.7 0-9.7 1.4-14.8 6.9-5.1 5.6-19.4 19-19.4 46.3 0 27.3 19.9 53.7 22.6 57.4 2.8 3.7 39.1 59.7 94.8 83.8 35.2 15.2 49 16.5 66.6 13.9 10.7-1.6 32.8-13.4 37.4-26.4 4.6-13 4.6-24.1 3.2-26.4-1.3-2.5-5-3.9-10.5-6.6z\"/></svg></a></p>";

	let footer ="";

	let popUp = new MainPopUpModal(header, body, footer);
    popUp.show();			

}


function homecardsbrandWise(couponList){
		
	let cards = ``;
	
	if(couponList != 0||couponList !=null){
	for (var i = 0; i < couponList.length; i++) {

	cards += `<div class="col-lg-4 mb-3 mt-4">
            <div class="card">
              <div class="card-img-pos">`;
				
//              <img src="images/flipkart.png" alt="" class="card-img-top cImg">
				if (couponList[i].brand == _const_BrandsList.AMAZON) {
						cards += 	`<img src="/images/amazon.png" alt="" class="card-img-top cImg">`;
					}else if(couponList[i].brand == _const_BrandsList.Big_Bazar){
						cards += 	`<img src="/images/big_bazar.png" alt="" class="card-img-top cImg">`;						
					}else if(couponList[i].brand == _const_BrandsList.Flipkart){
						cards += 	`<img src="/images/flipkart.png" alt="" class="card-img-top cImg">`;						
					}else if(couponList[i].brand == _const_BrandsList.Mastercard){
						cards += 	`<img src="/images/mastercard.png" alt="" class="card-img-top cImg">`;						
					}else if(couponList[i].brand == _const_BrandsList.Myntra){
						cards += 	`<img src="/images/myntra.png" alt="" class="card-img-top cImg">`;						
					}else if(couponList[i].brand == _const_BrandsList.Pantaloons){
						cards += 	`<img src="/images/pantaloons.jpg" alt="" class="card-img-top cImg">`;						
					}else if(couponList[i].brand == _const_BrandsList.Paypal){
						cards += 	`<img src="/images/paypal.png" alt="" class="card-img-top cImg">`;						
					}else if(couponList[i].brand == _const_BrandsList.Shoppers_Stop){
						cards += 	`<img src="/images/shoperStop.png" alt="" class="card-img-top cImg">`;						
					}
					
        cards += `</div>
              <div class="card-body">`;

				if (couponList[i].brand == _const_BrandsList.AMAZON) {
						cards += 	`<h5 class="card-title"> Amazon </h5>`;
					}else if(couponList[i].brand == _const_BrandsList.Big_Bazar){
						cards += 	`<h5 class="card-title"> Big Bazar </h5>`;						
					}else if(couponList[i].brand == _const_BrandsList.Flipkart){
						cards += 	`<h5 class="card-title"> Flipkart </h5>`;						
					}else if(couponList[i].brand == _const_BrandsList.Mastercard){
						cards += 	`<h5 class="card-title"> Mastecard </h5>`;						
					}else if(couponList[i].brand == _const_BrandsList.Myntra){
						cards += 	`<h5 class="card-title"> Myntra </h5>`;						
					}else if(couponList[i].brand == _const_BrandsList.Pantaloons){
						cards += 	`<h5 class="card-title"> Pantaloons </h5>`;						
					}else if(couponList[i].brand == _const_BrandsList.Paypal){
						cards += 	`<h5 class="card-title"> Paypal </h5>`;						
					}else if(couponList[i].brand == _const_BrandsList.Shoppers_Stop){
						cards += 	`<h5 class="card-title"> Shopper's Stop </h5>`;						
					}

             cards += `<p class="card-text">
                  <p>You will be saving <i class="fas fa-rupee-sign"></i> <a style="color: red; font-weight: 800;"> ${couponList[i].couponValue} </a> on your purchase by just paying <i class="fas fa-rupee-sign"></i> <a style="color: forestgreen; font-weight: 800;"> ${couponList[i].couponPrice} </a></p>
                  <p>Expiry Date : ${couponList[i].couponExpiryDate}</p>
                </p>
               <a onclick="paymentsPage(${couponList[i].couponId});"  class="btn btn-outline-success btn-sm">Use Coupon</a>
               <a onclick="usagePopup();" class="btn  btn-outline-danger btn-sm">How to use <i class="fa fa-info-circle" aria-hidden="true"></i></a>
              </div>
             </div>
            </div>`;
		}
	}else{
	cards +=`<h1>No Coupons added yet !!</h1>`;
	}
	$(".coupon-Cards").html(cards);
}

	
function paymentsPage(couponList){
	console.log("Payment Called");
	var ajaxObject = new MasterAjax();
	ajaxObject.requestType = "POST";
	ajaxObject.url = "indoCoupon/v1/checkLoginUser";
	ajaxObject.contentType = false;
	ajaxObject.enctype = false;
	ajaxObject.requestData(function(responseData) {
	console.log()
		if (responseData.status == "OK" || responseData.status == "ok" ) {
			qrCodeUI(couponList);
		} else {
		Swal.fire({
			  icon: 'error',
			  title: 'Login Required',
			  text: 'You need to Login For using the coupons !',
			  footer: '<a href="/indoCoupon/v1/login">Login from Here</a>'
			})

		}
	});


	
	
}

