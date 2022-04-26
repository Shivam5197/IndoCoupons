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

//	alert("Hey I am called Man");
});

