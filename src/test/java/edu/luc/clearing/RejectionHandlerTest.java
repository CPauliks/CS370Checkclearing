package edu.luc.clearing;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

import static org.junit.Assert.*;
import java.io.StringReader;

import org.junit.Before;
import org.junit.Test;

public class RejectionHandlerTest {
	private RejectionHandler rejectionHandler;
	private DataStoreAdapter dataStore;
	
	@Before
	public void setup(){
		dataStore = mock(DataStoreAdapter.class);
		rejectionHandler = new RejectionHandler(dataStore);
	}
	
	@Test
	public void passesFailedChecksToDatastore() throws Exception {
	    	rejectionHandler.handle(new StringReader("{\"twenty\":2000}"));
	    	verify(dataStore).saveFailedCheck("Amount","twenty", "Value", "2000");
	}
	
}