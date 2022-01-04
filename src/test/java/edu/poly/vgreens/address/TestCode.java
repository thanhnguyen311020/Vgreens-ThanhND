package edu.poly.vgreens.address;

import java.util.Calendar;
import java.util.Date;

public class TestCode {
	public static void main(String[] args) {
		int month =  Calendar.getInstance().get(Calendar.MONTH);
		int year =  Calendar.getInstance().get(Calendar.YEAR);;
		if(month == 0) {
			month = 12;
			year = year -1;
		}
		
		System.out.println("Year: "+ year);
		System.out.println("Month: "+ month);
	}
}
