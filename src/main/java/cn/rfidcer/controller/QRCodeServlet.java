package cn.rfidcer.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

public class QRCodeServlet extends HttpServlet {

	private static final long serialVersionUID = -5569439697381125889L;
	
	@Override
    protected void doGet(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
 
//        String qrtext = request.getParameter("qrtext");
// 
//        ByteArrayOutputStream out = QRCode.from(qrtext).to(ImageType.PNG).withSize(250, 250).stream();   
// 
//        response.setContentType("image/png");
//        response.setContentLength(out.size());
// 
//        OutputStream outStream = response.getOutputStream();
// 
//        outStream.write(out.toByteArray());  
//        
//        outStream.flush();
//        outStream.close();
		// Set the content type based to zip
		response.setContentType("Content-type: text/zip");
		response.setHeader("Content-Disposition",
				"attachment; filename=mytest.zip");
		
		//String qrtext = request.getParameter("qrtext") + request.getParameter("qrtext");
		String qrtext = request.getParameter("qrtext");
		String[] ids = qrtext.split(",");
		System.out.println("qrtext is " + qrtext);
		// List of files to be downloaded
		List<File> files = new ArrayList<File>();
		// 51 px = 18mm
		for (String id : ids) {
			File file = QRCode.from(id).to(ImageType.JPG).withSize(51, 51).file(id);
			BufferedImage ImageTwo = ImageIO.read(file);
			int width = ImageTwo.getWidth();// 图片宽度
			int height = ImageTwo.getHeight();// 图片高度
			BufferedImage ImageOne = new BufferedImage(width * 2, height,BufferedImage.TYPE_4BYTE_ABGR);
			Graphics g = ImageOne.getGraphics();// 
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, width * 2, height);
			g.setColor(Color.black);
			Font mFont = new Font("宋体", Font.PLAIN, 12);
			g.setFont(mFont);
			String text1 = "销售单编号";
			String text2 = "客户";
			String text3 = "下单时间";
			int d = (int) (width * 2 * 0.05);
			int e1 = (int) (height * 0.25);
			int e2 = (int) (height * 0.5);
			int e3 = (int) (height * 0.75);
			g.drawString(text1, d, e1);
			g.drawString(text2, d, e2);
			g.drawString(text3, d, e3);
			// 从图片中读取RGB
			int[] ImageArrayOne = new int[width * 2 * height];
			ImageArrayOne = ImageOne.getRGB(0, 0,width * 2,height,
			ImageArrayOne, 0, width * 2);
			
			int[] ImageArrayTwo = new int[width * height];
			ImageArrayTwo = ImageTwo.getRGB(0, 0, width, height, ImageArrayTwo,0, width);
			// 生成新图片
			BufferedImage ImageNew = new BufferedImage(width * 3, height,
			BufferedImage.TYPE_INT_RGB);
			ImageNew.setRGB(width, 0, width*2, height, ImageArrayOne, 0, width * 2);// 设置左半部分的RGB
			ImageNew.setRGB(0, 0, width, height, ImageArrayTwo, 0, width);// 设置右半部分的RGB
			ImageIO.write(ImageNew, "jpg", file);
//			ImageIO.write(ImageOne, "jpg", file);
//			ImageIO.write(ImageTwo, "jpg", file);
			files.add(file);
		}
		//files.add(QRCode.from(qrtext).to(ImageType.JPG).withSize(51, 51).file("QRCode"+qrtext));
		//files.add(QRCode.from(qrtext).to(ImageType.JPG).withSize(51, 51).file("QRCode"+qrtext));
		
		ServletOutputStream out = response.getOutputStream();
		ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(out));

		for (File file : files) {
			System.out.println("Adding " + file.getName());
			zos.putNextEntry(new ZipEntry(file.getName()));

			// Get the file
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(file);

			} catch (FileNotFoundException fnfe) {
				// If the file does not exists, write an error entry instead of
				// file
				// contents
				zos.write(("ERRORld not find file " + file.getName())
						.getBytes());
				zos.closeEntry();
				System.out.println("Couldfind file "
						+ file.getAbsolutePath());
				continue;
			}

			BufferedInputStream fif = new BufferedInputStream(fis);

			// Write the contents of the file
			int data = 0;
			while ((data = fif.read()) != -1) {
				zos.write(data);
			}
			fif.close();

			zos.closeEntry();
			System.out.println("Finishedng file " + file.getName());
		}
		
		zos.close();
    }
}
