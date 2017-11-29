package cn.rfidcer.service.codetrace;

import javax.servlet.http.HttpServletRequest;

public interface CodeTraceInfoService {
	void saveCodeTraceInfo(String qrCode, HttpServletRequest request) throws Exception;
}
