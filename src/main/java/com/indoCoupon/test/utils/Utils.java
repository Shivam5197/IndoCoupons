/**
 * 
 */
package com.indoCoupon.test.utils;

import org.springframework.http.HttpStatus;

import lombok.extern.slf4j.Slf4j;

/**
 * @author shiva
 * Added on 19-Mar-2022
 * Package  com.indoCoupon.test.utils
 */
@Slf4j
public class Utils {

	public APIResponseModal getDefaultApiResponse() {
		APIResponseModal apiResponseModel = null;
		try {
			apiResponseModel = new APIResponseModal();
			apiResponseModel.setData(null);
			apiResponseModel.setMessage("Unable to process the request!");
			apiResponseModel.setStatus(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return apiResponseModel;
	}

	public Boolean isNotNull(String string) {
		if(string != null && !string.isEmpty())
			return true;
	return false;	
	}
	
	public Boolean isNotNull(Integer string) {
		if(string != null)
			return true;
	return false;	
	}
	
	public Boolean isNotNull(Object string) {
		if(string != null)
			return true;
	return false;	
	}

	
}
