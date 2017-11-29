package cn.rfidcer.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;

import cn.rfidcer.bean.TraceLog;
import cn.rfidcer.rocketmq.Producer;
import cn.rfidcer.service.IPService;

//@Service
public class IPServiceImpl implements IPService{

	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Producer producer;
	
	@Override
	public void sendIPInfoToMQ(String qrCode, HttpServletRequest request) {
		String ip = getIP(request);
		if(ip!=null){
			TraceLog traceLog = new TraceLog();
			traceLog.setIPAddress(ip);
			traceLog.setQrcode(qrCode);
			traceLog.setKernel(request.getHeader("User-agent"));
			traceLog.setCreateTime(new Date());
			Message msg;
			try {
				msg = new Message("IP",// topic
				        "trace",// tag
				        JSONObject.toJSONString(traceLog).getBytes("UTF-8"));
			SendResult result = producer.send(msg);
			logger.info(result.toString());
			} catch (UnsupportedEncodingException e) {
				logger.error(e.getMessage(),e);
			}
			
		}
	}

	private String getIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		ip = StringUtils.equalsIgnoreCase(ip, "0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
		return ip;
	}
}
