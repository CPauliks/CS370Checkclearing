package edu.luc.clearing;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

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
    	assertThat(parser.parseAmount("forty five").intValue(), is(equalTo(4500)));
    }
    
	@Test
	public void shouldeParseCombinedValues() throws Exception {
		assertThat(parser.parseAmount("sixty three and 44/100").intValue(), is(equalTo(6344)));
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
    
    @Test
    public void shouldHandleWordsAndNumbers() throws Exception{
    	assertEquals(300, parser.parseAmount("Three").intValue());
    	assertEquals(300, parser.parseAmount("3 dollars").intValue());
    }
    
    @Test
    public void shouldHandleCents() throws Exception{
    	assertEquals(55, parser.parseAmount("fifty five cent").intValue());
    	assertEquals(50, parser.parseAmount("fifty cents").intValue());
    	assertEquals(50, parser.parseAmount("50 cent").intValue());
    	assertEquals(50, parser.parseAmount("50/100 cents").intValue());
    }
    
    @Test
    public void shouldHandleCombinedCrazyAmounts() throws Exception{
    	assertEquals(6821, parser.parseAmount("sixty eight and twenty one cents").intValue());
    	assertEquals(8512, parser.parseAmount("eighty five dollars 12/100").intValue());
    	assertEquals(8512, parser.parseAmount("eighty five dollars 12 cents").intValue());
    	assertEquals(8512, parser.parseAmount("eighty five and twelve cents").intValue());
    	assertEquals(8512, parser.parseAmount("eighty five dollars 12/100 cents").intValue());
    	assertEquals(8512, parser.parseAmount("eighty five dollars and 12/100 cents").intValue());
    	assertEquals(9999, parser.parseAmount("ninety nine and 99/100 dollars ").intValue());
    }
    
    @Test
    public void shouldIgnoreInitialAndTrailingSpaces() throws Exception{
    	assertEquals(101, parser.parseAmount(" one dollars and one cent").intValue());
    	assertEquals(101, parser.parseAmount("one dollars and one cent ").intValue());
    	assertEquals(101, parser.parseAmount(" one dollars and one cent ").intValue());
    }
    
    @Test
    public void shouldAllow100AsAFraction() throws Exception{
    	assertEquals(200, parser.parseAmount("one and 100/100").intValue());
    	assertEquals(100, parser.parseAmount("100/100").intValue());
    }
    
    @Test
    public void shouldIgnoreDollarSign() throws Exception{
    	assertEquals(9068, parser.parseAmount("$90 And 68 Cents").intValue());
    	assertEquals(9068, parser.parseAmount("$90 + 68 Cents").intValue());
    }
    
    @Test
    public void shouldIgnorePlusSign() throws Exception {
    	assertEquals(5533, parser.parseAmount("55 Dollars + Thirty Three Cents").intValue());
    	assertEquals(5533, parser.parseAmount("$55 + Thirty Three Cents").intValue());
    }
    
    @Test
    public void shouldParseAlternateFormsOfAnd() throws Exception {
    	assertEquals(8976 , parser.parseAmount("Eighty Nine Dollar ~ 76 Cents").intValue());
    	assertEquals(4186 , parser.parseAmount("forty one dollar & 86 cents").intValue());
    	assertEquals(7972 , parser.parseAmount("Seventy Nine --- 72 Cent ").intValue());
    	assertEquals(487 , parser.parseAmount("Four , 87/100").intValue());
    }
    
    @Test
    public void shouldParseValuesLessThanThousands() throws Exception {
    	assertEquals(20000, parser.parseAmount("200").intValue());
    	assertEquals(20000, parser.parseAmount("two hundred").intValue());
    }
    
    @Test
    public void shouldIgnoreHyphens() throws Exception {
    	assertEquals(2504672, parser.parseAmount("twenty-five thousand fourty-six --- seventy-two").intValue());
    	assertEquals(2504672, parser.parseAmount("twenty - five thousand forty - six --- seventy - two").intValue());
    }
    
    @Test
    public void shouldParseThousands() throws Exception {
    	assertEquals(30064411 , parser.parseAmount("Three hundred thousand six hundred forty four and 11/100").intValue());
    }
    
    @Test
    public void shouldIgnoreExcessSpaces() throws Exception {
    	assertEquals(487 , parser.parseAmount("Four , 87/100").intValue());
    	assertEquals(101, parser.parseAmount(" one dollars and one cent ").intValue());
    	assertEquals(101, parser.parseAmount(" one dollars   and one cent ").intValue());
    	assertEquals(101, parser.parseAmount(" one dollars --- one cent ").intValue());
    	assertEquals(2505, parser.parseAmount("twenty  - five ---    5/100").intValue());
    }

}
