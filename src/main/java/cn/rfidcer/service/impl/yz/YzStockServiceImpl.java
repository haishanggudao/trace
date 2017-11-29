package cn.rfidcer.service.impl.yz;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.rfidcer.bean.yz.YzProductStock;
import cn.rfidcer.dao.yz.YzStockMapper;
import cn.rfidcer.dto.Data;
import cn.rfidcer.dto.ResponseData;
import cn.rfidcer.service.yz.YzStockService;
import cn.rfidcer.util.PageUtil;

@Service
public class YzStockServiceImpl implements YzStockService{

	@Autowired
	private YzStockMapper yzStockMapper;
	
	@Override
	public ResponseData<List<YzProductStock>> getGoodsInventoryListByPage(String entId,
			int currPageIndex, int pageSize) {
		ResponseData<List<YzProductStock>> res = new ResponseData<List<YzProductStock>>();
		List<YzProductStock> stockList = yzStockMapper.getGoodsInventoryListByPage(entId, PageUtil.getPage(currPageIndex, pageSize));
		res.setResult(1);
		res.setSynTime(new Date());
		res.setValue(new Data<List<YzProductStock>>(stockList));
		return res;
	}

}
