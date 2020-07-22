package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import java.io.File;
import java.io.FileOutputStream;
import org.testng.annotations.Test;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;



public class BarcodeWriter
{
	@Test
	public void BAR_Code_Generator() throws Exception
	{
		int			width			= 150;
		int			height 			= 80;
		String		filePath		= "./src/test/resources/zxing_images/bar-code-generated.png";
		String		format			= "png";
		String		BAR_CODE_TEXT	= "QR code generated with zxing";
		BitMatrix	bitMatrix		= new Code128Writer().encode(BAR_CODE_TEXT, BarcodeFormat.CODE_128, width, height, null);
		
		MatrixToImageWriter.writeToStream(bitMatrix, format, new FileOutputStream(new File(filePath)));
		
		System.out.println("\nCode128 Barcode Generated.\n\n");
	}
}
