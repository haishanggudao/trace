package cn.rfidcer.service.impl.codetrace;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.rfidcer.bean.TraceLog;
import cn.rfidcer.bean.codetrace.CodeTraceInfo;
import cn.rfidcer.dao.codetrace.CodeTraceInfoDao;
import cn.rfidcer.service.codetrace.CodeTraceInfoService;
import cn.rfidcer.util.UUIDGenerator;

@Service
public class CodeTraceInfoServiceImpl implements CodeTraceInfoService {

	@Autowired
	private CodeTraceInfoDao codeTraceInfoDao;
	
	@Override
	public void saveCodeTraceInfo(String qrCode, HttpServletRequest request) throws Exception {
		
		String ip = getIP(request);
		String header = request.getHeader("User-agent");
		CodeTraceInfo cti = new CodeTraceInfo();
		TraceLog tl = new TraceLog();
		try {
			String doGet = doGet(ip);
			JSONObject obj = JSONObject.parseObject(doGet);
			JSONObject data = obj.getJSONObject("data");
			String country = data.getString("country");
			String area =  data.getString("area");
			String region =  data.getString("region");
			String city =  data.getString("city");
			String isp =  data.getString("isp");
			tl.setArea(area);
			tl.setCity(city);
			tl.setCountry(country);
			tl.setIsp(isp);
			tl.setProvince(region);
		} catch (Exception e) {
		}
		tl.setTraceLogId(UUIDGenerator.generatorUUID());
		tl.setIPAddress(ip);
		tl.setKernel(header);
		tl.setCreateTime(new Date());
		
		cti.setTracelog(tl);
		cti.setQrcode(qrCode);
		
		codeTraceInfoDao.insertCodeTraceInfo(cti);
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
		ip = org.apache.commons.lang3.StringUtils.equalsIgnoreCase(ip, "0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
		return ip;
	}
	
	public String doGet(String ip) {
		String uriAPI = "http://ip.taobao.com/service/getIpInfo.php?ip=" + ip;
		String result = "";
		// 创建HttpGet或HttpPost对象，将要请求的URL通过构造方法传入HttpGet或HttpPost对象。
		try {
			HttpGet httpRequest = new HttpGet(uriAPI);
			// 使用DefaultHttpClient类的execute方法发送HTTP GET请求，并返回HttpResponse对象。
			HttpResponse httpResponse = HttpClientBuilder.create().build().execute(httpRequest);
			// 其中HttpGet是HttpUriRequst的子类
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				HttpEntity httpEntity = (HttpEntity) httpResponse.getEntity();
				result = EntityUtils.toString(httpEntity);
				// 取出应答字符串
				// 一般来说都要删除多余的字符
				result.replaceAll("\r", "");
				// 去掉返回结果中的"\r"字符，否则会在结果字符串后面显示一个小方格
			} else
				httpRequest.abort();
		} catch (Exception e) {
			e.printStackTrace();
			result = e.getMessage().toString();
		}
		return result;
	}
}
