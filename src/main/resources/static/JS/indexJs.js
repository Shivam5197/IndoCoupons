$(document).ready(function(){
	
	var ajaxObject = new MasterAjax();
	ajaxObject.requestType = "POST";
	ajaxObject.url = "indoCoupon/v1/checkUser";
	ajaxObject.contentType = false;
	ajaxObject.enctype = false;
	ajaxObject.requestData(function(responseData) {
		if (responseData.status == "OK" || responseData.status == "ok" ) {
		console.log("Response   : " + responseData.data);	
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
	nav = `
			<a style="color:white; font-weight: bold;">`+user.fullName+`</a>
			<a href="/indoCoupon/v1/account_settings"><svg xmlns="http://www.w3.org/2000/svg" width="70" height="40" fill="White" class="bi bi-person-circle" viewBox="0 0 16 16">
				<path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
				<path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
			  </svg></a>	

	`;		
	}else{
	nav = `
	        <a type="button" href="/indoCoupon/v1/sign-up" class="btn btn-outline-primary">Sign UP</a>
            <a type="button" href="/indoCoupon/v1/login" class="btn btn-outline-primary">Login</a>
	`;	
	}
	$("#login-SignUp_Button_Div").html(nav);
}


