package edu.luc.clearing;

import static org.junit.Assert.*;
import java.io.StringReader;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class RequestReaderTest {

	private RequestReader requestReader;
	private DataStoreAdapter dataStore;
	private Clock clock;
	
	@Before
	public void setup(){
		dataStore = Mockito.mock(DataStoreAdapter.class);
		clock = Mockito.mock(Clock.class);
		requestReader = new RequestReader(dataStore, clock);
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
    
	@Test
	public void shouldShortCircuitTheResponseIfItTakeslongerThan25Seconds() throws Exception {
		long now = System.currentTimeMillis();
		when(clock.currentTime()).thenReturn(now, now + 23 * 1000, now + 26 * 1000);
        String response = requestReader.respond(new StringReader("[\"one\", \"two\", \"three\"]"));
        assertEquals("{\"two\":200,\"one\":100}", response);
	}

}
