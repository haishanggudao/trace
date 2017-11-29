package cn.rfidcer.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.rfidcer.bean.Company;
import cn.rfidcer.dao.CompanyMapper;
import cn.rfidcer.service.TraceCodeService;
import cn.rfidcer.util.StringUtil;

@Service
public class TraceCodeServiceImpl implements TraceCodeService{

	@Autowired
	private CompanyMapper companyMapper;
	
	@Override
	public String newTraceCode(String companyId,String companyCode) {
		companyMapper.updateTraceSerialNum(companyId);
		Company company = companyMapper.selectByPrimaryKey(companyId);
		if(company!=null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
			String dateStr = sdf.format(new Date());
			String fullSerialNum = StringUtil.formatFillZeroLeft(5, company.getTraceSerialNum()+"");
			return companyCode+dateStr+company.getTraceSerialNum()+fullSerialNum;
		}
		return null;
	}
	
	@Override
	public String newTraceCode(String companyId) {
		companyMapper.updateTraceSerialNum(companyId);
		Company company = companyMapper.selectByPrimaryKey(companyId);
		if(company!=null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
			String dateStr = sdf.format(new Date());
			String fullSerialNum = StringUtil.formatFillZeroLeft(5, company.getTraceSerialNum()+"");
			return company.getCode()+dateStr+company.getTraceSerialNum()+fullSerialNum;
		}
		return null;
	}

}
