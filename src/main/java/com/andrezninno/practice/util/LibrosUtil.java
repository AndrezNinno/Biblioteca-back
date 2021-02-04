package com.andrezninno.practice.util;

import java.util.Calendar;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LibrosUtil {
	
	public static boolean esPalindrome(String isbn) {
		return new StringBuilder(isbn).reverse().toString().equals(isbn);
	};
	
	public static boolean mayor30(String isbn) {
		return isbn.chars()
				.mapToObj(c -> (char) c)
				.filter(f -> Character.isDigit(f))
				.map(d -> Integer.parseInt(d.toString()))
				.collect(Collectors.summingInt(Integer::intValue)) >= 30;
	}
	
	public static java.sql.Date fechaEntrega(java.sql.Date fechaPrestamo){
		Calendar c = Calendar.getInstance();
		c.setTime(fechaPrestamo);
		c.add(Calendar.DAY_OF_MONTH, 17);
		
		if(c.get(Calendar.DAY_OF_WEEK) == 0) {
			c.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		return new java.sql.Date(c.getTimeInMillis());
	}

}
