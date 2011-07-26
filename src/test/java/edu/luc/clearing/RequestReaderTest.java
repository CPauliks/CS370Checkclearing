package edu.luc.clearing;

import static org.junit.Assert.*;

import java.io.StringReader;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class RequestReaderTest {

	private RequestReader requestReader;
	private DataStoreAdapter dataStore;

	@Before
	public void setup(){
		dataStore = mock(DataStoreAdapter.class);
		requestReader = new RequestReader(dataStore);
	}
	
    @Test
    public void shouldReturnAnEmptyObjectForAnEmptyRequest() throws Exception {
        assertEquals("{}", requestReader.respond(new StringReader("[]")));
    }
    
    @Test
    public void shouldReturnCentsForCheckValues() throws Exception{
    	assertEquals("{\"one\":100}", requestReader.respond(new StringReader("[\"one\"]")));
    	assertEquals("{\"seven\":700}", requestReader.respond(new StringReader("[\"seven\"]")));
    	assertEquals("{\"twenty\":2000}", requestReader.respond(new StringReader("[\"twenty\"]")));
    }
    
    @Test
    public void shouldeIgnoreMalformedAmounts() throws Exception {
    	assertEquals("{}", requestReader.respond(new StringReader("[\"purple\"]")));

    }
    
    @Test
    public void shouldSaveAmountsInDataStore() throws Exception{
    	requestReader.respond(new StringReader("[\"one\"]"));
    	verify(dataStore).saveRow("Amount","one");
    }

}
