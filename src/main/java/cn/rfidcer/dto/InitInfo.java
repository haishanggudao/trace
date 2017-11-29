package cn.rfidcer.dto;

import cn.rfidcer.bean.ResultEntity;

public class InitInfo implements ResultEntity{

	private StoreConfig config;
	private DeviceSerial deviceSerial;
	public StoreConfig getConfig() {
		return config;
	}
	public void setConfig(StoreConfig config) {
		this.config = config;
	}
	public DeviceSerial getDeviceSerial() {
		return deviceSerial;
	}
	public void setDeviceSerial(DeviceSerial deviceSerial) {
		this.deviceSerial = deviceSerial;
	}
	
}
