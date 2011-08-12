package edu.luc.clearing;

import java.util.HashMap;
import java.util.HashSet;

public class CheckParser {
    private static final HashMap<String, Integer> AMOUNTS = new HashMap<String, Integer>();
    private static final HashSet<String> ANDVALUES = new HashSet<String>();
    
    public CheckParser(){
    	AMOUNTS.put("zero", 0);
    	AMOUNTS.put("one", 1);
    	AMOUNTS.put("two", 2);
    	AMOUNTS.put("three", 3);
    	AMOUNTS.put("four", 4);
    	AMOUNTS.put("five", 5);
    	AMOUNTS.put("six", 6);
    	AMOUNTS.put("seven", 7);
    	AMOUNTS.put("eight", 8);
    	AMOUNTS.put("nine", 9);
    	AMOUNTS.put("ten", 10);
    	AMOUNTS.put("eleven", 11);
    	AMOUNTS.put("twelve", 12);
    	AMOUNTS.put("thirteen", 13);
    	AMOUNTS.put("fourteen", 14);
    	AMOUNTS.put("fifteen", 15);
    	AMOUNTS.put("sixteen", 16);
    	AMOUNTS.put("seventeen", 17);
    	AMOUNTS.put("eighteen", 18);
    	AMOUNTS.put("nineteen", 19);
    	AMOUNTS.put("twenty", 20);
    	AMOUNTS.put("thirty", 30);
    	AMOUNTS.put("forty", 40);
    	AMOUNTS.put("fourty", 40);
    	AMOUNTS.put("fifty", 50);
    	AMOUNTS.put("sixty", 60);
    	AMOUNTS.put("seventy", 70);
    	AMOUNTS.put("eighty", 80);
    	AMOUNTS.put("ninety", 90);
    	AMOUNTS.put("no", 0);
    	AMOUNTS.put("", 0);
    	
    	ANDVALUES.add("and");
    	ANDVALUES.add("+");
    	ANDVALUES.add("~");
    	ANDVALUES.add(",");
    	ANDVALUES.add("&");
    }
    
	public Integer parseAmount(String amount) {
		amount = amount.trim().toLowerCase();
		amount = amount.replaceAll("---", ",");
		amount = amount.replaceAll("-", " ");
		amount = amount.replaceAll("\\$", "");
		int currentMultiplier = 100;
		String[] substrings = amount.split("\\s+"); //Splits on any number of spaces now. one less string mutation
		int len = substrings.length;
		Integer sum = 0;
		String tempString;
		boolean foundCents = false;
		
		for (int i = len - 1; i >= 0; i--){

			tempString = substrings[i];
			
			if(AMOUNTS.containsKey(tempString)){
				sum += (AMOUNTS.get(tempString) * currentMultiplier);
			}
			
			else if (ANDVALUES.contains(tempString)){
				if(!foundCents){
					sum = sum/100;
				}
				currentMultiplier = 100;
			}
			
			else if (tempString.contains("dollar")){
				currentMultiplier = 100;
			}
			
			else if (tempString.contains("hundred")){
				currentMultiplier = 10000;
			}
			
			else if (tempString.contains("cent")){
				foundCents = true;
				currentMultiplier = 1;
			}
			
			else if (tempString.contains("/100")){
				String[] fractionParts = tempString.split("\\W");
				sum += new Integer(fractionParts[0]);
				currentMultiplier = 100;
				foundCents = true;
			}
			
			else{
				try{
					sum += (new Integer(tempString) * currentMultiplier);
				}
				catch (NumberFormatException e){
					return null;
				}
			}
			
		}

		return sum;
	}
	
}