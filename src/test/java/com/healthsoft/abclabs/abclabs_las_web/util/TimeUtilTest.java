package com.healthsoft.abclabs.abclabs_las_web.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class TimeUtilTest {

	@Test
	public void test() {
		TimeUtil timeUtil=TimeUtil.getTimeUtil();
		
		String dateTime=timeUtil.addMinutes("2024-03-22 09:10:00", 10);
		
		System.out.println(dateTime);
		
		assertEquals("2024-03-22 09:20:00", dateTime);
		
	}

}
