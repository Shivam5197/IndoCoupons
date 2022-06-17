
function qrCodeUI(couponId){

var obj = new MasterAjax();
obj.requestType = "POST";
obj.url = "coupon/getCouponDetails/"+couponId+"";
obj.contentType = false;
obj.processData = false;
obj.dataType= "application/json";
obj.requestData(function(responseData){
	if(responseData.status == "OK" || responseData.status == "ok"){
		let couponList = JSON.parse(responseData.data);	

let cards = `
	<div class="container card shadow">
		<div class="row">
			<!-- 		<div class="col-md-12"> -->
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<nav class="nav nav-pills nav-justified" id="myTab">
					<a class="nav-link active" aria-current="page" href="#">UPI/Wallets</a> 
					<a class="nav-link" onclick="comingSoon();" href="#">Credit/Debit Cards</a> 
					<a class="nav-link" onclick="comingSoon();" href="#">Net Banking</a> 
				</nav>
			</div>
			<div class="col-md-2"></div>
		</div>
	</div>

      <div class="col-md-6">
        <h2>You can Scan this QR code to make payment or Use Paytm number: 9818535782</h2>
        <img src="/images/qrcode.jpeg" alt="" style="width: inherit;">
      </div>

      <div class="col-md-6">
        <hr>
        <h3>Coupon You selected </h3>`;

	if(couponList != 0||couponList !=null){

	cards += `<div class="mb-3 mt-4">
            <div class="card">
              <div class="card-img-pos">`;
				
//              <img src="images/flipkart.png" alt="" class="card-img-top cImg">
				if (couponList.brand == _const_BrandsList.AMAZON) {
						cards += 	`<img src="/images/amazon.png" alt="" class="card-img-top cImg">`;
					}else if(couponList.brand == _const_BrandsList.Big_Bazar){
						cards += 	`<img src="/images/big_bazar.png" alt="" class="card-img-top cImg">`;						
					}else if(couponList.brand == _const_BrandsList.Flipkart){
						cards += 	`<img src="/images/flipkart.png" alt="" class="card-img-top cImg">`;						
					}else if(couponList.brand == _const_BrandsList.Mastercard){
						cards += 	`<img src="/images/mastercard.png" alt="" class="card-img-top cImg">`;						
					}else if(couponList.brand == _const_BrandsList.Myntra){
						cards += 	`<img src="/images/myntra.png" alt="" class="card-img-top cImg">`;						
					}else if(couponList.brand == _const_BrandsList.Pantaloons){
						cards += 	`<img src="/images/pantaloons.jpg" alt="" class="card-img-top cImg">`;						
					}else if(couponList.brand == _const_BrandsList.Paypal){
						cards += 	`<img src="/images/paypal.png" alt="" class="card-img-top cImg">`;						
					}else if(couponList.brand == _const_BrandsList.Shoppers_Stop){
						cards += 	`<img src="/images/shoperStop.png" alt="" class="card-img-top cImg">`;						
					}
					
        cards += `</div>
              <div class="card-body">`;

				if (couponList.brand == _const_BrandsList.AMAZON) {
						cards += 	`<h5 class="card-title"> Amazon </h5>`;
					}else if(couponList.brand == _const_BrandsList.Big_Bazar){
						cards += 	`<h5 class="card-title"> Big Bazar </h5>`;						
					}else if(couponList.brand == _const_BrandsList.Flipkart){
						cards += 	`<h5 class="card-title"> Flipkart </h5>`;						
					}else if(couponList.brand == _const_BrandsList.Mastercard){
						cards += 	`<h5 class="card-title"> Mastecard </h5>`;						
					}else if(couponList.brand == _const_BrandsList.Myntra){
						cards += 	`<h5 class="card-title"> Myntra </h5>`;						
					}else if(couponList.brand == _const_BrandsList.Pantaloons){
						cards += 	`<h5 class="card-title"> Pantaloons </h5>`;						
					}else if(couponList.brand == _const_BrandsList.Paypal){
						cards += 	`<h5 class="card-title"> Paypal </h5>`;						
					}else if(couponList.brand == _const_BrandsList.Shoppers_Stop){
						cards += 	`<h5 class="card-title"> Shopper's Stop </h5>`;						
					}

             cards += `<p class="card-text">
                  <p>You will be saving <i class="fas fa-rupee-sign"></i> <a style="color: red; font-weight: 800;"> ${couponList.couponValue} </a> on your purchase by just paying <i class="fas fa-rupee-sign"></i> <a style="color: forestgreen; font-weight: 800;"> ${couponList.couponPrice} </a></p>
                  <p>Expiry Date : ${couponList.couponExpiryDate}</p>
                </p>
               <a href="" class="btn btn-outline-danger btn-sm" onclick="usagePopp();">How to use <i class="fa fa-info-circle" aria-hidden="true"></i></a>
              </div>
             </div>
            </div>
           
          <div class="col-md-12">
			 <a onclick="sendMailToAdmin(${couponList.couponId}); this.disabled=true;" class="btn btn-primary" style="width: 100%;margin-bottom: 10vh;">Amount Paid</a>
          </div>
`;
}else{
	
}
		$(".coupon-Cards").html(cards);

	}else{
			Swal.fire({
			  icon: 'error',
			  title: 'Oops...',
			  text: 'Something went wrong!',
			  footer: '<a href="">Report to Admin?</a>'
			})
	}
});	

}

function comingSoon(){


			Swal.fire({
			  icon: 'error',
			  title: 'Under Developement',
			  text: 'This Feature is under developement Will be coming soon',
			  footer: 'Thank You'
			})

	
}

function sendMailToAdmin(couponId){
	
var obj = new MasterAjax();
obj.requestType = "POST";
obj.url = "coupon/sendMailToAdmin/"+couponId+"";
obj.contentType = false;
obj.processData = false;
obj.dataType= "application/json";
obj.requestData(function(responseData){
	if(responseData.status == "OK" || responseData.status == "ok"){

Swal.fire({
  icon: 'success',
  title: 'Congrats !',
  text: responseData.data,
})
}else{
Swal.fire({
  icon: 'error',
  title: 'Oops...',
  text: responseData.message,
})
}

});

}


function usagePopp(){
}



function phoneNumberPopup(){
Swal.fire('Our Paytm is active on number: 9818535782 ');
}