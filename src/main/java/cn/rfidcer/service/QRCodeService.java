package cn.rfidcer.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.rfidcer.bean.GoodsDetail;
import net.lingala.zip4j.core.ZipFile;

/**
* @Title: QRCodeService.java 
* @Package cn.rfidcer.service 
* @Description: Service QRCode
* @author jie.jia
* @Copyright Copyright
* @date 2016年6月23日 下午7:24:25 
* @version V1.0
 */
public interface QRCodeService {

	File getQRCodeFile(String name, String content) throws IOException;
	
	File getQRCodeFileTuanCan(String name, String content) throws IOException;

	ZipFile getZipFile(ArrayList<File> list);

	/**
	 * 添加空白图; created by jgx at 2016-05-05;
	 * get value from the configured table of DB; updated by jie.jia at 2016-05-06 09:50
	 * @return
	 */
	BufferedImage getNullImg();

	/**
	 * @param detailList
	 * @param multiImg
	 * @param name
	 * @return
	 * @throws IOException
	 */
	ArrayList<File> getMultiRowQRCodeFiles(List<GoodsDetail> detailList, int multiImg, String name) throws IOException;
}
