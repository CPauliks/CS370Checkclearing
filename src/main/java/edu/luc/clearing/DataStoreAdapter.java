package edu.luc.clearing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class DataStoreAdapter {

	private DatastoreService datastore;

	public DataStoreAdapter(){
		datastore = DatastoreServiceFactory.getDatastoreService();
	}
	
	DataStoreAdapter(DatastoreService datastore){
		this.datastore = datastore;
	}

	public List<Map<String, Object>> runQuery(String column) {
		ArrayList<Map<String,Object>> properties = new ArrayList<Map<String,Object>>();
		Query query = new Query(column);
		PreparedQuery preparedQuery = datastore.prepare(query);
		for (Entity e : preparedQuery.asIterable()){
			properties.add(e.getProperties());
		}
		return properties;
	}

	public void saveCheck(String column, String value) {
		Entity entity = new Entity("Checks");
		entity.setProperty(column, value);
		datastore.put(entity);
	}
	
	public void saveFailedCheck(String column1, String value1, String column2, String value2) {
		Entity entity = new Entity("FailedChecks");
		entity.setProperty(column1, value1);
		entity.setProperty(column2, value2);
		datastore.put(entity);
	}

}
