package cn.rfidcer.controller;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.rfidcer.annotation.AvoidDuplicateSubmission;
import cn.rfidcer.bean.ResultMsg;


/**   
* @Title: TokenController.java 
* @Package cn.rfidcer.controller 
* @Description: Controller token
* @author jie.jia
* @Copyright Copyright
* @date 2016年6月29日 上午11:32:19 
* @version V1.0   
*/
@Controller
@RequestMapping("/token")
public class TokenController {

	/**
	 * 创建token
	 * @param httpSession
	 * @return
	 */
	@AvoidDuplicateSubmission(needSaveToken = true)
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public ResultMsg save(HttpSession httpSession) {
		String serverToken = (String) httpSession.getAttribute("token");
		ResultMsg  msg = new ResultMsg();
		msg.setCode("1");
		msg.setMsg(serverToken);
		return msg;
	}

}
