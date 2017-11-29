package cn.rfidcer.service.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.rfidcer.bean.InstockPurchaseItem;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.dao.InstockItemMapper;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.bean.InstockItem;
import cn.rfidcer.service.InstockItemService;

@Service
public class InstockItemServiceImpl implements InstockItemService {

	private Logger logger = LoggerFactory.getLogger(InstockItemServiceImpl.class);
	
	@Autowired
	private InstockItemMapper itMapper;
	
	@Override
	public List<InstockPurchaseItem> findAllListByParamAnd(Page page, InstockItem instockItem) {
			return itMapper.findAllListByParamAnd(page,instockItem);
	}
	
	
	@Override
	public ResultMsg deleteByKey(String instockItemId) {
		logger.info("invoke deleteByKey ,id is {0} .", instockItemId);
		ResultMsg resultMsg = new ResultMsg();
		try {
			int deleteByPrimaryKey = itMapper.deleteByPrimaryKey(instockItemId);
			if(deleteByPrimaryKey == 1) {
				resultMsg.setCode("1");
				resultMsg.setMsg("删除成功");
			} else {
				resultMsg.setCode("0");
				resultMsg.setMsg("删除失败");
			}
		} catch (Exception e) {
			resultMsg.setCode("0");
			resultMsg.setMsg(e.getMessage());
		}
		return resultMsg;
	}
}
