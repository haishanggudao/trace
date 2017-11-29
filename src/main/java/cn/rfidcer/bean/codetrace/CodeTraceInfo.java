package cn.rfidcer.bean.codetrace;

import cn.rfidcer.bean.TraceLog;


public class CodeTraceInfo {
	
	private TraceLog tracelog;
	private String qrcode;
	
	public TraceLog getTracelog() {
		return tracelog;
	}
	public void setTracelog(TraceLog tracelog) {
		this.tracelog = tracelog;
	}
	public String getQrcode() {
		return qrcode;
	}
	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}
}
