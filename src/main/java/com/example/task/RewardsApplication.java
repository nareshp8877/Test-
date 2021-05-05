package com.example.task;

import javax.servlet.http.HttpServletRequest;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;  

@RestController
@SpringBootApplication
public class RewardsApplication {
	
	@RequestMapping(value = "/getRewardPoints", method = RequestMethod.GET, produces = "application/json")
	private @ResponseBody String getChecksum(@RequestBody int transactionAmount, HttpServletRequest request) {

		JSONObject jsonResponse = new JSONObject();
		JSONObject cks = new JSONObject();
		double points = 0.0;
		try {

			if(transactionAmount>0){
				points = getPoints(transactionAmount);
			if (points >0) {
				cks.put("points", points);
				jsonResponse.put("response", new JSONObject(cks.toString()));
				jsonResponse.put("Status", 200);
				jsonResponse.put("Description", "Success");
			} else {
				jsonResponse.put("Status", 400);
				jsonResponse.put("Description", "Failed to get response");
			}
			}else{
				jsonResponse.put("Status", 500);
				jsonResponse.put("Description", "Provide valid input");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				jsonResponse.put("Status", 500);
				jsonResponse.put("Description", "Failed to Respond");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		return jsonResponse.toString();
	}

	protected static double getPoints(int transactionAmount){
		Double points=0.0;
	try{
	int val = transactionAmount-50;
	int val1 = transactionAmount-100;
	if(val>0){
	points+=50;
	}
	if(val1>0){
	points+=2*val1;
	}

	}catch(Exception e){
	e.printStackTrace();
	}
	return points;
	}

}
