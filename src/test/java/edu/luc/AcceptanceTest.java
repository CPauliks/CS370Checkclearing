package edu.luc;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Before;
import org.junit.Test;

import edu.luc.clearing.CheckParser;

public class AcceptanceTest {

	private CheckParser parser;
	
	@Before
	public void setup(){
		parser = new CheckParser();
	}
	
    @Test
    public void shouldParseWholeValuesLessThanTen() throws Exception{
    	assertThat(parsedAmountOf("one"), is(equalTo(100)));
    	assertThat(parsedAmountOf("two"), is(equalTo(200)));
    	assertThat(parsedAmountOf("three"), is(equalTo(300)));
    	assertThat(parsedAmountOf("four"), is(equalTo(400)));
    	assertThat(parsedAmountOf("five"), is(equalTo(500)));
    	assertThat(parsedAmountOf("six"), is(equalTo(600)));
    	assertThat(parsedAmountOf("seven"), is(equalTo(700)));
    	assertThat(parsedAmountOf("eight"), is(equalTo(800)));
    	assertThat(parsedAmountOf("nine"), is(equalTo(900)));
    }

	private int parsedAmountOf(String amount) {
		return parser.parseAmount(amount).intValue();
	}
    
    

}
