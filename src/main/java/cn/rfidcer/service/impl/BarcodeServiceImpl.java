package cn.rfidcer.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.rfidcer.bean.CommonVariable;
import cn.rfidcer.bean.Goods;
import cn.rfidcer.bean.GoodsDetail;
import cn.rfidcer.bean.User;
import cn.rfidcer.dao.CompanyMapper;
import cn.rfidcer.dao.GoodsDetailDao;
import cn.rfidcer.dao.ProductMapper;
import cn.rfidcer.dao.SysVariableDao;
import cn.rfidcer.service.BarcodeService;
import cn.rfidcer.service.GoodsDetailService;
import cn.rfidcer.service.GoodsService;
import cn.rfidcer.service.SerialNumService;
import cn.rfidcer.util.DateUtil;
import cn.rfidcer.util.StringUtil;

@Service
public class BarcodeServiceImpl implements BarcodeService {
	
	private Logger logger = LoggerFactory.getLogger(BarcodeServiceImpl.class);
	
	@Autowired
	private SysVariableDao sysVariableDao;
	
	@Autowired
	private GoodsDetailDao goodsDetailDao;
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private CompanyMapper companyMapper;
	 
	@Autowired
	private GoodsDetailService goodsDetailService;
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private SerialNumService serialNumService;

	@Override
	public File executeBarcodeFile(String content) throws Exception {
		// Code39Bean bean = new Code39Bean();
		Code128Bean bean = new Code128Bean();
		 
        final int dpi = 600;
 
        //Configure the barcode generator
        // bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi)); //makes the narrow bar, width exactly one pixel
        // bean.setWideFactor(3);
        //bean.doQuietZone(false);
        bean.setModuleWidth(1.0);
        bean.setBarHeight(40.0);
        bean.setFontSize(10.0);
        bean.setQuietZone(10.0);
        bean.doQuietZone(true);
 
        //Open output file
//        File outputFile = new File("/tmp"+"/"+"images"+"/"+"out.png");
        String tmpProperty = sysVariableDao.getValByKey("tmp");
        File outputFile = new File(tmpProperty + content +".png");
        OutputStream out = new FileOutputStream(outputFile);
 
        try {
 
            //Set up the canvas provider for monochrome PNG output
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(
                out, "image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
 
            //Generate the barcode 
            bean.generateBarcode(canvas, content);
 
            //Signal end of generation
            canvas.finish();
        } finally {
            out.close();
        }
		return outputFile;
	}

	@Override
	public ArrayList<File> createOrUpdateGoodsDetail(GoodsDetail detail, int qrNum, User currentUser) {
		ArrayList<File> files = new ArrayList<File>();
			try {
				List<GoodsDetail> detailList = createDetailList(detail, qrNum, currentUser); 
			// loop detailList and execute the barcode file
			for (GoodsDetail goodsDetail : detailList) {
				
				 String myContent = goodsDetail.getBarcode();
				
				File myFile = executeBarcodeFile( myContent );
				
				files.add(myFile);
			}
			 
		} catch (Exception e) {
			logger.error("生成条形码异常：",e);
		}
		return files;
	}

	public List<GoodsDetail> createDetailList(GoodsDetail detail, int qrNum, User currentUser) {
		List<GoodsDetail> detailList = goodsDetailDao.getGoodsDetailsByGoodsId(detail.getGoodsId());
		
		int length = 0; 
		
		if(detailList.isEmpty()){
			length = qrNum;
		}else{
			length = qrNum-detailList.size();
		}
		
		
			String companyId = detail.getCompanyId();
			
			String goodsId = detail.getGoodsId();
			
			String strCompanyCode = companyMapper.selectByPrimaryKey(companyId).getCode();
			
			//没有商品详细则追加和重新生成
			for (int i = 0; i < length; i++) {
				// 待定条码规则
				/*
				 *前缀暂定3101 
				 */
				String myBarcode = executeBarcodeString("3101", goodsId);
				
				// service: insert a new goods detail
				GoodsDetail goodsDetail = goodsDetailService.createNewGoodsDetailWithBarcode(companyId,strCompanyCode,goodsId,myBarcode,currentUser);
				
				detailList.add(goodsDetail); 
			}
		return detailList;
	}

	@Override
	public ZipFile getZipFile(ArrayList<File> list, GoodsDetail detail) {
		ZipFile zipFile=null;
		
		Goods myGoods = goodsService.getByKey(detail.getGoodsId());
		String myTitle="";
		try {
			myTitle = new String(myGoods.getProduct().getProductName().getBytes(),"ISO-8859-1")+myGoods.getGoodsBatch();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		try {
			String tmpProperty = sysVariableDao.getValByKey("tmp");
			if(StringUtils.isEmpty(tmpProperty)){
				tmpProperty = System.getProperty("java.io.tmpdir");
				if(!tmpProperty.endsWith("/")){
					tmpProperty+="/";
				}
				
			}
			File file = new File(tmpProperty+"Barcode" + myTitle + ".zip");
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
	public String executeBarcodeString(String companyId, String goodsId) {
		String barcodeString = companyId;
		
//		// 1. 发往地区码
//		List<CommonVariable> cvs = commonVariableMapper.selectByCVV("-","barcode","areaCode");
//		for (CommonVariable cv : cvs) {
//			barcodeString += cv.getVarValue();
//		}
		// 2. 企业节点码
//		String strCompanyCode = companyMapper.selectByPrimaryKey(companyId).getCode();
//		barcodeString += StringUtil.formatFillZeroLeft(9, strCompanyCode);
		
		// 3. 日期码
		barcodeString += DateUtil.format(new Date(), "yyMMdd");
		
		// 4. 商品编码
//		Product myProduct = productMapper.findProductBygoodsId(goodsId);
//		barcodeString += myProduct.getProductCode();
		
		// 5. 日流水号
		barcodeString += StringUtil.formatFillZeroLeft(5,serialNumService.newSerialNum(new CommonVariable("-","barcode","fid","9998")));
		
		return barcodeString;
	}
	
	 
}
