package cn.rfidcer.dao.yz;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.rfidcer.bean.yz.SaleOrderTotalMoney;
import cn.rfidcer.bean.yz.StoreSaleItem;
import cn.rfidcer.interceptor.Page;
import tk.mybatis.mapper.common.Mapper;


/**   
 * @Title: StoreSaleItemMapper.java 
 * @Package cn.rfidcer.dao.yz 
 * @Description: 门店销售明细Dao
 * @author jgx 
 * @Copyright Copyright
 * @date 2016年7月8日 下午2:29:16 
 * @version V1.0   
*/
public interface StoreSaleItemMapper extends Mapper<StoreSaleItem>{
	
	List<StoreSaleItem> findAll(Page page,@Param("storeSaleItem") StoreSaleItem storeSaleItem);
	
	/**
	 * 获取销售单的总销售额和总折扣额; created by jie.jia at 2016-08-30 19:22
	 * @param page
	 * @param storeSaleItem
	 * @return
	 */
	SaleOrderTotalMoney getSaleTotalMoney(Page page,@Param("storeSaleItem") StoreSaleItem storeSaleItem);
	
	StoreSaleItem getSaleItemsByTraceCode(String goodsTraceCode);
	
}
