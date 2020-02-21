package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import java.math.BigDecimal;
import java.util.Date;
import java.util.Locale;
import org.apache.commons.validator.routines.BigDecimalValidator;
import org.apache.commons.validator.routines.CurrencyValidator;
import org.apache.commons.validator.routines.DateValidator;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.IntegerValidator;
import org.apache.commons.validator.routines.PercentValidator;



/**
 * Utility for validation of different fields
 * 
 * @author Bishal Acharya
 * 
 * @see https://bishalacharya.blogspot.com/2011/09/commons-validator-java-based-validation.html
 */
public class ValidationUtility
{
	public static void main(String args[])
	{
		String s = "12/18/1952";
		System.out.println(DateValidator.getInstance().validate(s, "MM/dd/yyyy"));
		System.out.println("valid percent :" + ValidationUtility.percentValidator("100"));
		System.out.println("Invalid percent :" + ValidationUtility.percentValidator("110"));
		System.out.println("Valid Currency :" + ValidationUtility.currencyValidator("100", Locale.US));
		System.out.println("InValid Currency :" + ValidationUtility.currencyValidator("Dollar", Locale.US));
		System.out.println("Integer Validator :" + ValidationUtility.IntegerValidator("1"));
		System.out.println("Integer Validator :" + ValidationUtility.IntegerValidator("1.2"));
		System.out.println("Valid Numeric:" + ValidationUtility.checkIsNumeric("1"));
		System.out.println("InValid Numeric:" + ValidationUtility.checkIsNumeric("ABCD"));
	}
	
	
	/**
	 * Check if provided String is Number
	 * 
	 * @param str
	 */
	public static boolean checkIsNumeric(String str)
	{
		if(str == null)
		{
			return false;
		}
		
		return str.matches("-?\\d+(.\\d+)?");
		//		return NumberUtils.isParsable(data);	//	org.apache.commons.lang3.math.NumberUtils
	}


	/**
	 * Check if given String provided is double
	 * 
	 * @param str
	 */
	public static boolean checkIfDouble(String str)
	{
		if(str == null)
		{
			return false;
		}

		try
		{
			Double.parseDouble(str);
		}
		catch(NumberFormatException nfe)
		{
			return false;
		}
		
		return true;
	}


	/**
	 * Validates whether provided string is date field or not
	 * 
	 * @param date - A date to test
	 * 
	 * @return Validity of the date by checking against the default Locale
	 */
	public static boolean validateDate(String date)
	{
//		String			format		= "MM/dd/yyyy";
		DateValidator	validator	= DateValidator.getInstance();
//		Date			dateVal		= validator.validate(date, format);
		Date			dateVal		= validator.validate(date);
		if(dateVal == null)
		{
			return false;
		}
		
		return true;
	}


	/**
	 * Validates whether provided string is date field or not
	 * 
	 * @param date - A date to test
	 * @param format - A DateTime format to check against
	 * 
	 * @return Validity of the date by checking against the given format
	 */
	public static boolean validateDate(String date, String format)
	{
		DateValidator	validator	= DateValidator.getInstance();
		Date			dateVal		= validator.validate(date, format);
		
		if(dateVal == null)
		{
			return false;
		}
		
		return true;
	}


	/**
	 * Formats the given date as according to given formatter
	 * 
	 * @param date
	 * @param format
	 * 
	 * @return
	 */
	public static String formatDate(String date, String format)
	{
		DateValidator	validator	= DateValidator.getInstance();
		String			dateVal		= null;

		try
		{
			dateVal = validator.format(date, format);
		}
		catch(IllegalArgumentException e)
		{
			System.out.println("Bad date:" + date + ": cannot be formatted");
		}
		
		if(dateVal == null)
		{
			return null;
		}
		
		return dateVal;
	}


	/**
	 * Validates whether clients data is Integer or not
	 * 
	 * @param integer
	 * 
	 * @return
	 */
	public static boolean IntegerValidator(String integer)
	{
		IntegerValidator	validator	= IntegerValidator.getInstance();
		Integer				integerVal	= validator.validate(integer, "#,##0.00");
		
		if(integerVal == null)
		{
			return false;
		}
		
		return true;
	}


	/**
	 * validates whether data is currency of not
	 * 
	 * @param currency
	 * @param loc
	 * 
	 * @return
	 */
	public static boolean currencyValidator(String currency, Locale loc)
	{
		BigDecimalValidator validator = CurrencyValidator.getInstance();
		
		if(loc == null)
		{
			loc = Locale.US;
		}
		
		BigDecimal amount = validator.validate(currency, loc);
		
		if(amount == null)
		{
			return false;
		}
		
		return true;
	}


	/**
	 * Validates whether data provided is in percentage or not
	 * 
	 * @param percentVal
	 * 
	 * @return
	 */
	public static boolean percentValidator(String percentVal)
	{
		BigDecimalValidator	validator	= PercentValidator.getInstance();
		boolean				valid		= false;
		BigDecimal			Percent		= validator.validate(percentVal, Locale.US);
		
		if(Percent == null)
		{
			valid = false;
		}
		
		// Check the percent is between 0% and 100%
		if(validator.isInRange(Percent, 0, 1))
		{
			valid = true;
		}
		else
		{
			valid = false;
		}
		
		return valid;
	}


	/**
	 * validates correct email address
	 * 
	 * @param email
	 * 
	 * @return
	 */
	public static boolean emailValidator(String email)
	{
		EmailValidator	validator		= EmailValidator.getInstance();
		boolean			isAddressValid	= validator.isValid(email);
		
		return isAddressValid;
	}
}