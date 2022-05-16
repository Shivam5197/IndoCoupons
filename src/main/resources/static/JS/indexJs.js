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
			<a href="/indoCoupon/v1/admin_account_settings"><svg xmlns="http://www.w3.org/2000/svg" width="70" height="40" fill="White" class="bi bi-person-circle" viewBox="0 0 16 16">
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
               <a href="" class="btn  btn-outline-danger btn-sm">How to use <i class="fa fa-info-circle" aria-hidden="true"></i></a>
              </div>
             </div>
            </div>`;
		}
	}else{
	cards +=`<h1>No Coupons added yet !!</h1>`;
	}
	$(".coupon-Cards").html(cards);

}//End of Function


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
               <a href="" class="btn  btn-outline-danger btn-sm">How to use <i class="fa fa-info-circle" aria-hidden="true"></i></a>
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

