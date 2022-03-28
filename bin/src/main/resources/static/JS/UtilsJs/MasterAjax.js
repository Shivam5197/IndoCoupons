/**
 * This method is to Create a  Ajax request and get Response Here as well
 */
class MasterAjax{
	constructor(){
		this.requestType = null;
		this.url = null;
		this.timeout = 100000;
		this.enctype =  null;
		this.data = null;
		this.processData = null;
		this.contentType = null;
		this.responseData = null;
		this.responseStatus = null;
		this.responseStatusCode = null;
	}
	
	requestData(callBack){
		
		var parameterError=false;
		if(null == this.requestType){
			parameterError=true;
			console.log("Error: Request Type can't be null");
		}    
		if(null === this.url || undefined === this.url || "undefined" === this.url){
			parameterError=true;
			console.log("Error: URL can't be null");
		}  
		if(null == this.data || this.data.length <= 0){
			//console.log("Warning: Data is null");
		}
		if(parameterError === false){
		toggleSpinnerOn(); 
			$.ajax({
				type : this.requestType,
				enctype : this.enctype,
		        processData : this.processData, 
		        contentType : this.contentType, 
//				url : global_contextPath+"/"+this.url,
			url : global_contextPath+"/"+this.url,
				data: this.data,
				timeout : this.timeout,
				success : function(responseData,textStatus) {
					toggleSpinnerOff();
					callBack(responseData,textStatus);
				},
				error : function(responseData,textStatus) {
					toggleSpinnerOff(); 
					callBack(responseData,textStatus); 
				}
			}); 
		}
		//return this.responseData;
	}
}
