package cn.rfidcer.service.impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.rfidcer.bean.CommonVariable;
import cn.rfidcer.bean.Goods;
import cn.rfidcer.bean.GoodsDetail;
import cn.rfidcer.dao.CommonVariableMapper;
import cn.rfidcer.dao.GoodsDao;
import cn.rfidcer.dao.SysVariableDao;
import cn.rfidcer.service.QRCodeService;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

@Service
public class QRCodeServiceImpl implements QRCodeService{


	@Autowired
	private SysVariableDao sysVariableDao;
	@Autowired
	private CommonVariableMapper commonVariableDao;
	@Autowired
	private GoodsDao goodsDao;
	
	@Override
	public File getQRCodeFile(String name, String content) throws IOException {
		String QRCodewidth = sysVariableDao.getValByKey("QRCodeWidth");
		String QRCodeHeight = sysVariableDao.getValByKey("QRCodeHeigth");
		int width=125;
		int height=125;
		if(!StringUtils.isEmpty(QRCodewidth)){
			width=Integer.parseInt(QRCodewidth);
		}
		if(!StringUtils.isEmpty(QRCodeHeight)){
			height=Integer.parseInt(QRCodeHeight);
		}
		File file = QRCode.from(content).to(ImageType.JPG).withSize(width,height ).file();
		BufferedImage ImageTwo = ImageIO.read(file);
		BufferedImage ImageOne = new BufferedImage(width*2, height, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics g = ImageOne.getGraphics();// 
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width * 2, height);
		g.setColor(Color.black);
		Font mFont = new Font(null, Font.PLAIN, 12);
		g.setFont(mFont);
		String text1 = "";
		String text2 = name;
		String text3 = "";
		int d = (int) (width * 0.05);
		int e1 = (int) (height * 0.3);
		int e2 = (int) (height * 0.6);
		int e3 = (int) (height * 0.9);
		g.drawString(text1, d, e1);
		g.drawString(text2, d, e2);
		g.drawString(text3, d, e3);
		// 从图片中读取RGB
		int[] ImageArrayOne = new int[width * height];
		ImageArrayOne = ImageOne.getRGB(0, 0, width, height, ImageArrayOne, 0, width);

		int[] ImageArrayTwo = new int[width * height];
		ImageArrayTwo = ImageTwo.getRGB(0, 0, width, height, ImageArrayTwo, 0, width);
		// 生成新图片
		BufferedImage ImageNew = new BufferedImage(width*2, height, BufferedImage.TYPE_INT_RGB);
		ImageNew.setRGB(width, 0, width, height, ImageArrayOne, 0, width);// 设置右半部分的RGB
		ImageNew.setRGB(0, 0, width, height, ImageArrayTwo, 0, width);// 设置左半部分的RGB
		ImageIO.write(ImageNew, "jpg", file);
		return file;
	}
	
	@Override
	public ArrayList<File>  getMultiRowQRCodeFiles(List<GoodsDetail> detailList,int multiImg,String name) throws IOException {
		ArrayList<File> files = new ArrayList<File>();	
		int RowCount = 0 ;//行上列计次
		ArrayList<BufferedImage> lstBufferedImg = new ArrayList<BufferedImage>();
		StringBuffer  strContent = new StringBuffer(); 
		//获取图片的宽度和高度创建一个空白图片
		BufferedImage imageModel = getNullImg();
		for (GoodsDetail goodsDetail : detailList) {
				if(goodsDetail.getCode()!=null){
				String content = sysVariableDao.getValByKey("QRCodePrev")+goodsDetail.getCode();
				strContent.append(goodsDetail.getCode()+"-");//生成图片名
				File file = null;
				//添加团餐类型打印
				boolean isTuanCan = false;
				if(!StringUtils.isEmpty(goodsDetail.getGoodsId())) {
					Goods goods = goodsDao.getByGoodsId(goodsDetail.getGoodsId());
					if(goods != null) {
						CommonVariable cv = commonVariableDao.selectByPrimaryKey(goods.getDeliverType());
						if(cv != null) {
							if(org.apache.commons.lang3.StringUtils.equalsIgnoreCase(cv.getVarValue(), "团餐")) {
								isTuanCan = true;
							}
						}
					}
				}
				if (isTuanCan) {
					file = getQRCodeFileTuanCan(name, content);
				} else {
					file = getQRCodeFile(name, content);
				}
				BufferedImage bufferedImage = ImageIO.read(file);
				lstBufferedImg.add(bufferedImage);
			}else{
				lstBufferedImg.add(getNullImg());//如果是空白的添加空白图
			}
			RowCount++;//行上列追加
			if(RowCount==multiImg){
				RowCount = 0;//行上列完毕
				BufferedImage ImageNew = new BufferedImage(imageModel.getWidth()*multiImg, imageModel.getHeight(), BufferedImage.TYPE_INT_RGB);
				int multiImgCount = 0;
				for(BufferedImage ImageOne : lstBufferedImg){
					int  width = ImageOne.getWidth();
					int  height  = ImageOne.getHeight();
					int[] ImageArrayOne = new int[width * height];
					ImageArrayOne = ImageOne.getRGB(0, 0,width,height, ImageArrayOne, 0,width);
					// 生成新图片
					ImageNew.setRGB(width*multiImgCount, 0, width, height, ImageArrayOne, 0, width);// 设置右半部分的RGB
					multiImgCount++;
				}
				
				File newfile = QRCode.from("N").to(ImageType.JPG).file("Q"+strContent.toString()+"C");
				ImageIO.write(ImageNew, "jpg", newfile);
				files.add(newfile);
				strContent.setLength(0);
				lstBufferedImg.clear();
			}
			
		}
		return files;
	}
	
