package edu.luc.clearing;

import static org.junit.Assert.assertEquals;

import java.io.StringReader;

import org.junit.*;

public class CheckClearingServletTest {
	CheckClearingServlet servlet;
	
	@Before
	public void setup(){
		servlet = new CheckClearingServlet();
	}
	
    @Test
    public void shouldReturnAnEmptyObjectForAnEmptyRequest() throws Exception {
        assertEquals("{}", servlet.response(new StringReader("[]")));
    }
    
    @Test
    public void shouldReturnCentsForCheckValues() throws Exception{
    	assertEquals("{\"one\":100}", servlet.response(new StringReader("[\"one\"]")));
    	assertEquals("{\"seven\":700}", servlet.response(new StringReader("[\"seven\"]")));
    }
    
    @Test
    public void shouldParseWholeValuesLessThanTen() throws Exception{
    	assertEquals(100, servlet.parseAmount("one").intValue());
    	assertEquals(200, servlet.parseAmount("two").intValue());
    	assertEquals(300, servlet.parseAmount("three").intValue());
    	assertEquals(400, servlet.parseAmount("four").intValue());
    	assertEquals(500, servlet.parseAmount("five").intValue());
    	assertEquals(600, servlet.parseAmount("six").intValue());
    	assertEquals(700, servlet.parseAmount("seven").intValue());
    	assertEquals(800, servlet.parseAmount("eight").intValue());
    	assertEquals(900, servlet.parseAmount("nine").intValue());
    }
}
