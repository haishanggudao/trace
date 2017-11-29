package cn.rfidcer.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

public class Barcode4jUtil {
	
	public static void testBarcode() throws Exception{
		//Create the barcode bean
        Code39Bean bean = new Code39Bean();
 
        final int dpi = 150;
 
        //Configure the barcode generator
        bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi)); //makes the narrow bar, width exactly one pixel
        bean.setWideFactor(3);
        bean.doQuietZone(false);
 
        //Open output file
        File outputFile = new File("/tmp"+"/"+"images"+"/"+"out.png");
        OutputStream out = new FileOutputStream(outputFile);
 
        try {
 
            //Set up the canvas provider for monochrome PNG output
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(
                out, "image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
 
            //Generate the barcode
//            bean.generateBarcode(canvas, "Hello World");
            bean.generateBarcode(canvas, "1234567890");
 
            //Signal end of generation
            canvas.finish();
        } finally {
            out.close();
        }
	}

	public static void main(String[] args) throws Exception { 
//		testBarcode();
		int youNumber = 1;
		// 0 代表前面补充0
		// 4 代表长度为4
		// d 代表参数为正数型
		String str = String.format("%04d", youNumber);
		System.out.println(str); // 0001
	} 

}
