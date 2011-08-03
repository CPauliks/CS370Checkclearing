package edu.luc.clearing;
import org.junit.Test;
import static org.mockito.Mockito.*;

import com.google.appengine.api.datastore.DatastoreService;


public class DataStoreAdapterTest {

	@Test (expected = NullPointerException.class)
	public void  canStoreItems() throws Exception{
		DatastoreService googlestore = mock(DatastoreService.class);
		DataStoreAdapter store = new DataStoreAdapter(googlestore);
		store.saveCheck("Amount", "one");
		verify(store).saveCheck("Amount","one");
	}

}
