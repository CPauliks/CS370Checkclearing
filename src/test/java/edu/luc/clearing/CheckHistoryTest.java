package edu.luc.clearing;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;


public class CheckHistoryTest {

	private CheckHistory history;
	private DataStoreAdapter mockDataStoreAdapter;
	
	@Before
	public void setUp() {
		mockDataStoreAdapter = mock(DataStoreAdapter.class);
		history = new CheckHistory(mockDataStoreAdapter);
	}

	@Test 
	public void getRequestReturnsAllThePreviouslyEncounteredCheckAmounts() throws Exception {
		Map<String, Object> check = new HashMap<String, Object>();
		check.put("Amount", "one");
		List<Map<String, Object>> checks = Arrays.asList(check);
		when(mockDataStoreAdapter.runQuery("Checks")).thenReturn(checks);
		assertEquals("[\"one\"]", history.getAmounts());
	}

}
