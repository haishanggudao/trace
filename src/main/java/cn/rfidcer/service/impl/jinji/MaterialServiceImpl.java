package cn.rfidcer.service.impl.jinji;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;

import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.bean.jinji.Material;
import cn.rfidcer.dao.jinji.MaterialMapper;
import cn.rfidcer.service.ProductBaseService;
import cn.rfidcer.service.jinji.MaterialService;
import cn.rfidcer.util.CommonImportUtils;
import cn.rfidcer.util.UUIDGenerator;

@Service
public class MaterialServiceImpl implements MaterialService {
	
	private Logger logger = LoggerFactory.getLogger(MaterialServiceImpl.class);
	
	@Autowired
	private MaterialMapper materialMapper;
	
	@Autowired
	@Qualifier("productBaseServiceImpl")
	private ProductBaseService productBaseService ;
	
	@Override
	public ResultMsg addOrUpdateMaterial(Material material, User user) { 
		logger.info("新增或修改原料：" + material);
		ResultMsg resultMsg = new ResultMsg();
		String info = null;
		int res = 0;
		int iIsDuplicatedName = materialMapper.checkMaterialNameExists(material);
		if (iIsDuplicatedName == 0) {
			saveMaterialFile(material);
			if (StringUtils.isEmpty(material.getProductId())) {
				material.setProductId(UUIDGenerator.generatorUUID());
				CommonImportUtils.setCreateAndUpdateTime(material, user);// 设置修改时间和创建时间
				res = materialMapper.insertSelective(material);
				info = "新增原料";
			} else {
				CommonImportUtils.setUpdateTime(material, user);// 设置修改时间
				res = materialMapper.updateByPrimaryKeySelective(material);
				info = "修改原料";
			}
		} else {
			resultMsg.setCode("-2");
			resultMsg.setMsg("原料名称已存在");
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
	
	private void saveMaterialFile(Material material) {
		String strfileUrl = null;
		if(material.getProductImgfile() != null && material.getProductImgfile().getSize()>0){
			//上传产品图片; call service of product
			strfileUrl = productBaseService.saveFile(material.getProductImgfile(),material.getProductImageUrl());
			material.setProductImageUrl(strfileUrl);
		}  
	}

	@Override
	public ResultMsg delMaterial(Material material) {
		logger.info("Del material ID:" + material.getProductId());
		ResultMsg resultMsg = new ResultMsg();

		String delProductID = material.getProductId();

		try {
			// delete the product by updating status; added by jie.jia at
			// 2016-04-07 14:03
			int res = materialMapper.deleteWithStatusByPrimaryKey(delProductID);
			resultMsg.setCode(res + "");

			if (res == 1) {
				resultMsg.setMsg("删除原料成功");
			} else {
				resultMsg.setMsg("删除原料失败");
			}
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			resultMsg.setCode("-1");
			resultMsg.setMsg("当前原料无法删除");
		}
		return resultMsg;
	}

}
