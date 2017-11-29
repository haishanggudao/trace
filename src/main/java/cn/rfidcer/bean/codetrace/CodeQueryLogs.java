package cn.rfidcer.bean.codetrace;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class CodeQueryLogs {
    
	private String logId;
	
	private String qrcode;
    
    private Date checkDate;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkDate1;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkDate2;
    
    private String ipaddr;
    
    private long totalTimes;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date minDate;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date maxDate;

	public String getLogId() {
		return logId;
	}
	
	public void setLogId(String logId) {
		this.logId = logId;
	}
    
	public String getQrcode() {
		return qrcode;
	}
	
	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	

	public Date getCheckDate1() {
		return checkDate1;
	}

	public void setCheckDate1(Date checkDate1) {
		this.checkDate1 = checkDate1;
	}

	public Date getCheckDate2() {
		return checkDate2;
	}

	public void setCheckDate2(Date checkDate2) {
		this.checkDate2 = checkDate2;
	}

	public String getIpaddr() {
		return ipaddr;
	}

	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}

	public long getTotalTimes() {
		return totalTimes;
	}

	public void setTotalTimes(long totalTimes) {
		this.totalTimes = totalTimes;
	}

	public Date getMinDate() {
		return minDate;
	}

	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}

	public Date getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}
	
}
