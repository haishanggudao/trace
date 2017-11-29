package cn.rfidcer.service.impl;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import cn.rfidcer.bean.Product;
import cn.rfidcer.bean.ProductCategory;
import cn.rfidcer.bean.ProductCompany;
import cn.rfidcer.bean.ProductStandard;
import cn.rfidcer.bean.ProductStandardDetail;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.dao.ProductCategoryDao;
import cn.rfidcer.dao.ProductCompanyMapper;
import cn.rfidcer.dao.ProductMapper;
import cn.rfidcer.dao.ProductStandardDao;
import cn.rfidcer.dao.ProductStandardDetailDao;
import cn.rfidcer.dao.SysVariableDao;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.ProductBaseService;
import cn.rfidcer.util.CommonImportUtils;
import cn.rfidcer.util.StringUtil;
import cn.rfidcer.util.UUIDGenerator;

@Service
public class ProductBaseServiceImpl implements ProductBaseService {
	
	private Logger logger = LoggerFactory.getLogger(ProductBaseServiceImpl.class);
	
	@Autowired
	protected ProductMapper productDao;

	@Autowired
	protected ProductCategoryDao categoryDao;

	@Autowired
	protected ProductStandardDao standardDao;

	@Autowired
	protected SysVariableDao variableDao;
	@Autowired
	protected ProductCompanyMapper productCompanyMapper;
	@Autowired
	protected ProductStandardDetailDao standardDetailDao;

	@Override
	public ResultMsg addOrUpdateProduct(Product product, User user) {
		logger.info("新增或修改产品：" + product);
		ResultMsg resultMsg = new ResultMsg();
		String info = null;
		int res = 0;
		int iIsDuplicatedName = productDao.checkProductNameExists(product);
		if (iIsDuplicatedName == 0) {
			saveProductFile(product);
			if (StringUtils.isEmpty(product.getProductId())) {
				product.setProductId(UUIDGenerator.generatorUUID());
				CommonImportUtils.setCreateAndUpdateTime(product, user);// 设置修改时间和创建时间
				
				
				res = productDao.insertSelective(product);
				if(res>=1){
					//添加自身企业
					ProductCompany productCompany = new ProductCompany();
					productCompany.setCompanyId(product.getCompanyId());
					productCompany.setProductId(product.getProductId());
					res = productCompanyMapper.insert(productCompany);
				}
				info = "新增产品";
			} else {
				CommonImportUtils.setUpdateTime(product, user);// 设置修改时间
				res = productDao.updateByPrimaryKeySelective(product);
				info = "修改产品";
			}
		} else {
			resultMsg.setCode("-2");
			resultMsg.setMsg("产品名称已存在");
		}

		if (res == 1) {
			resultMsg.setCode("1");
			resultMsg.setMsg(info + "成功" + " ");
		} else if (info != null) {
			resultMsg.setCode("0");
			resultMsg.setMsg(info + "失败");
		}

		return resultMsg;
	}

	
	
