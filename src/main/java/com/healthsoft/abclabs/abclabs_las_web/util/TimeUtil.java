package com.healthsoft.abclabs.abclabs_las_web.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
	private static TimeUtil timeUtil;
	
	private TimeUtil(){
		
	}
	
	public static TimeUtil getTimeUtil() {
		if(timeUtil==null) {
			timeUtil=new TimeUtil();
		}
		return timeUtil;
	}
	
	public String addMinutes(String date,int minutes) {
		 // Given time
        String givenTime = date;
        
        // Parse the given time string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(givenTime, formatter);

        // Add 10 minutes
        LocalDateTime resultTime = dateTime.plusMinutes(minutes);

        // Format the result
        String formattedResult = resultTime.format(formatter);
        
        return formattedResult;
	}

}
