package cn.rfidcer.service.yz;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import cn.rfidcer.bean.PurchaseItem;
import cn.rfidcer.bean.PurchaseOrder;
import cn.rfidcer.bean.TestBean;
import cn.rfidcer.interceptor.Page;

/**
* @Title: PurchaseReportService.java 
* @Package cn.rfidcer.service.yz 
* @Description: service 采购报表 
* @author jie.jia
* @Copyright Copyright
* @date 2016年6月21日 下午3:48:47 
* @version V1.0
 */
public interface PurchaseReportService {
	
	/**
	 * 查询采购单
	 * 
	 * @param page
	 * @param purchaseOrder
	 * @return
	 */
	List<PurchaseItem> list(Page page, PurchaseOrder purchaseOrder);
	
	HSSFWorkbook export(List<TestBean> list);

}
