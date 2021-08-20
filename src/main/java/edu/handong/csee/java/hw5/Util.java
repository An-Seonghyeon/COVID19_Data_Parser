package edu.handong.csee.java.hw5;

/**
 * This program implements useful applications which are frequently used in other programs.
 * This program provides two methods to convert a String typed value into integer type, and
 * check whether a certain String typed value can be converted to integer type or not.
 * 
 * @author Seonghyeon An
 * @version 1.0
 * @since 2020-05-31
 */
public class Util {
	/**
	 * This is the method to convert a String value into an integer.
	 * It used Integer.valueOf method to convert a String typed value into an integer typed value.
	 * 
	 * @param value A String value to convert into an integer.
	 * @return An integer typed value from a String typed value.
	 */
	public static int convertStringToValue(String value) {
		return Integer.valueOf(value);
	}
	
	/**
	 * This is the method to check whether the value is integer form or not by using try-catch block.
	 * Try-catch block is a block of code where Java exceptions are processed.
	 * The statements in try block would be executed first and return true if the statements are all valid.
	 * If an exception is caused in the try block, the execution control would be moved to catch block and return false if NumberFormatException errors occurred.
	 * NumberFormatException occurred when the input value in Integer.parseInt method is not numerical form.
	 * 
	 * @param value A String value to check whether it is an integer or not. 
	 * @return True if a String value is an integer or False if a String value is not an integer.
	 */
	public static boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
		}
		 
		catch(NumberFormatException e) {
			return false;
		}
		
		return true;
	}
}