package cn.rfidcer.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import cn.rfidcer.bean.Product;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;

/**
 * 产品信息Service；
 * @author jie.jia
 *
 */
public interface ProductBaseService {

	/**
	 * 增加或者修改产品；created by jie.jia at 2015-12-23 13:19
	 * @param product
	 * @param user
	 * @return
	 */
	ResultMsg addOrUpdateProduct(Product product, User user);
	
	/**
	 * 删除产品信息；created by jie.jia at 2015-12-23 16:24
	 * @param product
	 * @return
	 */
	ResultMsg delProduct(Product product);
	
	/**
	 * 获取产品列表；created by jie.jia at 2015-12-23 13:17 
	 * @param page
	 * @param product
	 * @return
	 */
	List<Product> getProductList(Page page,Product product);
	
	
	List<Product> findProductListByGoodsVariable(Page page,Product product);
	
	
	
	/**
	 * 查询有规格明细产品ID和名称
	 * @param product
	 * @return
	 */
	List<Product> findProductInfoAreDetailed(Product product);
	
	
	/**
	 * 查询产品列表信息; created by jie.jia at 2016-04-01 17:02
	 * @param page
	 * @param product
	 * @return
	 */
	List<Product> findProductListByQuery(Page page,Product product);

	void createOrUpdate(List<Product> ps, User user) throws Exception;

	void createAP(Product pc);
	
	/**导入产品
	 * @param uploadImportFile
	 * @param user
	 * @param companyId
	 * @param productType
	 * @return
	 */
	ResultMsg addImportProduct(MultipartFile uploadImportFile,User user,String companyId,String productType);

	ResultMsg changeTransform(Product product);
	
	public  String  saveFile(MultipartFile file,String fileUrl);
	
	public  String  saveFile(MultipartFile file,String fileUrl,String webPath,String strogePath);
	
	List<Product> findProductListByNameOrMarketcode(Product product);
}
