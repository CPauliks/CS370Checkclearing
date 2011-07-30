package edu.luc.clearing;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;


public class CheckHistory {

	private DataStoreAdapter storeAdapter;
	private Gson gson;
	
	public CheckHistory(DataStoreAdapter storeAdapter){
		this.storeAdapter = storeAdapter;
		gson = new Gson();
	}
	
	public String getAmounts(String limitStr) {
		Integer limit = null;
		if (limitStr != null) {
			limit = Integer.parseInt(limitStr);
		}
		Set<String> amounts = new HashSet<String>();
		List<Map<String, Object>> runQuery = storeAdapter.runQuery("Checks");
		for(Map<String, Object> properties : runQuery) {
			if (limitStr == null || (amounts.size() < limit.intValue()))
				amounts.add(properties.get("Amount").toString());
		}
		return gson.toJson(amounts);
	}

}
