package cn.rfidcer.controller.fruit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.rfidcer.annotation.CurrentUser;
import cn.rfidcer.bean.CompanyPage;
import cn.rfidcer.bean.Goods;
import cn.rfidcer.bean.GoodsDetail;
import cn.rfidcer.bean.User;
import cn.rfidcer.service.GoodsDetailService;

@Controller
@RequestMapping("/goods_fruit")
public class GoodsFruitController {
	
	@Autowired
	private GoodsDetailService goodsDetailService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(CompanyPage companyPage) {
		return "goods";
	}
	
	@RequestMapping(value="/getQRCodeAndUrl",produces={"application/json;charset=UTF-8"}) 
	@ResponseBody
	public List<GoodsDetail> getQRCodesAndUrl(Goods goods,@CurrentUser User currentUser){
		return goodsDetailService.createGoodsDetailAndUrl(goods, currentUser);
	}

}
