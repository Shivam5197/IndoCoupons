
function manageCoupons(){

	var ajaxObject = new MasterAjax();
	ajaxObject.requestType = "POST";
	ajaxObject.url = "coupon/getAllCoupons";
	ajaxObject.contentType = false;
	ajaxObject.enctype = false;
	ajaxObject.requestData(function(responseData) {
		if (responseData.status == "OK" || responseData.status == "ok" ) {
		let coupon = JSON.parse(responseData.data);	
		couponList(coupon);
		console.log(JSON.parse(responseData.data));
		} else {
		console.log(responseData.data);
		}
	});
}

/*							<th scope="col"><img src="/images/logo.jpg"" alt=""></th>
*/

function couponList(couponList){
	let ui = `<div class="row">
					<div class="col-md-12">
						<button class="btn btn-info" onclick="addCouponForm();" style="width: 100%;">Add new Coupons</button>
					</div>
				</div>`;
		if(couponList != 0||couponList !=null){				
		ui += `<div class="row mt-5">
					<h5 style="margin-left: 25%;">Coupons Available</h5>
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
							<th scope="col">Status</th>
							<th scope="col">Edit</th>
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
					if (couponList[i].couponStatus == _const_couponStatus.ACTIVE) {
						ui += `<td><button type="button" class="btn btn-success">ACTIVE</button></td>`;
					}else if(couponList[i].couponStatus == _const_couponStatus.EXPIRED){
						ui += `<td><button type="button" class="btn btn-secondary">EXPIRED</button></td>`;					
					}else if(couponList[i].couponStatus == _const_couponStatus.SOLD){
						ui += `<td><button type="button" class="btn btn-danger">SOLD</button></td>`;					
					}else if(couponList[i].couponStatus == _const_couponStatus.USEDBYMYSELF){
						ui += `<td><button type="button" class="btn btn-warning">ADMIN USED</button></td>`;					
					}
						ui += `<td><button type="button" onclick="editCouponForm(${couponList[i].couponId})" class="btn btn-info"><i class="fas fa-edit"></i></button></td>
						  </tr>`;
					};
					ui += `</tbody>
					  </table>
	</div>`;
	}else{
	ui += `<h1>No Coupons Found !! Please add some.</h1>`;	
	}
			ui += `</div>
				</div>`;

	$("#admin_Workspace").html(ui);
}

function addCouponForm(){
    let header = "<h4>Add new Coupons</h4>";
    let data = `<div class="row">
          <div class="col-md-12">
           
	<select class="form-select" id="brandDropDown" aria-label="Default select example">
  	<option selected>Select Brand*</option>
                   <option value= "`+_const_BrandsList.AMAZON+`" href="#">Amazon</option>
                   <option value= "`+_const_BrandsList.Amnesty_International+`" href="#">Amnesty International</option>
                   <option value= "`+_const_BrandsList.Big_Bazar+`" href="#">Big Bazar</option>
                   <option value= "`+_const_BrandsList.Eco_Matcher+`" href="#">Eco Matcher</option>
                   <option value= "`+_const_BrandsList.Flipkart+`" href="#">Flipkart</option>
                   <option value= "`+_const_BrandsList.Mastercard+`" href="#">Mastercard</option>
                   <option value= "`+_const_BrandsList.Myntra+`" href="#">Myntra</option>
                   <option value= "`+_const_BrandsList.Pantaloons+`" href="#">Pantaloons</option>
                   <option value= "`+_const_BrandsList.Paypal+`" href="#">Paypal</option>
                   <option value= "`+_const_BrandsList.Shoppers_Stop+`" href="#">Shoppers Stop</option>
                   <option value= "`+_const_BrandsList.UNICEF+`" href="#">UNICEF</option>
                   <option value= "`+_const_BrandsList.WWF+`" href="#">WWF</option>
		</select>
          </div>
        </div>

        <div class="row mt-3">

          <div class="col-md-12">
            <div class="input_div">
              <input type="text" class="inputTxt" id="couponCode"
                name="couponCode" required="required" placeholder=""> <label
                for="" class="inputlab">Coupon Code*</label>
            </div>            
          </div>
        </div>

        <div class="row">
          <div class="col-md-12">
            <div class="input_div">
              <input type="text" class="inputTxt" id="couponKey"
                name="couponKey" required="required" placeholder=""> <label
                for="" class="inputlab">Key</label>
            </div>            
          </div>
        </div>

        <div class="row">
          <div class="col-md-12">
            <div class="input_div">
              <input type="text" class="inputTxt" id="couponKeyValue"
                name="couponKeyValue" required="required" placeholder=""> <label
                for="" class="inputlab">Key Value</label>
            </div>            
          </div>
        </div>

        <div class="row">
          <div class="col-md-12">
            <div class="input_div">
              <input type="text" class="inputTxt" id="couponValue"
                name="couponValue" required="required" placeholder=""> <label
                for="" class="inputlab">Coupon Value</label>
            </div>            
          </div>
        </div>

        <div class="row">
          <div class="col-md-12">
            <div class="input_div">
              <input type="text" class="inputTxt" id="couponPrice"
                name="couponPrice" required="required" placeholder=""> <label
                for="" class="inputlab">Coupon Price</label>
            </div>            
          </div>
        </div>

        <div class="row">
          <div class="col-md-12">
            <div class="input_div">
              <input type="date" class="inputTxt" id="expiryDate"
                name="expiryDate" required="required"  placeholder=""> <label
                for="" class="inputlab">Expiry Date</label>
            </div>            
          </div>
        </div>`;

    let footer =  "<button type=\"submit\" onclick=\"saveCoupon(null);\" class=\"btn btn-info\">Save Coupon</button>";
    
    let popUp = new MainPopUpModal(header, data, footer);
    popUp.show();
}


function saveCoupon(couponId){
	
	let brandName = $("#brandDropDown").val();
	let couponCode = $("#couponCode").val();
	let couponKey = $("#couponKey").val();
	let couponKeyValue = $("#couponKeyValue").val();
	let couponValue= $("#couponValue").val();
	let couponPrice= $("#couponPrice").val();
	let expiryDate = $("#expiryDate").val();
	let formData = new FormData();

	if(brandName != null && brandName != "Select Brand"){
	formData.append("brand", brandName);		
	}else{
		swal({
				title : "Failed !",
				text : "Please Select Brand",
				icon : "warning",
				dangerMode : true,
				button : "OK",
		});
	}		

	if(couponValue != null && couponValue != ""){
	formData.append("couponValue", couponValue);		
	}else{
		swal({
				title : "Failed !",
				text : "Please Enter couponValue",
				icon : "warning",
				dangerMode : true,
				button : "OK",
		});
	}		

	if(couponPrice != null && couponPrice != ""){
	formData.append("couponPrice", couponPrice);		
	}else{
		swal({
				title : "Failed !",
				text : "Please Enter couponPrice",
				icon : "warning",
				dangerMode : true,
				button : "OK",
		});
	}		

	if(couponCode != null && couponCode != ""){
	formData.append("couponCode", couponCode);		
	}else{
		swal({
				title : "Failed !",
				text : "Please Enter Coupon Code",
				icon : "warning",
				dangerMode : true,
				button : "OK",
		});
	}

	formData.append("couponKey", couponKey);		
	formData.append("couponKeyValue", couponKeyValue);			
	formData.append("couponExpiryDate", expiryDate);		

	var obj = new MasterAjax();
	obj.requestType = "POST";
	if (couponId == null || couponId == "") {
	obj.url = "coupon/saveCoupon";
	}else{
	obj.url = "coupon/updateCoupon/" +couponId+ "";
	}
	
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
				title : "Failed !",
				text : responseData.message,
				icon : "warning",
				dangerMode : true,
				button : "OK",
		});
		}
	});	
}


function editCouponForm(couponId){
	
}

