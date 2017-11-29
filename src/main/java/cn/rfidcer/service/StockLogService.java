package cn.rfidcer.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public interface StockLogService {

	/**
	 * @param yearmonth
	 * @param companyId 
	 * @return
	 */
	List<Map<String, String>> getGoodsByYearMonth(String yearmonth, String companyId);

	/**
	 * @param companyId 
	 * @return
	 */
	List<Map<String, String>> getYearMonth(String companyId);

	/**
	 * @param companyId 
	 * @param goods 
	 * @param month 
	 * @return
	 */
	List<Map<String, Object>> getInstockPriceReport(String month, String goods, String companyId);

	/**
	 * @param companyId 
	 * @param goods 
	 * @param month 
	 * @return
	 */
	List<Map<String, Object>> getOutstockPriceReport(String month, String goods, String companyId);

	/**
	 * @param yearmonth
	 * @param companyid
	 * @return
	 */
	HSSFWorkbook export(String yearmonth, String companyid);

}