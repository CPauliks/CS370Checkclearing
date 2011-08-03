package edu.luc.clearing;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class RejectedCheckServlet extends HttpServlet {
	
	private RejectionHandler rejectionHandler;
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		rejectionHandler.handle(req.getReader());
	}
}
