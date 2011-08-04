package edu.luc.clearing;

import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class RejectionHandler {
	
	private DataStoreAdapter dataStore;

	public RejectionHandler(DataStoreAdapter dataStore) {
		this.dataStore = dataStore;
	}

	public void handle(Reader reader) {
		Gson gson = new Gson();
		Map<String, Integer> checks = gson.fromJson(reader, requestType());
		for(String key : checks.keySet()){
			dataStore.saveFailedCheck("Amount", key, "Value", checks.get(key).toString() );
			System.err.println("Incorrectly Parsed: "+ key);
		}
	}
	
	private Type requestType() {
		return new TypeToken<Map<String, Integer>>(){}.getType();
	}
}
