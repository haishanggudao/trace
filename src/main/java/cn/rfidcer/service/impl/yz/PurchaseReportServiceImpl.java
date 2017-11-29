package cn.rfidcer.service.impl.yz;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.rfidcer.bean.PurchaseItem;
import cn.rfidcer.bean.PurchaseOrder;
import cn.rfidcer.bean.TestBean;
import cn.rfidcer.dao.PurchaseOrderDao;
import cn.rfidcer.interceptor.Page; 
import cn.rfidcer.service.yz.PurchaseReportService;

@Service
public class PurchaseReportServiceImpl implements PurchaseReportService {
	
	private Logger logger=LoggerFactory.getLogger(PurchaseReportServiceImpl.class);
	
	@Autowired
	private PurchaseOrderDao purchaseOrderDao;
	
	String[] excelHeader = { "Sno", "Name", "Age"};   
	
	@Override
	public List<PurchaseItem> list(Page page, PurchaseOrder purchaseOrder) {
		return purchaseOrderDao.listItemsByPurchaseOrder(page, purchaseOrder);
	}

	@Override
	public HSSFWorkbook export(List<TestBean> list) {
		// TODO Auto-generated method stub
		HSSFWorkbook wb = new HSSFWorkbook();    
        HSSFSheet sheet = wb.createSheet("Campaign");    
        HSSFRow row = sheet.createRow((int) 0);    
        HSSFCellStyle style = wb.createCellStyle();    
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);    
    
        for (int i = 0; i < excelHeader.length; i++) {    
            HSSFCell cell = row.createCell(i);    
            cell.setCellValue(excelHeader[i]);    
            cell.setCellStyle(style);    
            sheet.autoSizeColumn(i);    
        }    
    
        for (int i = 0; i < list.size(); i++) {    
            row = sheet.createRow(i + 1);    
            TestBean testBean = list.get(i);    
            row.createCell(0).setCellValue(testBean.getTestId());    
            row.createCell(1).setCellValue(testBean.getProductName());    
            row.createCell(2).setCellValue(testBean.getProductShortName());    
        }    
        return wb; 
	}

}
