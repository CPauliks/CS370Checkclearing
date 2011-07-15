package edu.luc.clearing;

import static org.junit.Assert.*;

import org.junit.*;

public class CheckParserTest {

	private CheckParser parser;

	@Before
	public void setup(){
		parser = new CheckParser();
	}
	
    @Test
    public void shouldParseWholeValuesLessThanTen() throws Exception{
    	assertEquals(100, parser.parseAmount("one").intValue());
    	assertEquals(200, parser.parseAmount("two").intValue());
    	assertEquals(300, parser.parseAmount("three").intValue());
    	assertEquals(400, parser.parseAmount("four").intValue());
    	assertEquals(500, parser.parseAmount("five").intValue());
    	assertEquals(600, parser.parseAmount("six").intValue());
    	assertEquals(700, parser.parseAmount("seven").intValue());
    	assertEquals(800, parser.parseAmount("eight").intValue());
    	assertEquals(900, parser.parseAmount("nine").intValue());
    }
	
    @Test
    public void shouldIgnoreCase() throws Exception{
    	assertEquals(300, parser.parseAmount("Three").intValue());
    }

    
    @Test
    public void shouldHandleZeroAmounts() throws Exception {
    	assertEquals(0, parser.parseAmount("zero").intValue());
    }

}