	@Override
	public BufferedImage getNullImg(){
		// get the value from the configured table of DB
		String QRCodewidth = sysVariableDao.getValByKey("QRCodeWidth");
		String QRCodeHeight = sysVariableDao.getValByKey("QRCodeHeigth");
		
		int width=125;
		int height=125;
		
		if(!StringUtils.isEmpty(QRCodewidth)){
			width=Integer.parseInt(QRCodewidth);
		}
		
		if(!StringUtils.isEmpty(QRCodeHeight)){
			height=Integer.parseInt(QRCodeHeight);
		}
		
		BufferedImage ImageOne = new BufferedImage(width * 2, height, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics g = ImageOne.getGraphics();// 
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width * 2, height);
		return ImageOne;
	}
	@Override
	public ZipFile getZipFile(ArrayList<File> list) {
		 ZipFile zipFile=null;
			try {
				String tmpProperty = sysVariableDao.getValByKey("tmp");
				if(StringUtils.isEmpty(tmpProperty)){
					tmpProperty = System.getProperty("java.io.tmpdir");
					if(!tmpProperty.endsWith("/")){
						tmpProperty+="/";
					}
					
				}
				File file = new File(tmpProperty+"QRCode.zip");
				if(file.exists()){
					file.delete();
				}
				zipFile = new ZipFile(file);
				ZipParameters parameters = new ZipParameters();  
			    parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);  
			    parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
			    zipFile.addFiles(list, parameters);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return zipFile;
	}
	@Override
	public File getQRCodeFileTuanCan(String name, String content) throws IOException {
		String QRCodewidth = sysVariableDao.getValByKey("QRCodeWidth");
		String QRCodeHeight = sysVariableDao.getValByKey("QRCodeHeigth");
		int width=125;
		int height=125;
		if(!StringUtils.isEmpty(QRCodewidth)){
			width=Integer.parseInt(QRCodewidth);
		}
		if(!StringUtils.isEmpty(QRCodeHeight)){
			height=Integer.parseInt(QRCodeHeight);
		}
		File file = QRCode.from(content).to(ImageType.JPG).withSize(width,height ).file();
		BufferedImage ImageTwo = ImageIO.read(file);
		BufferedImage ImageOne = new BufferedImage(width*2, height, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics g = ImageOne.getGraphics();// 
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width * 2, height);
		g.setColor(Color.black);
		Font mFont = new Font(null, Font.PLAIN, 12);
		g.setFont(mFont);
		String text1 = "*****";
		String text2 = name;
		String text3 = "";
		int d = (int) (width * 0.05);
		int e1 = (int) (height * 0.3);
		int e2 = (int) (height * 0.6);
		int e3 = (int) (height * 0.9);
		g.drawString(text1, d, e1);
		g.drawString(text2, d, e2);
		g.drawString(text3, d, e3);
		// 从图片中读取RGB
		int[] ImageArrayOne = new int[width * height];
		ImageArrayOne = ImageOne.getRGB(0, 0, width, height, ImageArrayOne, 0, width);

		int[] ImageArrayTwo = new int[width * height];
		ImageArrayTwo = ImageTwo.getRGB(0, 0, width, height, ImageArrayTwo, 0, width);
		// 生成新图片
		BufferedImage ImageNew = new BufferedImage(width*2, height, BufferedImage.TYPE_INT_RGB);
		ImageNew.setRGB(width, 0, width, height, ImageArrayOne, 0, width);// 设置右半部分的RGB
		ImageNew.setRGB(0, 0, width, height, ImageArrayTwo, 0, width);// 设置左半部分的RGB
		ImageIO.write(ImageNew, "jpg", file);
		return file;
	}

}
