package edu.luc.clearing;

import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.*;
import org.junit.*;

public class CheckParserTest {

	private CheckParser parser;

	@Before
	public void setup(){
		parser = new CheckParser();
	}
	
	@Test
	public void shouldeParseFractionalValues() throws Exception {
		assertThat(parser.parseAmount("44/100").intValue(), is(equalTo(44)));
		assertThat(parser.parseAmount("57/100").intValue(), is(equalTo(57)));
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
    public void shouldeParseWholeValuesGreaterThanTen() throws Exception{
    	assertThat(parser.parseAmount("twenty").intValue(), is(equalTo(2000)));
    	assertThat(parser.parseAmount("forty-five").intValue(), is(equalTo(4500)));
    }
    
	@Test
	public void shouldeParseCombinedValues() throws Exception {
		assertThat(parser.parseAmount("sixty-three and 44/100").intValue(), is(equalTo(6344)));
		assertThat(parser.parseAmount("fourteen and 57/100").intValue(), is(equalTo(1457)));
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
