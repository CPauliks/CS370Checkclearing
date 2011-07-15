package edu.luc.clearing;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.*;
import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.PrintWriter;
import java.io.StringReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckClearingServletTest {
	CheckClearingServlet servlet;
	private HttpServletResponse mockResponse;
	private HttpServletRequest mockRequest;
	private CharArrayWriter writer;
	
	@Before
	public void setup() throws Exception{
		servlet = new CheckClearingServlet();
		mockResponse = mock(HttpServletResponse.class);
		mockRequest = mock(HttpServletRequest.class);
		BufferedReader reader = new BufferedReader(new StringReader("[]"));
		writer = new CharArrayWriter();
		
		when(mockRequest.getReader()).thenReturn(reader);
		when(mockResponse.getWriter()).thenReturn(new PrintWriter(writer));
	}
	
	@Test
	public void setsContentTypeforTheResponse() throws Exception{

		servlet.doPost(mockRequest,mockResponse);
		verify(mockResponse).setContentType("application/json");
	}
	
	@Test
	public void writesAResponseObject() throws Exception{
		servlet.doPost(mockRequest, mockResponse);
		assertThat(writer.toString(), is(equalTo("{}")));
	}
	
}