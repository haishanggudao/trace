package cn.rfidcer.dao;

import org.apache.ibatis.annotations.Param;

import cn.rfidcer.bean.Company;
import cn.rfidcer.bean.GoodsInfo;
import cn.rfidcer.bean.GoodsQC;
import cn.rfidcer.bean.InstockInfo;
import cn.rfidcer.bean.OutstockInfo;
import cn.rfidcer.bean.ProcessMain;
import cn.rfidcer.bean.Slaughterhouse;
import cn.rfidcer.bean.GoodsQCMaterials;

/**追溯信息数据库层
 * @author xzm
 *
 */
public interface TraceDao {

	/**根据商品明细二维码查询商品信息
	 * @return
	 */
	GoodsInfo getGoodsByDetailCode(@Param("QRCode")  String QRCode,@Param("columns") String columns);
	
	/**根据商品二维码查询商品信息
	 * @param QRCode
	 * @return
	 */
	GoodsInfo getGoodsByGoodsCode(@Param("QRCode") String QRCode,@Param("columns") String columns);
	
	/**
	 * 根据 goodsId 来查询商品信息; created by jie.jia at 2016-10-27 17:12
	 * @param goodsId
	 * @return
	 */
	GoodsInfo getGoodsByGoodsId(@Param("goodsId") String goodsId);
	
	/**根据商品ID查询质检信息
	 * @param goodsId
	 * @return
	 */
	GoodsQC getGoodsQCByQRCode(@Param("goodsId") String goodsId,@Param("columns") String columns);
	
	/**根据商品ID查询质检信息(图片)
	 * @param goodsId
	 * @return
	 */
	GoodsQCMaterials getGoodsQCMaterialsByGoodsId(@Param("goodsId") String goodsId);
	
	/**根据商品ID查询加工信息
	 * @param goodsId
	 * @return
	 */
	ProcessMain getProcessInfoByGoodsId(@Param("goodsId")  String goodsId,@Param("columns") String columns);
	
	/**根据商品ID查询拆分信息
	 * @param goodsId
	 * @param columns
	 * @return
	 */
	ProcessMain getProcessSplitByGoodsId(@Param("goodsId")  String goodsId,@Param("columns") String columns);
	
	/**根据商品明细ID查询出库信息
	 * @param goodsDetailId
	 * @return
	 */
	OutstockInfo getOutStockMainInfoByGoodsId(@Param("goodsDetailId") String goodsDetailId,@Param("columns") String columns);
	
	/**获取商品的屠宰场信息
	 * @param slaughterhouseId
	 * @return
	 */
	Slaughterhouse getSlaughterhouseInfoById(@Param("slaughterhouseId") String slaughterhouseId,@Param("columns") String columns);
	
	/**根据商品ID查询入库信息
	 * @param goodsId
	 * @return
	 */
	InstockInfo getInstockInfoByGoodsId(@Param("goodsId") String goodsId,@Param("columns") String columns);
	
	/**查询零售信息
	 * @param companyid
	 * @param columns 零售信息字段
	 * @return
	 */
	Company getTraceCompanyInfoById(@Param("companyid") String companyid,@Param("columns") String columns);
	
	
	/**更新商品明细追溯次数
	 * @return
	 */
	int updateGoodsDetailTraceCount(GoodsInfo goodsInfo);
	
	/**更新商品追溯次数
	 * @return
	 */
	int updateGoodsTraceCount(GoodsInfo goodsInfo);
}
