package com.vbur.avensis.chart;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;



public class UserRequest {
	
	
	private static final Logger AppLog = LogManager.getLogger(UserRequest.class);
	public static int question(String clientQuestion){
		int userOption=0;
		System.out.println(clientQuestion);
		try{
			BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		   	userOption= Integer.parseInt(bufferRead.readLine());
		   	
		   	AppLog.info("User choice: "+userOption);
		   	} catch (Exception e) {
				// TODO Auto-generated catch block
				AppLog.error(e.getMessage());
				e.printStackTrace();
			}
	 	   
		return userOption;
		}
	public final static void clearConsole()
	{
	    try
	    {
	        final String os = System.getProperty("os.name");
	        if (os.contains("Windows"))
	            Runtime.getRuntime().exec("cmd /c cls");
	        else
	            Runtime.getRuntime().exec("clear");
	    }
	    catch (final Exception e)
	    {
	    	AppLog.error(e.getMessage());
	    }
	}
}
