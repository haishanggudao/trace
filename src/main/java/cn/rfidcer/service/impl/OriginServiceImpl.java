package cn.rfidcer.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.rfidcer.bean.Origin;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.dao.OriginMapper;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.OriginService;
import cn.rfidcer.util.CommonImportUtils;
import cn.rfidcer.util.ResultMsgUtil;
import cn.rfidcer.util.UUIDGenerator;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class OriginServiceImpl extends BaseServiceImpl<Origin> implements OriginService {

	private Logger logger = LoggerFactory.getLogger(OriginService.class);
	
	@Autowired
	private OriginMapper originMapper;
	
	@Override
	public ResultMsg addOrUpdateOrigin(Origin origin, User user) {
		logger.info("invoke addOrUpdate ,id is {} .", origin.getOriginId());
		ResultMsg resultMsg = new ResultMsg();
		try {
			int num = checkOriginName(origin);
			if(num!=0){
				resultMsg.setCode("2");
				resultMsg.setMsg("源头信息已存在");
			}else{
				if (StringUtils.isEmpty(origin.getOriginId())) {
					origin.setOriginId(UUIDGenerator.generatorUUID());
					origin.setStatus("0");
					CommonImportUtils.setCreateAndUpdateTime(origin, user);//设置修改时间和创建时间
					originMapper.insertSelective(origin);
					resultMsg.setMsg("新增源头信息成功");
				} else {
					CommonImportUtils.setUpdateTime(origin, user);
					originMapper.updateByPrimaryKeySelective(origin);
					resultMsg.setMsg("修改源头信息成功");
				}
				resultMsg.setCode("1");
			}
		} catch (Exception e) {
			resultMsg.setCode("0");
			resultMsg.setMsg(e.getMessage());
			logger.error("源头信息异常",e);
		}
		return resultMsg;
	}
	
	private int checkOriginName(Origin origin) {
		Example example = new Example(Origin.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("originName", origin.getOriginName());
		if(!StringUtils.isEmpty(origin.getOriginId())){
			criteria.andNotEqualTo("originId", origin.getOriginId());
		}
		int num = originMapper.selectCountByExample(example);
		return num;
	}

	@Override
	public ResultMsg delOrigin(Origin origin, User user) {
		origin.setIsDeleted("-1");
		CommonImportUtils.setUpdateTime(origin, user);
		int res=originMapper.updateByPrimaryKeySelective(origin);
		return ResultMsgUtil.getReturnMsg(res, "删除源头信息");
	}

	@Override
	public List<Origin> getOriginList(Page page, Origin origin) {
		return originMapper.findAllList(page,origin);
	}

}
