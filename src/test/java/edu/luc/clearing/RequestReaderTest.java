package edu.luc.clearing;

import static org.junit.Assert.assertEquals;

import java.io.StringReader;

import org.junit.Before;
import org.junit.Test;

public class RequestReaderTest {

	private RequestReader requestReader;

	@Before
	public void setup(){
		requestReader = new RequestReader();
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

}
