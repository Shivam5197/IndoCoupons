/**
 * 
 */

function displayErrorMessage(message){
	console.log("DisplayErrorMessage");
	$("#errorMessageDiv").show();
	$("#errorMessageDiv").html("<ul style=\"list-style:disc\" class=\"errorList\"><li>"+message+"</li></ul>");
} 

class MainPopUpModal{      
    constructor(title,content,popUpHandler){
        this.title = title;    
        this.bodyContent = content;     
        this.popUpHandler = popUpHandler;    
        this.closeBtnTitle = null;  
        this.initializePopUp();  
    }
    setTitle(title){
        this.title = undefined != title && null != title ? title : "";   
        this.initializePopUp();
    }
    setBodyContent(content){
        this.bodyContent = undefined != content && null != content ? content : "";
        this.initializePopUp();
    }
    setHandler(popUpHandler){
        this.popUpHandler = undefined != popUpHandler && null != popUpHandler ? popUpHandler : "";  
        this.initializePopUp();   
    }
    setCloseButtonText(text){
        this.closeBtnTitle = undefined != text && null != text ? text : "";    
        this.initializePopUp();
    }     
        
    initializePopUp(){
        try{      
            
            if(undefined != this.title && null != this.title){
                $("#main-model-title").html(this.title); 
					$("#main-model-title").css("color", "white").css("font-weight", "bold").css("font-style", "inherit");
            }else{
                $("#main-model-title").html("");
            }
            if(undefined != this.popUpHandler && null != this.popUpHandler){
                $("#main-modal-handler").html(this.popUpHandler);
            }else{
                $("#main-modal-handler").html("");
            }
            
            if(undefined != this.bodyContent && null != this.bodyContent){
                $("#main-model-body").html(this.bodyContent);
            }else{
                $("#main-model-body").html("");
            }   
            
            if(undefined != this.closeBtnTitle && null != this.closeBtnTitle){
                $("#main-model-close-btn-text").html(this.closeBtnTitle);
            }else{
                $("#main-model-close-btn-text").html("Close");
            }         
            
        }catch(Exception){         
            console.log("Exception : Main Modal : " + Exception);
        }
    }
    show(){
        $("#main-model").modal("show");
    }
    hide(){  
        $("#main-model").modal("hide");
        $(".modal-dialog").css({'width':90+'%','max-width':500+'px'});         
    }
    lg(){    
        $(".modal-dialog").css({'width':90+'%','max-width':70+'%'});
    }
    md(){
        $(".modal-dialog").css({'width':50+'%','max-width':70+'%'});
    }
}

function resetPosition() {
    // reset modal if it isn't visible
    if (!$(".modal.in").length){
      $(".modal-dialog").css({
        top: '0',
        left: '0'
      });
    }
 }


function displaySuccessToast(header,message){
/*	let header = "Failed";
	let message = "This is the main reason you are getting failed  here ";
*/	
	var toast = "<div class=\"toast-header\" style=\"background-color: #32de84; color: white;\">"
		+"<strong class=\"mr-auto\">"+header+"</strong>"  
			+"<button type=\"button\" class=\"m;-2 mb-1 close\" data-dismiss=\"toast\" area-label=\"close\">"
						+"<span area-hidden=\"true\">&times;</span>"
							+"</button>"
			+"</div>"
				+"<div class=\"toast-body\">"
					+"<p>"+message+"</p>"
							  +"</div>";
					$("#epictoast").html(toast);

var option = {
    animation : true,
    delay : 5000 
};

    var toastElement = document.getElementById("epictoast");
    var tost = new bootstrap.Toast(toastElement , option);
    tost.show();

}

function displayFailedToast(header,message){
/*	let header = "Failed";
	let message = "This is the main reason you are getting failed  here ";
*/	
	var toast = "<div class=\"toast-header\" style=\"background-color: #D2122E; color: white;\">"
		+"<strong class=\"mr-auto\">"+header+"</strong>"  
			+"<button type=\"button\" class=\"m;-2 mb-1 close\" data-dismiss=\"toast\" area-label=\"close\">"
						+"<span area-hidden=\"true\">&times;</span>"
							+"</button>"
			+"</div>"
				+"<div class=\"toast-body\">"
					+"<p>"+message+"</p>"
							  +"</div>";
					$("#epictoast").html(toast);

var option = {
    animation : true,
    delay : 5000 
};

    var toastElement = document.getElementById("epictoast");
    var tost = new bootstrap.Toast(toastElement , option);
    tost.show();

}


function sleep(milliseconds) {
	console.log("Sleep Called");
  const date = Date.now();
  let currentDate = null;
  do {
    currentDate = Date.now();
  } while (currentDate - date < milliseconds);
}


		//loader
		function toggleSpinnerOn(){    
				$(".processingLoader").show();
				$("#workSpace").css("opacity","0.1");
		}
		function toggleSpinnerOff(){
				$(".processingLoader").hide();	    
				$("#workSpace").css("opacity","1");
		}

function _isNull (object){
	if(undefined != object && null != object){  
		return false;
	}else{
		return true; 
	}
}


function _isEmpty(object){
	if(undefined != object && null != object && object.length > 0){  
		return false; 
	}else{
		return true; 
	}
}


