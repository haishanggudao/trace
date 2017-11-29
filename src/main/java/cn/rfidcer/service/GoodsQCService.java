package cn.rfidcer.service;

import java.util.List;

import cn.rfidcer.bean.GoodsQC;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;


/**产品类别业务层
 * @author xzm
 *
 */
public interface GoodsQCService {

	/** 查询商品质检信息
	 * @param page 分页对象
	 * @param goodsQC 查询条件
	 * @return
	 */
	List<GoodsQC> findAll(Page page,GoodsQC goodsQC);
	
	/**新增或修改商品质检信息
	 * @param goodsQC
	 * @param user
	 * @return
	 */
	ResultMsg createOrUpdateGoodsQC(GoodsQC goodsQC,User user);
	
	/**删除商品质检信息
	 * @param goodsQC
	 * @return
	 */
	ResultMsg delGoodsQC(GoodsQC goodsQC);
	
}
