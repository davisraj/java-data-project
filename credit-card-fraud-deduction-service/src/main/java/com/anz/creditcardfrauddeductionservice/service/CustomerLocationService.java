package com.anz.creditcardfrauddeductionservice.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.anz.creditcardfrauddeductionservice.model.CustomerCardTransactions;

/**
 * @author rdavis This Service generate location message on startup and store it
 *         in java Map. In real life it can used to call Database or Rest
 *         service.
 */

@Service
public class CustomerLocationService {

	Map<String, ArrayList<String>> locationMap = null;

	public boolean ValidateLocation(CustomerCardTransactions crdTrans) throws Exception {

		List locationList = null;
		boolean isValid = false;
		if (locationMap != null) {
			locationList = locationMap.get(crdTrans.getCustomerId());
			if (locationList.contains(crdTrans.getCity())) {
				isValid = false;
			} else {

				isValid = true;
			}
		}

		return isValid;

	}

	@PostConstruct
	public void generateUserLocation() {

		Map<String, ArrayList<String>> locationMap = new HashMap<String, ArrayList<String>>();

		locationMap.put("123456",
				new ArrayList<String>(Arrays.asList("chennai", "bangalore", "mumbai", "hyederabad", "delhi")));
		locationMap.put("123457",
				new ArrayList<String>(Arrays.asList("chennai", "bangalore", "mumbai", "hyederabad", "delhi")));
		locationMap.put("123458",
				new ArrayList<String>(Arrays.asList("chennai", "bangalore", "mumbai", "hyederabad", "delhi")));
		locationMap.put("123459",
				new ArrayList<String>(Arrays.asList("chennai", "bangalore", "mumbai", "hyederabad", "delhi")));
		locationMap.put("1234510",
				new ArrayList<String>(Arrays.asList("chennai", "bangalore", "mumbai", "hyederabad", "delhi")));
		locationMap.put("1234511",
				new ArrayList<String>(Arrays.asList("chennai", "bangalore", "mumbai", "hyederabad", "delhi")));
		locationMap.put("1234512",
				new ArrayList<String>(Arrays.asList("chennai", "bangalore", "mumbai", "hyederabad", "delhi")));
		locationMap.put("1234513",
				new ArrayList<String>(Arrays.asList("chennai", "bangalore", "mumbai", "hyederabad", "delhi")));
		locationMap.put("1234514",
				new ArrayList<String>(Arrays.asList("chennai", "bangalore", "mumbai", "hyederabad", "delhi")));
		locationMap.put("1234515",
				new ArrayList<String>(Arrays.asList("chennai", "bangalore", "mumbai", "hyederabad", "delhi")));

	}

}
