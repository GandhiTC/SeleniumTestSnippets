package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import org.testng.annotations.Test;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;



public class QRImageWriter
{
	@Test
	public void createQRCode()
	{
		int							width			= 320;
		int							height 			= 320;
		String						filePath		= "./src/test/resources/zxing_images/qr-code-generated.png";
		String						format			= "png";
		String						QR_CODE_TEXT	= "QR code generated with zxing";

		try
		{
			QRCodeWriter	qrCodeWriter	= new QRCodeWriter();
			BitMatrix		bitMatrix		= qrCodeWriter.encode(QR_CODE_TEXT, BarcodeFormat.QR_CODE, width, height);
			BufferedImage	img				= MatrixToImageWriter.toBufferedImage(bitMatrix);
			File 			outputfile 		= new File(filePath);
			
		    ImageIO.write(img, format, outputfile);
		}
		catch(Exception e)
		{
			System.out.println("create QRCode error message: " + e.getMessage());
		}
	}
}
