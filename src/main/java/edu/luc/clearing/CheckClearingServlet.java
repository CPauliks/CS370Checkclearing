package edu.luc.clearing;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@SuppressWarnings("serial")
public class CheckClearingServlet extends HttpServlet {
    private static final HashMap<String, Integer> AMOUNTS = new HashMap<String, Integer>();
    
    public CheckClearingServlet() {
    	AMOUNTS.put("one", 100);
    	AMOUNTS.put("two", 200);
    	AMOUNTS.put("three", 300);
    	AMOUNTS.put("four", 400);
    	AMOUNTS.put("five", 500);
    	AMOUNTS.put("six", 600);
    	AMOUNTS.put("seven", 700);
    	AMOUNTS.put("eight", 800);
    	AMOUNTS.put("nine", 900);
    }
    
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.getWriter().print(response(req.getReader()));
    }

	public String response(Reader requestData) {
		Gson gson = new Gson();
		HashMap<String,Integer> map = new HashMap<String, Integer>();
		List<String> checks = gson.fromJson(requestData, requestType());
		for(String amount: checks){
			map.put(amount, parseAmount(amount));
		}
		return gson.toJson(map);
	}

	private Type requestType() {
		return new TypeToken<List<String>>(){}.getType();
	}

	public Integer parseAmount(String amount) {
		return AMOUNTS.get(amount);
	}
}