	public  String  saveFile(MultipartFile file,String fileUrl) {
		String strSaveFileUrl = "";
		if (file != null && !file.isEmpty()) {
			String webPath = variableDao.getValByKey("WebPath");
			String strogePath = variableDao.getValByKey("strogePath");
			if (fileUrl != null && fileUrl.trim().length() != 0) {
				String oldFileName = fileUrl.substring(webPath.length());
				File oldFile = new File(strogePath + oldFileName);
				if (oldFile.exists()) {
					oldFile.delete();
				}
			}
			try {
				String path = strogePath + System.currentTimeMillis()
						+ file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
				File imgFile = new File(path);
				FileUtils.copyInputStreamToFile(file.getInputStream(), imgFile);
				strSaveFileUrl=webPath + imgFile.getName();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return strSaveFileUrl;
	}
	
	public  String  saveFile(MultipartFile file,String fileUrl,String webPath,String strogePath) {
		String strSaveFileUrl = "";
		if (file != null && !file.isEmpty()) {
			if (fileUrl != null && fileUrl.trim().length() != 0) {
				String oldFileName = fileUrl.substring(webPath.length());
				File oldFile = new File(strogePath + oldFileName);
				if (oldFile.exists()) {
					oldFile.delete();
				}
			}
			try {
				String path = strogePath + System.currentTimeMillis()
						+ file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
				File imgFile = new File(path);
				FileUtils.copyInputStreamToFile(file.getInputStream(), imgFile);
				strSaveFileUrl=webPath + imgFile.getName();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return strSaveFileUrl;
	}
	
	protected void saveProductFile(Product product) {
		String strfileUrl = null;
		if(product.getProductImgfile() != null && product.getProductImgfile().getSize()>0){
			//上传产品图片
			strfileUrl = saveFile(product.getProductImgfile(),product.getProductImageUrl());
			product.setProductImageUrl(strfileUrl);
		}
		if(product.getPublicityImagefile() != null && product.getPublicityImagefile().getSize()>0){
			//上传宣传图片
			strfileUrl = saveFile(product.getPublicityImagefile(),product.getPublicityImageUrl());
			product.setPublicityImageUrl(strfileUrl);
		}
	}
	
	@Override
	public List<Product> getProductList(Page page, Product product) {
		
		if ( StringUtil.isBlank(product.getProductCategoryId()) &&  product.getCategory() != null) { 
			if ( !StringUtil.isBlank(product.getCategory().getProductCategoryId()) ) {
				product.setProductCategoryId(product.getCategory().getProductCategoryId());
			} 
		}
		return productDao.findProductList(page, product);
	}
	@Override
	public List<Product> findProductInfoAreDetailed(Product product) {
		return productDao.findProductInfoAreDetailed( product);
	}
	
	
	@Override
	public ResultMsg delProduct(Product product) {
		logger.info("Del product ID:" + product.getProductId());
		ResultMsg resultMsg = new ResultMsg();

		String delProductID = product.getProductId();

		try {
			// delete the product by updating status; added by jie.jia at
			// 2016-04-07 14:03
			int res = productDao.deleteWithStatusByPrimaryKey(delProductID);
			resultMsg.setCode(res + "");

			if (res == 1) {
				resultMsg.setMsg("删除产品成功");
			} else {
				resultMsg.setMsg("删除产品失败");
			}
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			resultMsg.setCode("-1");
			resultMsg.setMsg("当前产品无法删除");
		}
		return resultMsg;
	}

	@Override
	public void createOrUpdate(List<Product> ps, User user) throws Exception {
		if (ps != null && !ps.isEmpty()) {
			for (Product product : ps) {
				addOrUpdateProduct(product, user);
			}
		}
	}

	@Override
	public void createAP(Product pc) {
		productDao.insertSelective(pc);
	}

	@Override
	public List<Product> findProductListByQuery(Page page, Product product) {
		return productDao.findProductListByQuery(page, product);
	}

	@Override
	public ResultMsg addImportProduct(MultipartFile importFile, User user, String companyId, String productType) {
		
		//long starTime_all=System.currentTimeMillis();
		StringBuffer sb = new StringBuffer();
		int successNum = 0;
		int failNum = 0;
		ResultMsg msg = CommonImportUtils.checkImportFile(importFile);
		if (msg.getCode() != null) {
			return msg;
		}
		try {
			Workbook workbook = new HSSFWorkbook(importFile.getInputStream());
			Sheet sheet = workbook.getSheetAt(0);
			List<Row> list = new ArrayList<Row>();
			List<String> firstRowList = getFirstRowList();
			for (Row row : sheet) {
				if (row.getRowNum() == 0) {
					/*
					 * 验证首行内容
					 */

					CommonImportUtils.validateFirstRow(row, firstRowList);
					continue;
				}
				if (CommonImportUtils.isBlankRow(row, firstRowList.size())) {
					continue;
				}
				/*
				 * 验证excel格式
				 */
				validateOtherRow(row);
				list.add(row);
			}
			for (Row row : list) {
				//long starTime_for=System.currentTimeMillis();
				Timestamp time = new Timestamp(System.currentTimeMillis());
				String categoryName = row.getCell(4).toString();
				ProductCategory productCategory = new ProductCategory();
				productCategory.setProductCategoryName(categoryName);
				productCategory.setCompanyId(companyId);
				productCategory = categoryDao.findProductCategoryByName(productCategory);
				if (productCategory == null) {
					productCategory = new ProductCategory();
					productCategory.setProductCategoryName(categoryName);
					productCategory.setCompanyId(companyId);
					productCategory.setProductCategoryId(UUIDGenerator.generatorUUID());
					productCategory.setLevel("1");
					productCategory.setStatus("0");
					CommonImportUtils.setCreateAndUpdateTime(productCategory, user, time);
					/*
					 * 分类描述默认与名称相同
					 */
					productCategory.setProductCategoryDesc(categoryName);
					categoryDao.createProductCategory(productCategory);
				}
				/*
				 * 查询规格名称是否存在，不存在则新建
				 */
				String standardName = row.getCell(5).toString();
				ProductStandard productStandard = new ProductStandard();
				productStandard.setProductStandardName(standardName);
				productStandard.setProductCategoryId(productCategory.getProductCategoryId());
				productStandard = standardDao.checkProductStandardExists(productStandard);
				if (productStandard == null) {
					productStandard = new ProductStandard();
					productStandard.setProductStandardId(UUIDGenerator.generatorUUID());
					productStandard.setProductStandardName(standardName);
					productStandard.setProductCategoryId(productCategory.getProductCategoryId());
					CommonImportUtils.setCreateAndUpdateTime(productStandard, user, time);
					standardDao.createProductStandard(productStandard);
				}
				/*
				 * 查询产品名称是否存在，不存在则新增产品
				 */
				String productName = row.getCell(0).toString();
				Product product = new Product();
				product.setProductName(productName);
				product.setCompanyId(companyId);
				List<Product> lstProduct  = productDao.findProductListByName(product);
				if (lstProduct.size() < 1  ) {
					product = new Product();
					product.setProductId(UUIDGenerator.generatorUUID());
					product.setProductName(productName);
					product.setCompanyId(companyId);
					product.setProductCategoryId(productCategory.getProductCategoryId());
					product.setProductCode(row.getCell(1).toString());
					product.setProductType(productType);
					product.setStatus("0");
					/*
					 * 产品简称
					 */
					Cell cell = row.getCell(2);
					if (cell == null || cell.toString() == null) {
						product.setProductShortName(productName);
					} else {
						product.setProductShortName(cell.toString());
					}
					/*
					 * 产品简介
					 */
					Cell cell2 = row.getCell(3);
					if (cell2 != null && cell.toString() != null) {
						product.setProductDesc(cell2.toString());
					}
					product.setCreateBy(user.getId());
					product.setCreateTime(time);
					product.setUpdateBy(user.getId());
					product.setUpdateTime(time);
					int res = productDao.insertSelective(product);
					if (res == 1) {
//						res = productDao.insertSelective(product);
//						if(res>=1){
							//添加自身企业
							ProductCompany productCompany = new ProductCompany();
							productCompany.setCompanyId(product.getCompanyId());
							productCompany.setProductId(product.getProductId());
							res = productCompanyMapper.insert(productCompany);
//						}
						successNum++;
					} else {
						failNum++;
						sb.append("第" + (row.getRowNum() + 1) + "行产品导入失败<br>");
					}
				} else {
					product = lstProduct.get(0);
					failNum++;
					sb.append("第" + (row.getRowNum() + 1) + "行产品名称已存在<br>");
				}
				/*
				 * 查询规格明细是否存在，不存在新增
				 */
				ProductStandardDetail standardDetail = new ProductStandardDetail();
				standardDetail.setCompanyId(companyId);
				standardDetail.setProduct(product);//防止重复数据
				standardDetail.setProductStandard(productStandard);
				String productStandardNum = row.getCell(6).toString().replace(".0","");
				standardDetail.setProductStandardNum(productStandardNum);
				int num = standardDetailDao.findProductStandardDetailByUnique(standardDetail);
				if (num == 0) {
					standardDetail.setProductStandardDetailId(UUIDGenerator.generatorUUID());
					CommonImportUtils.setCreateAndUpdateTime(standardDetail, user, time);
					standardDetailDao.createProductStandardDetail(standardDetail);
				}
			  //long endTime_for=System.currentTimeMillis();
			  //long Time=endTime_for-starTime_for;
			  //System.out.println("已执行到："+(row.getRowNum()+1)+"行 运行时间："+Time+"毫秒");
			}
			if (!sb.equals("")) {
				sb.append("<br>");
			}
			sb.append("导入结果：<br>");
			sb.append("成功导入" + successNum + "行产品<br>");
			sb.append("失败" + failNum + "行");
			msg.setCode("1");
			msg.setMsg(sb.toString());
		} catch (Exception e) {
			msg.setCode("-3");
			msg.setMsg(e.getMessage());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		//long endTime_all=System.currentTimeMillis();
		//long Time=endTime_all-starTime_all;
		//System.out.println("总运行时间："+Time/1000+"秒");
		
		return msg;
	}

	protected void validateOtherRow(Row row) throws Exception {
		for (int j = 0; j < 7; j++) {
			Cell cell = row.getCell(j);
			switch (j) {
			case 1:
				if (cell != null) {
					Pattern pattern = Pattern.compile("[0-9]{0,32}");
					Matcher matcher = pattern.matcher(cell.toString().trim());
					//System.out.println((row.getRowNum() + 1) +"行 产品编码："+cell.toString());
					if (!matcher.matches()) {
						throw new Exception("第" + (row.getRowNum() + 1) + "行产品编码必须为数字");
					}
				} else {
					throw new Exception("第" + (row.getRowNum() + 1) + "行产品编码不能为空");
				}
				break;
			case 2:
				break;
			case 3:
				break;
			default:
				if (cell == null || cell.toString().trim().length() == 0) {
					throw new Exception("第" + (row.getRowNum() + 1) + "行" + getFirstRowList().get(j) + "不能为空");
				}
				break;
			}
		}
	}

	protected List<String> getFirstRowList() {
		List<String> list = new ArrayList<String>();
		list.add("产品名称");
		list.add("产品编码（6位数字）");
		list.add("产品简称（如果为空，将与产品名称保持一致）");
		list.add("产品简介（可为空）");
		list.add("产品分类（请使用产品类别信息中的名称）");
		list.add("产品规格名称");
		list.add("产品规格数量");
		return list;
	}

	@Override
	public ResultMsg changeTransform(Product product) {
		ResultMsg msg = new ResultMsg();
		if (!StringUtils.isEmpty(product.getProductId())) {
			Product p = productDao.selectByPrimaryKey(product.getProductId());
			p.setProductType("0");
			int result = productDao.updateByPrimaryKeySelective(p);
			if (result == 1) {
				msg.setCode("1");
				msg.setMsg("更新成功");
			} else {
				msg.setCode("-1");
				msg.setMsg("更新失败");
			}
		}
		return msg;
	}


	@Override
	public List<Product> findProductListByGoodsVariable(Page page,Product product) {
		return productDao.findProductListByGoodsVariable(page, product);
	}
	@Override
	public List<Product> findProductListByNameOrMarketcode(Product product) {
		return productDao.findProductListByNameOrMarketcode(product);
	}
}
