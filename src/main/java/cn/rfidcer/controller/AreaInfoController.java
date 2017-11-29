package cn.rfidcer.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import cn.rfidcer.annotation.CurrentUser;
import cn.rfidcer.bean.AreaInfo;
import cn.rfidcer.bean.Company;
import cn.rfidcer.bean.CompanyField;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.AreaInfoService;
import cn.rfidcer.service.CompanyFieldService;
import cn.rfidcer.service.CompanyService;

/**
* @Title: CompanyController.java 
* @Package cn.rfidcer.controller 
* @Description: Controller 企业信息 
* @author jie.jia
* @Copyright Copyright
* @date 2016年6月27日 下午7:46:43 
* @version V1.0
 */
@Controller
@RequestMapping("/areaInfo")
public class AreaInfoController {
	
	@Autowired
	private AreaInfoService areaInfoService;
	
	/**
	 * 获取省；
	 * @return
	 */
	@RequestMapping(value="/getprovinces")
	@ResponseBody
	public List<AreaInfo> getProvinces() {
		return areaInfoService.getProvinces();
	}
	
	/**
	 * 获取市；
	 * @param proviceId
	 * @return
	 */
	@RequestMapping(value="/getcitys")
	@ResponseBody
	public List<AreaInfo> getCitys(String proviceId) {
		return areaInfoService.getCitys(proviceId);
	}
	
	/**
	 * 获取区；
	 * @param proviceId
	 * @param cityId
	 * @return
	 */
	@RequestMapping(value="/getareas")
	@ResponseBody
	public List<AreaInfo> getAreas(String proviceId,String cityId) {
		return areaInfoService.getAreas(proviceId, cityId);
	}
	
	@RequestMapping(value="/areaListWithQuery")
	@ResponseBody
	public Map<String, Object> findAllAreaListWithQuery(Page page,@CurrentUser User user,AreaInfo ai) {
//		List<AreaInfo> lst = areaInfoService.findAllWithQuery(page, ai);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", areaInfoService.findAllWithQuery(page, ai));
		return map;
	}
	
	/**
	 * 获取区信息列表;
	 * @param page
	 * @param user
	 * @param ai
	 * @return
	 */
	@RequestMapping(value="/newAreaListUseByCatgName")
	@ResponseBody
	public Map<String , Object> findAllNewAreaListUseByCatgName(Page page,@CurrentUser User user,AreaInfo ai) {
        Map<String,Object> map=new HashMap<String,Object>();
		map.put("rows", areaInfoService.findAllCatgNameAndId(page, ai));
        return map;
	}

}
