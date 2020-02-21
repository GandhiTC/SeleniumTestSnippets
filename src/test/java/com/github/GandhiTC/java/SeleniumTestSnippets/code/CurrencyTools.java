package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Currency;
import java.util.Locale;
import org.apache.commons.validator.routines.BigDecimalValidator;
import org.apache.commons.validator.routines.CurrencyValidator;



public class CurrencyTools
{
	public static void main(String[] args)
	{
		Locale				testLocale	= Locale.getDefault();
		BigDecimalValidator	validator	= CurrencyValidator.getInstance();
		BigDecimal			amount		= validator.validate("$123.00", testLocale);
		
		if(amount == null)
		{
			System.out.println("Not properly formatted for:  " + testLocale.getDisplayName());
		}
		else
		{
			System.out.println("This is valid currency for:  " + testLocale.getDisplayName());
		}
		
		//	remove the parentheses since this is something unusual
		amount = validator.validate("$(123.00)".replaceAll("\\(", "").replace(')', ' ').trim(), testLocale);
		
		if(amount == null)
		{
			System.out.println("Not properly formatted for:  " + testLocale.getDisplayName());
		}
		else
		{
			System.out.println("This is valid currency for:  " + testLocale.getDisplayName());
		}
		
		//	negative test with an invalid value
		amount = validator.validate("invalid", testLocale);
		
		if(amount == null)
		{
			System.out.println("Not properly formatted for:  " + testLocale.getDisplayName());
		}
		else
		{
			System.out.println("This is valid currency for:  " + testLocale.getDisplayName());
		}
		
		displayCurrency(testLocale, 9876543.21);
		
		//	need to find where LocaleUtility class comes from
		System.out.println(convertPrice("9876543.21", "EUR"));
	}


	static public void displayCurrency(Locale currentLocale, double amount)
	{
		Double			currencyAmount		= new Double(amount);
		Currency		currentCurrency		= Currency.getInstance(currentLocale);
		NumberFormat	currencyFormatter	= NumberFormat.getCurrencyInstance(currentLocale);
		
		System.out.println(currentLocale.getDisplayName() + ", " + currentCurrency.getDisplayName() + ": " + currencyFormatter.format(currencyAmount));
	}


	/**
	 * Converts given item price into a number based on given currency code. It works generically for all currencies supported by Java
	 * 
	 * @param itemPrice		currency amount
	 * 
	 * @param currencyCode	3-letter ISO country code
	 * 
	 * @return 				{@link String} containing stripped price or same as given price if parsing failed or formatter couldn't be constructed
	 *
	 * @author				Muhammad Haris https://dzone.com/articles/currency-format-validation-and
	 */
	public static Double convertPrice(String itemPrice, String currencyCode)
	{
		Double			itemPriceConverted	= null;
		Locale			currencyLocale		= Locale.getDefault();		//	LocaleUtility.getLocaleAgainstCurrency(currencyCode);
		//	Or implement an exchange rate api
		//	see:  https://www.exchangerate-api.com/docs/java-currency-api
		DecimalFormat	currencyFormatter	= getCurrencyFormatter(currencyLocale);

		if(currencyFormatter != null)
		{
			itemPrice = appendCurrencySymbol(itemPrice, currencyFormatter);

			try
			{
				Number number = currencyFormatter.parse(itemPrice);
				itemPriceConverted = number.doubleValue();
			}
			catch(ParseException e)
			{
				//	LOG.error("Failed to parse currency: " + currencyCode + ", value: " + itemPrice + ". " + e.getMessage(), e);
				System.out.println("Failed to parse currency: " + currencyCode + ", value: " + itemPrice + ". " + e.getMessage());
			}
		}
		else
		{
			//	LOG.error("No appropriate formatter found for currency: " + currencyCode + ", value: " + itemPrice + ". ");
			System.out.println("No appropriate formatter found for currency: " + currencyCode + ", value: " + itemPrice + ". ");
		}
		
		return itemPriceConverted;
	}
	
	
	/**
	 * Checks whether given itemPrice is valid or not according to the currency locale
	 *
	 * @param 	itemPrice
	 * @param 	itemCurrency
	 * @return 	true if price is valid, false if price is invalid
	 * @author 	Muhammad Haris
	 */
	public static boolean isPriceValid(String itemPrice, String itemCurrency)
	{
		Locale			currencyLocale		= Locale.getDefault();		//	LocaleUtility.getLocaleAgainstCurrency(itemCurrency);
		DecimalFormat	currencyFormatter	= getCurrencyFormatter(currencyLocale);

		if(currencyFormatter != null)
		{
			itemPrice = appendCurrencySymbol(itemPrice, currencyFormatter);
			return CurrencyValidator.getInstance().isValid(itemPrice, currencyLocale);
		}
		
		return true;
	}


	/**
	 * Gets currency formatter against given currency locale
	 * 
	 * @param currencyCode	{@link String} containing 3 letter ISO currency code
	 * 
	 * @return				{@link NumberFormat} object specialized for the currency or null if it couldn't be composed
	 * 
	 * @author				Muhammad Haris https://dzone.com/articles/currency-format-validation-and
	 */
	public static DecimalFormat getCurrencyFormatter(Locale currencyLocale)
	{
		if(currencyLocale != null)
		{
			return (DecimalFormat)NumberFormat.getCurrencyInstance(currencyLocale);
		}
		
		return null;
	}


	/**
	 * Appends appropriate currency symbol to the given price using the pattern defined in the given currency formatter
	 * 
	 * @param itemPrice			{@link String} containing price of the item in locale specific format
	 * 
	 * @param currencyFormatter	{@link DecimalFormat} object containing currency locale specific formatting info
	 * 
	 * @author 					Muhammad Haris https://dzone.com/articles/currency-format-validation-and
	 */
	public static String appendCurrencySymbol(String itemPrice, DecimalFormat currencyFormatter)
	{
		String	currencySymbol	= currencyFormatter.getDecimalFormatSymbols().getCurrencySymbol();
		String	pattern			= currencyFormatter.toPattern();

		if(!itemPrice.contains(currencySymbol))
		{
			if(pattern.startsWith("¤ "))
			{
				itemPrice = currencySymbol + " " + itemPrice;
			}
			else if(pattern.endsWith(" ¤"))
			{
				itemPrice = itemPrice + " " + currencySymbol;
			}
			else if(pattern.startsWith("¤"))
			{
				itemPrice = currencySymbol + itemPrice;
			}
			else if(pattern.endsWith("¤"))
			{
				itemPrice = itemPrice + currencySymbol;
			}
		}
		
		return itemPrice;
	}
}
