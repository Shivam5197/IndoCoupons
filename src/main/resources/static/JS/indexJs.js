$(document).ready(function(){
	
	var ajaxObject = new MasterAjax();
	ajaxObject.requestType = "POST";
	ajaxObject.url = "Indoera/getUser";
	ajaxObject.contentType = false;
	ajaxObject.enctype = false;
	ajaxObject.requestData(function(responseData) {
		if (responseData.status == "OK") {
		let user = JSON.parse(responseData.data);		
			checkUser(user);
		} else {
			console.log(responseData);
			checkUser(null);
		}
	});

});
