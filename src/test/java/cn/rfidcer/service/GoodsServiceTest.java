package cn.rfidcer.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.rfidcer.DefaultContext;
import cn.rfidcer.bean.TreeBean;
import cn.rfidcer.service.GoodsService;

public class GoodsServiceTest extends DefaultContext{

	@Autowired
	private GoodsService goodsService;
	
	@Test
	public void getGoodsTypesTest(){
		//List<TreeBean> goodsTypes = goodsService.getGoodsTypes(null);
		//System.out.println(goodsTypes);
	}
}
