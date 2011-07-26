package edu.luc.clearing;

import java.io.Reader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class RequestReader {

	private CheckParser checkParser;
	private final DataStoreAdapter dataStore;
	
	public RequestReader(DataStoreAdapter dataStore){
		this.dataStore = dataStore;
		checkParser = new CheckParser();
	}

	public String respond(Reader requestData) {
		Gson gson = new Gson();
		HashMap<String,Integer> map = new HashMap<String, Integer>();
		List<String> checks = gson.fromJson(requestData, requestType());
		for(String amount: checks){
			Integer parsedValue = checkParser.parseAmount(amount);
			if (parsedValue == null){
				System.err.println("Could not parse amount:  " + amount);
			}
			map.put(amount, parsedValue);
			dataStore.saveRow("Amount", amount);
		}
		return gson.toJson(map);
	}

	private Type requestType() {
		return new TypeToken<List<String>>(){}.getType();
	}
}
