package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;



public class BarcodeReader
{
	@Test
	public void QRTest() throws IOException, NotFoundException
	{
		BufferedImage	bufferedImage	= ImageIO.read(new File("./src/test/resources/zxing_images/bar-code.png"));
		LuminanceSource	source			= new BufferedImageLuminanceSource(bufferedImage);
		BinaryBitmap	bitmap			= new BinaryBitmap(new HybridBinarizer(source));
		Result			result			= new MultiFormatReader().decode(bitmap);
		
		System.out.println(result.getText());
		
		Assert.assertEquals(result.getText(), "chercher tech");
	}
}
