package edu.luc.clearing;

import java.util.HashMap;

public class CheckParser {
    private static final HashMap<String, Integer> AMOUNTS = new HashMap<String, Integer>();
    
    public CheckParser(){
    	AMOUNTS.put("zero", 0);
    	AMOUNTS.put("one", 100);
    	AMOUNTS.put("two", 200);
    	AMOUNTS.put("three", 300);
    	AMOUNTS.put("four", 400);
    	AMOUNTS.put("five", 500);
    	AMOUNTS.put("six", 600);
    	AMOUNTS.put("seven", 700);
    	AMOUNTS.put("eight", 800);
    	AMOUNTS.put("nine", 900);
    	AMOUNTS.put("ten", 1000);
    	AMOUNTS.put("eleven", 1100);
    	AMOUNTS.put("twelve", 1200);
    	AMOUNTS.put("thirteen", 1300);
    	AMOUNTS.put("fourteen", 1400);
    	AMOUNTS.put("fifteen", 1500);
    	AMOUNTS.put("sixteen", 1600);
    	AMOUNTS.put("seventeen", 1700);
    	AMOUNTS.put("eighteen", 1800);
    	AMOUNTS.put("nineteen", 1900);
    	AMOUNTS.put("twenty", 2000);
    	AMOUNTS.put("thirty", 3000);
    	AMOUNTS.put("forty", 4000);
    	AMOUNTS.put("fifty", 5000);
    	AMOUNTS.put("sixty", 6000);
    	AMOUNTS.put("seventy", 7000);
    	AMOUNTS.put("eighty", 8000);
    	AMOUNTS.put("ninety", 9000);
    	AMOUNTS.put("and", 0); //could delete it but the code is more streamlined otherwise to just say it's 0
    	//code shinyness vs extra time spent on a get operation, I guess
    }
    
	public Integer parseAmount(String amount) {
		amount = amount.toLowerCase();
		String[] substrings = amount.split("\\W");
		int len = substrings.length;
		Integer sum = 0;
		
		if (substrings[len-1].equals("100")){ //contains cents
			
			for (int i = 0; i < len - 2; i++){//add all words before the cents
				
				try{
					sum += AMOUNTS.get(substrings[i]);	
				}
			
				catch (NullPointerException e) {//some junk word there is NaN
					return null;
				}
					 
			}
			
			try{ //add the cents
				sum += new Integer (substrings[len-2]);	
			}
		
			catch (NullPointerException e) {
				return null;
			}

		}
		
		else { //does not contain cents
			
			for (int i = 0; i < len; i++){ //add all words

				try{
					 sum += AMOUNTS.get(substrings[i]);
				}
			
				catch (NullPointerException e) {//some junk word there is NaN
					return null;
				}
			}
		}
		
		return sum;
	}

}
