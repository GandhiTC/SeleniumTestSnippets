package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import javax.imageio.ImageIO;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;



public class QRImageReader
{
	@Test
	public void QRTest() throws NotFoundException
	{
		try
		{
			File			file			= new File("./src/test/resources/zxing_images/qr-code.png");
			String			decodedText		= null;
			
			// store the file as an image
			BufferedImage	bufferedImage	= ImageIO.read(file);
			
			// process the image
			LuminanceSource	source			= new BufferedImageLuminanceSource(bufferedImage);
			BinaryBitmap	bitmap			= new BinaryBitmap(new HybridBinarizer(source));
			
			// store the details of the QR code
			Result			result			= new MultiFormatReader().decode(bitmap);
							decodedText 	= result.getText();
			
			// print to console
			System.out.println("Decoded text = " + decodedText);
			
			// testng assertion
			Assert.assertEquals(decodedText, "cherchertech");
		}
		catch(IOException e)
		{
			System.out.println("Could not decode QR Code, IOException :: " + e.getMessage());
		}
	}
}
