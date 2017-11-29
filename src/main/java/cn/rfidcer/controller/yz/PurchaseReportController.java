package cn.rfidcer.controller.yz;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.rfidcer.bean.PurchaseOrder;
import cn.rfidcer.bean.TestBean;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.yz.PurchaseReportService;

@Controller
@RequestMapping("/purchaseReport")
public class PurchaseReportController {
	
	@Autowired
	private PurchaseReportService purchaseReportService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return "yz/purchaseReport";
	}
	
	@RequestMapping("/findAllList")
	@ResponseBody 
	public Map<String, Object> findAllList(Page page,PurchaseOrder purchaseOrder){
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("rows", purchaseReportService.list(page, purchaseOrder));		
		return map;
	}
	
	@RequestMapping(value = "/excelExport")    
    public void exportExcel(HttpServletRequest request, HttpServletResponse response)     
    		throws Exception {    
            
        List<TestBean> list = new ArrayList<TestBean>();    
        list.add(new TestBean(1000,"zhangsan","20"));    
        list.add(new TestBean(1001,"lisi","23"));    
        list.add(new TestBean(1002,"wangwu","25"));    
        HSSFWorkbook wb = purchaseReportService.export(list);    
        response.setContentType("application/vnd.ms-excel");    
        response.setHeader("Content-disposition", "attachment;filename=testBean.xls");    
        OutputStream ouputStream = response.getOutputStream();    
        wb.write(ouputStream);    
        ouputStream.flush();    
        ouputStream.close();    
   }    

}
