package application;

import java.io.*;
import java.net.*;
public class Motd {
	//This solves the puzzle to complete the final message URL
	   public static String getPuzzle() throws Exception {
		  String urlToRead = "http://cswebcat.swan.ac.uk/puzzle";
	      StringBuilder result = new StringBuilder();
	      URL url = new URL(urlToRead);
	      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	      conn.setRequestMethod("GET");
	      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	      String line;
	      while ((line = rd.readLine()) != null) {
	    	 
	         result.append(decode(line));
	      }
	      rd.close();
	      return result.toString();
	   }
	   //This retrieve the actual message of the day
	   public static String getMessage(String urlToRead) throws Exception {
		      StringBuilder result = new StringBuilder();
		      URL url = new URL(urlToRead);
		      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		      conn.setRequestMethod("GET");
		      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		      String line;
		      while ((line = rd.readLine()) != null) {
		    	 
		         result.append(line);
		      }
		      rd.close();
		      return result.toString();
		   }
	   
	   //This decodes the string from the puzzle URL 
	   private static String decode(String line) {
		   Boolean toggle = false;
		    char[] change = line.toCharArray();
		    for (int i = 0; i < change.length; i++) {
		        if (Character.isLetter(change[i])) {
		        	if (toggle == false){
			            change[i] += 1;
			            toggle = true;
			            if (change[i] == '['){
			            	change[i] = 'a';
			            }
			            
		        	}
		        	else if (toggle == true){
		        		change[i] -= 1;
			            toggle = false;
			            if (change[i] == '@'){
			            	change[i] = 'z';
			            }

		        }
		    }
		    }
		    line = String.valueOf(change);
		    return line;
		}
}

