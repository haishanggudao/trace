package cn.rfidcer.controller.yz;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.rfidcer.service.StockLogService;

@Controller
@RequestMapping("/pricereport")
public class PriceReportController {
	
	@Autowired
	private StockLogService slService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return "yz/pricereport";
	}
	
	@RequestMapping("/getgoodsbyyearmonth")
	@ResponseBody
	public List<Map<String, String>> getGoodsByYearMonth(String yearmonth,String companyId) {
		List<Map<String, String>> list = null;
		if(!StringUtils.isEmpty(yearmonth)) {
			list = slService.getGoodsByYearMonth(yearmonth,companyId);
		}
		if (list == null) {
			list = new ArrayList<Map<String, String>>();
		}
		return list;
	}

	@RequestMapping("/getyearmonth")
	@ResponseBody
	public List<Map<String, String>> getYearMonth(String companyId) {
		List<Map<String, String>> list;
		list = slService.getYearMonth(companyId);
		if (list == null) {
			list = new ArrayList<Map<String, String>>();
		}
		return list;
	}
	
	@RequestMapping("/getpricereport")
	@ResponseBody
	public Map<String, String> getPriceReport(String month,String goods,String companyId) {
		Map<String, String> map = new HashMap<String,String>();
		List<Map<String,Object>> instockList = slService.getInstockPriceReport(month,goods,companyId);
		List<Map<String,Object>> outstockList = slService.getOutstockPriceReport(month,goods,companyId);
		// 入库
		List<String> xAxisDataInstock = new ArrayList<String>();
		List<Double> seriesDataInstockCount = new ArrayList<Double>();
		List<Double> seriesDataInstockPrice = new ArrayList<Double>();
		List<Double> seriesDataInstockSum = new ArrayList<Double>();
		// 出库
		List<String> xAxisDataOutstock = new ArrayList<String>();
		List<Double> seriesDataOutstockCount = new ArrayList<Double>();
		List<Double> seriesDataOutstockPrice = new ArrayList<Double>();
		List<Double> seriesDataOutstockSum = new ArrayList<Double>();
		
		for (Map<String,Object> item : instockList) {
			xAxisDataInstock.add(item.get("idate").toString());
			//数量
			Double d_isnum = 0.0;
			try {
				String s_isnum = item.get("isnum").toString();
				d_isnum = Double.parseDouble(s_isnum);
			} catch (Exception e) {
				d_isnum = 0.0;
			}
			seriesDataInstockCount.add(d_isnum);
			//价格
			Double d_iaprice = 0.0;
			try {
				String s_iaprice = item.get("iaprice").toString();
				d_iaprice = Double.parseDouble(s_iaprice);
			} catch (Exception e) {
				d_iaprice = 0.0;
			}
			seriesDataInstockPrice.add(d_iaprice);
			//总值
			Double d_istotal = 0.0;
			try {
				String s_istotal = item.get("istotal").toString();
				d_istotal = Double.parseDouble(s_istotal);
			} catch (Exception e) {
				d_istotal = 0.0;
			}
			seriesDataInstockSum.add(d_istotal);
		}
		for (Map<String,Object> item : outstockList) {
			xAxisDataOutstock.add(item.get("odate").toString());
			//数量
			Double d_osnum = 0.0;
			try {
				String s_osnum = item.get("osnum").toString();
				d_osnum = Double.parseDouble(s_osnum);
			} catch (Exception e) {
				d_osnum = 0.0;
			}
			seriesDataOutstockCount.add(d_osnum);
			//价格
			Double d_oaprice = 0.0;
			try {
				String s_oaprice = item.get("oaprice").toString();
				d_oaprice = Double.parseDouble(s_oaprice);
			} catch (Exception e) {
				d_oaprice = 0.0;
			}
			seriesDataOutstockPrice.add(d_oaprice);
			//总值
			Double d_ostotal = 0.0;
			try {
				String s_ostotal = item.get("ostotal").toString();
				d_ostotal = Double.parseDouble(s_ostotal);
			} catch (Exception e) {
				d_ostotal = 0.0;
			}
			seriesDataOutstockSum.add(d_ostotal);
		}
		// 入库
		map.put("xAxisDataInstock", xAxisDataInstock.toString());
		map.put("seriesDataInstockCount", seriesDataInstockCount.toString());
		map.put("seriesDataInstockPrice", seriesDataInstockPrice.toString());
		map.put("seriesDataInstockSum", seriesDataInstockSum.toString());
		// 出库
		map.put("xAxisDataOutstock", xAxisDataOutstock.toString());
		map.put("seriesDataOutstockCount", seriesDataOutstockCount.toString());
		map.put("seriesDataOutstockPrice", seriesDataOutstockPrice.toString());
		map.put("seriesDataOutstockSum", seriesDataOutstockSum.toString());
		return map;
	}
	
	@RequestMapping(value = "/excelexport")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response, String yearmonth,String companyid)
			throws Exception {
		HSSFWorkbook wb = slService.export(yearmonth,companyid);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=report.xls");
		OutputStream ouputStream = response.getOutputStream();
		wb.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}
}