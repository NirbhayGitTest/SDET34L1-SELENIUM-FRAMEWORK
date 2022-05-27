package com.vtiger.practice;

import java.text.SimpleDateFormat;
import java.util.Date;


public class GetDateBeforePOMTest {
	
	public static void main(String[] args) {
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_sss");
		String date1 = sdf.format(date);
		System.out.println(date1);
	}

}
