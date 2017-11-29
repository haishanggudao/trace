package cn.rfidcer.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.rfidcer.DefaultContext;
import cn.rfidcer.bean.Goods;

public class GoodsDaoTest extends DefaultContext{
	
	@Autowired
	private GoodsDao goodsDao;
	
	@Test
	public void findByProductidStandarddetailidBatchTest(){
		String productId = "AC9D651235704F8BBED4E64D4D416438"; 
		String productStandardDetailId = "0b8b7c3cc63211e590b100505697467e"; 
		String goodsBatch = "2016129105624";
		
		Goods myGoods = new Goods();
		
		myGoods = goodsDao.findByProductidStandarddetailidBatch(productId, productStandardDetailId, goodsBatch);
		
		System.out.println(myGoods);
	}
	
	@Test
	public void findTest(){
		Goods myGoods = new Goods();
		int resultCount = goodsDao.findCountGoods(myGoods);
		System.out.println(resultCount);
	}
	
	@Test
	public void getGoodsListTest(){
		Goods myGoods = new Goods();
		
		List<Goods> myGoodsList = new ArrayList<Goods>();
		
		myGoods.setCompanyId("d25652e7aa2840dfac6ef9646bb7eaa4");
		//myGoodsList = goodsDao.getGoodsList(null, myGoods);
		
		System.out.println( myGoodsList );
	}

}
