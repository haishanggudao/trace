package cn.rfidcer.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.rfidcer.bean.Processor;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.dao.ProcessorMapper;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.ProcessorService;
import cn.rfidcer.service.ProductBaseService;
import cn.rfidcer.util.UUIDGenerator;

@Service
public class ProcessorServiceImpl extends BaseServiceImpl<Processor> implements ProcessorService{

	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ProcessorMapper processorMapper;
	
	@Autowired
	@Qualifier("productBaseServiceImpl")
	private ProductBaseService productBaseService;
	
	@Override
	public List<Processor> findAllList(Page page, Processor processor) {
		return processorMapper.findAllList(page,processor);
	}

	@Override
	public ResultMsg addorUpdateProcessor(Processor processor) {
		ResultMsg resultMsg = new ResultMsg();
		String info = null;
		int res = 0;
		if(StringUtils.isEmpty(processor.getId())){
			info="新增";
			logger.info("新增加工者："+processor);
			String savePath = productBaseService.saveFile(processor.getHealthCardImgfile(), processor.getHealthCard());
			processor.setHealthCard(savePath);
			processor.setId(UUIDGenerator.generatorUUID());
			res=processorMapper.insert(processor);
		}else{
			info="修改";
			if(processor.getHealthCardImgfile()!=null&&processor.getHealthCardImgfile().getSize()>0){
				String savePath = productBaseService.saveFile(processor.getHealthCardImgfile(), processor.getHealthCard());
				processor.setHealthCard(savePath);
			}
			res=processorMapper.updateByPrimaryKey(processor);
		}
		if (res == 1) {
			resultMsg.setCode("1");
			resultMsg.setMsg(info + "成功" + " ");
		} else{
			resultMsg.setCode("0");
			resultMsg.setMsg(info + "失败");
		}
		return resultMsg;
	}

}
