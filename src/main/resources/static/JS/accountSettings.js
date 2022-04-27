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
	alert("I am called");
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
		
			+"<button type=\"submit\" onclick=\"updateProfile("+user.userId+")\" class=\"btn btn-outline-primary mt-2\">Save Details</button>"
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



