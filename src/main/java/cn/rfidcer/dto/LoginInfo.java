package cn.rfidcer.dto;

import java.util.List;

import cn.rfidcer.bean.ResultEntity;
import cn.rfidcer.bean.StoreAccount;

public class LoginInfo implements ResultEntity{

	private List<StoreAccount> loginInfo;

	public List<StoreAccount> getLoginInfo() {
		return loginInfo;
	}

	public void setLoginInfo(List<StoreAccount> loginInfo) {
		this.loginInfo = loginInfo;
	}
	
}
