package cn.rfidcer.controller.yz;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.rfidcer.bean.yz.YzProductStock;
import cn.rfidcer.dto.ResponseData;
import cn.rfidcer.service.yz.YzStockService;

/**   
* @Description:羽众库存
* @author 席志明
* @date 2016年8月30日 上午9:27:29 
* @version V1.0   
*/
@Controller
@RequestMapping("/mobileStock")
@Api(tags="stock",description="商品库存接口")
public class YzStockController {

	@Autowired
	private YzStockService yzStockService;
	
	
	@RequestMapping(value="/GetGoodsInventoryListByPage",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	@ApiOperation(value="店铺商品库存列表", httpMethod ="POST", response=ResponseData.class, 
		notes ="获取店铺商品库存")
	@ApiImplicitParams(
			{
				@ApiImplicitParam(name="entId",value="企业ID",required=true,dataType="String",paramType="form"),
				@ApiImplicitParam(name="currPageIndex",value="当前页(1开始)",dataType="int",paramType="form"),
				@ApiImplicitParam(name="pageSize",value="每页行数",dataType="int",paramType="form"),
				@ApiImplicitParam(name="code",value="授权码",required=true,dataType="String",paramType="form")
				})
	public ResponseData<List<YzProductStock>> getGoodsInventoryListByPage(@RequestParam String entId,
			@RequestParam(required=false,defaultValue="1") int currPageIndex,
			@RequestParam(required=false,defaultValue="10") int pageSize,
			@RequestParam String code){
		return yzStockService.getGoodsInventoryListByPage(entId, currPageIndex, pageSize);
	}
}
