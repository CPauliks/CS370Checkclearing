package edu.luc.clearing;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


public class CheckHistoryTest {

	private CheckHistory history;
	private DataStoreAdapter mockDataStore;
	private Map<String, Object> check;
	private ArrayList<Map<String, Object>> checks;

	@Before
	public void setUp() {
		mockDataStore = Mockito.mock(DataStoreAdapter.class);
		history = new CheckHistory(mockDataStore);
		check = new HashMap<String, Object>();
		checks = new ArrayList<Map<String, Object>>();
		when(mockDataStore.runQuery("Checks")).thenReturn(checks);
	}
	
	@Test
	public void getRequestReturnsAllThePreviouslyEncounteredCheckAmounts() throws Exception {
		check.put("Amount", "one");
		checks.add(check);
		when(mockDataStore.runQuery("Checks")).thenReturn(checks); 
		assertEquals("[\"one\"]", history.getAmounts(null));
	}
	
//	@Test
//	public void doesNotLimitQueryIfNullIsPassedIn() throws Exception {
//		checks.add(createCheck("Amount", "one"));
//		checks.add(createCheck("Amount", "two"));
//		checks.add(createCheck("Amount", "four"));
//		checks.add(createCheck("Amount", "three"));
//		assertEquals("[\"two\",\"one\",\"three\",\"four\"]", history.getAmounts(null));
//	}
//	@Test
//	public void canLimitNumberOfChecksReturned() throws Exception {
//		checks.add(createCheck("Amount", "one"));
//		checks.add(createCheck("Amount", "two"));
//		checks.add(createCheck("Amount", "three"));
//		assertEquals("[\"two\",\"one\"]", history.getAmounts("2"));
//	}
	
	@Test (expected = NullPointerException.class)
	public void canHandleDataStoreReturningNull() throws Exception{
		when(mockDataStore.runQuery("Checks")).thenReturn(null);
		history.getAmounts(null);
	}
	
	public Map<String, Object> createCheck(String amount, Object number) {
		Map<String, Object> newCheck = new HashMap<String, Object>();
		newCheck.put(amount, number);
		return newCheck;
	}

}
