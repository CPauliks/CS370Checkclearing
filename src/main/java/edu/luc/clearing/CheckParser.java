package edu.luc.clearing;

import java.util.HashMap;

public class CheckParser {
    private static final HashMap<String, Integer> AMOUNTS = new HashMap<String, Integer>();
    
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
    }
    
	public Integer parseAmount(String amount) {
		amount = amount.toLowerCase();
		String[] substrings = amount.split("\\W");
		int len = substrings.length;
		Integer sum = 0;
		String tempString = "";
		boolean parsingCents = false;
		boolean foundDollar = false;
		for (int i = 0; i < len; i++){
			tempString = substrings[i];
			
			if (tempString.equals("and") || (tempString.equals("dollar") || tempString.equals("dollars"))){
				parsingCents = true;
				foundDollar = true;
			}
			
			else if ((tempString.equals("cent") || tempString.equals("cents"))){
				if (i == len -1){
					if(!parsingCents){
						return sum/100;
					}
					return sum;
				}
				else {
					return null; //cents wasn't at the end and I don't what to know what's going on here
				}
			}
			
			else if (tempString.equals("100")){
				if(!foundDollar){
					tempString = substrings[i-1];
					sum -= (100 * new Integer(tempString));
					sum += new Integer(tempString);
					parsingCents = true;
				}
			}
			
			else{
				try { //let's see if it's a string
					if(!parsingCents){
						sum += (100*AMOUNTS.get(tempString));
					}
					else{
						sum += (AMOUNTS.get(tempString));
					}
				}
				catch (NullPointerException npe){ //not a string, let's see if it's a number
					try {
						if(!parsingCents){
							sum += 100*new Integer(tempString);
						}
						else{
							sum += new Integer(tempString);
						}
					}
					catch (NumberFormatException nfe){ //neither a string nor a number
						return null;
					}
				}

			}
			
		}
		
		return sum;
	}

}
