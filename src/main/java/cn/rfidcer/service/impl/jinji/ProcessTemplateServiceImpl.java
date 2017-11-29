package cn.rfidcer.service.impl.jinji;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;

import cn.rfidcer.bean.Goods;
import cn.rfidcer.bean.ProcessTemplate;
import cn.rfidcer.bean.ProcessTemplateItem;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.dao.GoodsDao;
import cn.rfidcer.dao.ProcessTemplateMapper;
import cn.rfidcer.dao.ProductMapper;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.jinji.ProcessTemplateService;
import cn.rfidcer.util.CommonImportUtils;
import cn.rfidcer.util.ResultMsgUtil;
import cn.rfidcer.util.UUIDGenerator;

@Service
public class ProcessTemplateServiceImpl implements ProcessTemplateService{

	private Logger logger=LoggerFactory.getLogger(ProcessTemplateServiceImpl.class);
	
	@Autowired
	private ProcessTemplateMapper processTemplateDao;
	
	@Autowired
	private ProductMapper pmapper;
	
	@Autowired
	private GoodsDao goodsDao;
	
	
	public ResultMsg dataVerification(ProcessTemplate processTemplate){
		ResultMsg msg = new ResultMsg();
		msg.setCode("1");
		Page page = new Page();
		page.setPage(1);
		page.setRows(10);
		if(processTemplate.getProduct()== null || processTemplate.getProduct().getProductId().equals("") || pmapper.selectByPrimaryKey(processTemplate.getProduct().getProductId())==null){
			msg.setCode("0");
			msg.setMsg("请选择对应的产品！");
			return  msg;
		}
		int i = 0 ;
		List<ProcessTemplateItem> items = processTemplate.getTemplateItems();
		for (ProcessTemplateItem templateItem : items) {
			i++;
			if(templateItem.getProductId()== null || templateItem.getProductId().equals("") || pmapper.selectByPrimaryKey(templateItem.getProductId())==null){
				msg.setCode("0");
				msg.setMsg("第"+i+"行,请选择对应的产品！");
				return  msg;
			}
		}
		return msg;
	}
	
	@Override
	public List<ProcessTemplate> getProcessTemplateList(Page page, ProcessTemplate processTemplate) {
		return processTemplateDao.getProcessTemplateList(page, processTemplate);
	}

	@Override
	public ResultMsg createOrUpdateProcessTemplate(ProcessTemplate processTemplate, User user) {
		logger.info("新增或修改加工模板："+processTemplate); 
		//校验数据-校验下拉列表框值是否合法
		ResultMsg  msg = dataVerification(processTemplate);
		if(msg.getCode().equals("0")){
			return msg;
		}
		
		String info=null;
		int res=0;
		try {
			if ( StringUtils.isEmpty(processTemplate.getProcessTemplateId()) ) {
				String UUID = UUIDGenerator.generatorUUID();
				processTemplate.setProcessTemplateId(UUID);
				processTemplate.setType("0");
				CommonImportUtils.setCreateAndUpdateTime(processTemplate, user);//设置创建日期和修改日期	
				res = processTemplateDao.createProcessTemplate(processTemplate);
				info="新增模板";
				saveProcessTemplateItem(processTemplate, user, UUID);
			}else {
				CommonImportUtils.setUpdateTime(processTemplate, user);//设置修改日期	
				processTemplate.setType("0");
				res =processTemplateDao.updateProcessTemplate(processTemplate);
				ProcessTemplateItem templateItem = new ProcessTemplateItem();
				templateItem.setProcessTemplateId(processTemplate.getProcessTemplateId());
				processTemplateDao.delProcessTemplateItem(templateItem);
				saveProcessTemplateItem(processTemplate, user, processTemplate.getProcessTemplateId());
				info="修改模板";
			}
		} catch (Exception e) {
			logger.error("异常：",e);
		}
		
		return ResultMsgUtil.getReturnMsg(res, info);
	}

	private void saveProcessTemplateItem(ProcessTemplate processTemplate, User user, String UUID) {
		List<ProcessTemplateItem> items = processTemplate.getTemplateItems();
		for (ProcessTemplateItem templateItem : items) {
			if(StringUtils.isEmpty(templateItem.getTemplateItemId()))
			{
				templateItem.setTemplateItemId(UUIDGenerator.generatorUUID());
				templateItem.setProcessTemplateId(UUID);
				CommonImportUtils.setCreateAndUpdateTime(templateItem, user);//设置创建日期和修改日期	
				processTemplateDao.createProcessTemplateItem(templateItem);
			}else{
				CommonImportUtils.setUpdateTime(templateItem, user);//设置修改日期	
				processTemplateDao.updateProcessTemplateItem(templateItem);
			}
		}
	}
	
	@Override
	public ResultMsg delProcessTemplate(ProcessTemplate processTemplate) {
		logger.info("删除加工模板，ID："+processTemplate.getProcessTemplateId());
		ProcessTemplateItem processTemplateItem = new ProcessTemplateItem();
		processTemplateItem.setProcessTemplateId(processTemplate.getProcessTemplateId());
		int res;
		try {
			processTemplateDao.delProcessTemplateItem(processTemplateItem);
			res = processTemplateDao.delProcessTemplate(processTemplate);
		} catch (Exception e) {
			logger.error("删除加工模板异常：",e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			res=-1;
		}
		return ResultMsgUtil.getReturnMsg(res, "删除加工模板");
	}

	@Override
	public ResultMsg delTemplateItem(String templateId) {
		logger.info("删除加工模板明细，ID："+templateId);
		ProcessTemplateItem processTemplateItem = new ProcessTemplateItem();
		processTemplateItem.setTemplateItemId(templateId);
		int res = 0;
		try {
			res = processTemplateDao.delProcessTemplateItem(processTemplateItem);
		} catch (Exception e) {
			logger.error("删除产品异常：",e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			res=-1;
		}
		return ResultMsgUtil.getReturnMsg(res, "删除产品");
	}

	@Override
	public List<ProcessTemplateItem> findProcessTemplateItemsByProduct(ProcessTemplate processTemplate) {
		if(processTemplate==null){
			return null;
		}
		List<ProcessTemplateItem> list=null;
		if(StringUtils.isEmpty(processTemplate.getProcessMainId())){
			list = processTemplateDao.findProcessTemplateItemsByProduct(processTemplate);
			for (ProcessTemplateItem processTemplateItem : list) {
				Goods goods = goodsDao.findMinCreateTimeGoodsByDetailId(processTemplateItem.getStandardDetail().getProductStandardDetailId());
				if(goods!=null){
					processTemplateItem.setGoodsId(goods.getGoodsId());
					processTemplateItem.setGoodsBatch(goods.getGoodsBatch());
				}
			}
			
		}else{
			list=processTemplateDao.findProcessTemplateItemsByProcessId(processTemplate.getProcessMainId());
		}
		return list;
	}

	@Override
	public List<ProcessTemplateItem> findProcessTemplateItemsBTemplateid(String templateId) {
		return processTemplateDao.findProcessTemplateItemsBTemplateid(null, templateId);
	}

	@Override
	public ProcessTemplate findTemplateByDetailId(ProcessTemplate processTemplate) {
		return processTemplateDao.selectOne(processTemplate);
	}
	
	

}
