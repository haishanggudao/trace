package cn.rfidcer.util;

import cn.rfidcer.bean.ResultMsg;

public class ResultMsgUtil {

	
	public static ResultMsg getReturnMsg(int res,String msg){
		ResultMsg resultMsg = new ResultMsg();
		resultMsg.setCode(res+"");
		if( res==1){
			resultMsg.setMsg(msg+"成功");
		}else if(res==-1){
			resultMsg.setMsg(msg);
		}else{
			resultMsg.setMsg(msg+"失败");
		}
		return resultMsg;
	}
}
