package cn.rfidcer.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.rfidcer.dao.InstockLogMapper;
import cn.rfidcer.service.StockLogService;

@Service
public class StockLogServiceImpl implements StockLogService {

	@Autowired
	private InstockLogMapper ilMapper;
	
	private String[] excelHeader = { "产品名称", "月份", "数量", "价格","总值","出库/入库"};
	
	/* (non-Javadoc)
	 * @see cn.rfidcer.service.StockLogService#getGoodsByYearMonth(java.lang.String)
	 */
	@Override
	public List<Map<String, String>> getGoodsByYearMonth(String yearmonth,String companyId) {
		List<Map<String, String>> list = ilMapper.getGoodsByYearMonth(yearmonth,companyId);
		return list;
	}
	
	/* (non-Javadoc)
	 * @see cn.rfidcer.service.StockLogService#getYearMonth()
	 */
	@Override
	public List<Map<String, String>> getYearMonth(String companyId) {
		List<Map<String, String>> list = ilMapper.getYearMonth(companyId);
		return list;
	}

	/* (non-Javadoc)
	 * @see cn.rfidcer.service.StockLogService#getInstockPriceReport(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<Map<String, Object>> getInstockPriceReport(String month, String goods, String companyid) {
		return ilMapper.getInstockPriceReport(month,goods,companyid);
	}

	/* (non-Javadoc)
	 * @see cn.rfidcer.service.StockLogService#getOutstockPriceReport(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<Map<String, Object>> getOutstockPriceReport(String month, String goods, String companyid) {
		return ilMapper.getOutstockPriceReport(month,goods,companyid);
	}

	/* (non-Javadoc)
	 * @see cn.rfidcer.service.StockLogService#export(java.util.List)
	 */
	@Override
	public HSSFWorkbook export(String yearmonth, String companyid) {
		List<Map<String,Object>> list = ilMapper.getPriceReport(yearmonth,companyid);
		if(list == null) {
			list = new ArrayList<Map<String,Object>>();
		}
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
			Map<String,Object> map = list.get(i);
			try {row.createCell(0).setCellValue(map.get("iopn").toString());
			} catch (Exception e) {}
			try {row.createCell(1).setCellValue(map.get("iodate").toString());
			} catch (Exception e) {}
			try {row.createCell(2).setCellValue(map.get("iosnum").toString());
			} catch (Exception e) {}
			try {row.createCell(3).setCellValue(map.get("ioaprice").toString());
			} catch (Exception e) {}
			try {row.createCell(4).setCellValue(map.get("iostotal").toString());
			} catch (Exception e) {}
			try {row.createCell(5).setCellValue(map.get("iotype").toString());
			} catch (Exception e) {}
		}
		return wb;
	}
}