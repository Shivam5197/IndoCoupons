$(document).ready(function(){
	yourCoupons();
});




function accountDetails(){
	
	var ajaxObject = new MasterAjax();
	ajaxObject.requestType = "POST";
	ajaxObject.url = "indoCoupon/v1/checkUser";
	ajaxObject.contentType = false;
	ajaxObject.enctype = false;
	ajaxObject.requestData(function(responseData) {
		if (responseData.status == "OK" || responseData.status == "ok" ) {
		console.log("Response   : " + responseData.data);	
		let user = JSON.parse(responseData.data);	
			updateDetailsForm(user);
		} else {
			console.log(responseData);
							swal({
								title : "Failed !",
								text : "Session Timed out|| Please Login Again.",
								icon : "warning",
								dangerMode : true,
								button : "OK",
							});
															
						window.location = '/indoCoupon/v1/login';
		}
	});
}

function updateDetailsForm(user){
	let nav = "<div class=\"logincard p-3 bg-white rounded\">"
			+"<div class=\"row\">"
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
		
			+"<button type=\"submit\" onclick=\"updateProfile("+user.userId+");\" class=\"btn btn-outline-primary mt-2\">Save Details</button>"
		+"</div>";
	$(".account_set_Workspace").html(nav);
}


function updateProfile(userId){
	console.log("Update Profile called");
	
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


function yourCoupons(){
	
	var ajaxObject = new MasterAjax();
	ajaxObject.requestType = "POST";
	ajaxObject.url = "indoCoupon/v1/userCoupons";
	ajaxObject.contentType = false;
	ajaxObject.enctype = false;
	ajaxObject.requestData(function(responseData) {
		if (responseData.status == "OK" || responseData.status == "ok" ) {
		let couponList = JSON.parse(responseData.data);	
			userCoupons(couponList)
		} else {
			console.log(responseData);
							swal({
								title : "Failed !",
								text : "Session Timed out|| Please Login Again.",
								icon : "warning",
								dangerMode : true,
								button : "OK",
							});
															
						window.location = '/indoCoupon/v1/login';
		}
	});
}


function userCoupons(couponList){
	let ui = ``;
		if(couponList != 0||couponList !=null){				
		ui += `<div class="row mt-5">
					<h5 style="margin-left: 25%;">Your Coupons </h5>
				<div class="col-md-12">

					<div class="table-responsive-md">
					<table class="table" style="text-align: center;">
						<thead class="thead-light">
						  <tr>
							<th scope="col">Brand</th>
							<th scope="col">Coupon Code</th>
							<th scope="col">Coupon Value</th>
							<th scope="col">Expiry Date</th>							
							<th scope="col">Coupon Price</th>
						</tr>
						</thead>
						<tbody>`;
				for (var i = 0; i < couponList.length; i++) {
					ui += `<tr>`;
					
				if (couponList[i].brand == _const_BrandsList.AMAZON) {
						ui += 	`<th scope="col" ><img style="max-width: 19vw;" src="/images/amazon.png" alt="amazon logo"></th>`;
					}else if(couponList[i].brand == _const_BrandsList.Big_Bazar){
						ui += 	`<th scope="col"><img style="max-width: 19vw;" src="/images/big_bazar.png" alt="Big bazar logo"></th>`;						
					}else if(couponList[i].brand == _const_BrandsList.Flipkart){
						ui += 	`<th scope="col"><img style="max-width: 19vw;" src="/images/flipkart.png" alt="flipkart.png"></th>`;						
					}else if(couponList[i].brand == _const_BrandsList.Mastercard){
						ui += 	`<th scope="col"><img style="max-width: 19vw;" src="/images/mastercard.png" alt="amazon logo"></th>`;						
					}else if(couponList[i].brand == _const_BrandsList.Myntra){
						ui += 	`<th scope="col"><img style="max-width: 19vw;" src="/images/myntra.png" alt="myntra.png"></th>`;						
					}else if(couponList[i].brand == _const_BrandsList.Pantaloons){
						ui += 	`<th scope="col"><img style="max-width: 19vw;" src="/images/pantaloons.jpg" alt="pantaloons.jpg"></th>`;						
					}else if(couponList[i].brand == _const_BrandsList.Paypal){
						ui += 	`<th scope="col"><img style="max-width: 19vw;" src="/images/paypal.png" alt="paypal.png"></th>`;						
					}else if(couponList[i].brand == _const_BrandsList.Shoppers_Stop){
						ui += 	`<th scope="col"><img style="max-width: 19vw;" src="/images/shoperStop.png" alt="shoperStop.png"></th>`;						
					}
						ui += `<td>${couponList[i].couponCode}</td>
							<td>${couponList[i].couponValue} <i class="fas fa-rupee-sign"></i></td>
							<td>${couponList[i].couponExpiryDate}</td>
							<td>${couponList[i].couponPrice} <i class="fas fa-rupee-sign"></i></td>`;
	}
					ui += `</tbody>
					  </table>
	</div>`;
	}
			ui += `</div>
				</div>`;
	$(".account_set_Workspace").html(ui);
}


