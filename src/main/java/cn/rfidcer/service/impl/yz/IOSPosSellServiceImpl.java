package cn.rfidcer.service.impl.yz;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.rfidcer.bean.Company;
import cn.rfidcer.bean.yz.SaleOrderTotalMoney;
import cn.rfidcer.bean.yz.StoreSaleItem;
import cn.rfidcer.bean.yz.StoreSaleOrder;
import cn.rfidcer.bean.yz.WsSaleOrder;
import cn.rfidcer.dao.CompanyMapper;
import cn.rfidcer.dao.yz.StoreSaleItemMapper;
import cn.rfidcer.dao.yz.StoreSaleOrderMapper;
import cn.rfidcer.dto.Data;
import cn.rfidcer.dto.ResponseData;
import cn.rfidcer.dto.TimeRange;
import cn.rfidcer.service.yz.IOSPosSellService;
import cn.rfidcer.util.DateUtil;
import cn.rfidcer.util.PageUtil;

@Service
public class IOSPosSellServiceImpl implements IOSPosSellService {
	
	@Autowired
	private CompanyMapper companyMapper;
	
	@Autowired
	private StoreSaleOrderMapper storeSaleOrderMapper;
	
	@Autowired
	private StoreSaleItemMapper storeSaleItemMapper;

	@Override
	public ResponseData<List<WsSaleOrder>> GetSaleListByPage(String storeId, String entId, int dateType, int currPageIndex, int pageSize) {
		ResponseData<List<WsSaleOrder>> res=new ResponseData<List<WsSaleOrder>>();
		
		if (StringUtils.isEmpty(storeId)) {
			res.setResult(-1);
			res.setError("门店id不能为空");
			return res;
		}
		
		// call util: get startTime and endTime
		TimeRange timeRange = DateUtil.getTimeRangeByDateType(dateType);  
		
		StoreSaleOrder storeSaleOrder = new StoreSaleOrder();
		storeSaleOrder.setCompanyId(storeId);
		storeSaleOrder.setEntId(entId);
		
		// DAO action: find all sell orders
		List<StoreSaleOrder> myStoreSaleOrders = storeSaleOrderMapper.findAllWithDataType(PageUtil.getPage(currPageIndex, pageSize), storeSaleOrder, timeRange.getStartTime(), timeRange.getEndTime());
		
		List<WsSaleOrder> mWsSaleOrders = new ArrayList<WsSaleOrder>();
		for (StoreSaleOrder mySaleOrder : myStoreSaleOrders) {
			WsSaleOrder wsSaleOrder = new WsSaleOrder();
			wsSaleOrder.setGuid(mySaleOrder.getStoreSaleOrderId());
			wsSaleOrder.setSaleNo(mySaleOrder.getStoreSaleOrdeNo());
			wsSaleOrder.setSaleDate(DateUtil.format(mySaleOrder.getOrderTime()));
			
			wsSaleOrder.setTotalMoney(mySaleOrder.getTotalPrice().doubleValue());
			
			Company myCompany = companyMapper.selectByPrimaryKey(mySaleOrder.getCompanyId());
			wsSaleOrder.setStoreID(mySaleOrder.getCompanyId());
			wsSaleOrder.setStoreName(myCompany.getName());
			
			StoreSaleItem storeSaleItem = new StoreSaleItem();
			storeSaleItem.setStoreSaleOrderId(mySaleOrder.getStoreSaleOrderId());
			storeSaleItem.setStoreID(storeId);
			storeSaleItem.setEntId(entId);
			
			// DAO action: get the total money
			SaleOrderTotalMoney mySaleOrderTotalMoney = storeSaleItemMapper.getSaleTotalMoney(null, storeSaleItem);
			double totalSale = new Double(mySaleOrderTotalMoney.getTotalMoney());
			double totalCutOff = new Double(mySaleOrderTotalMoney.getTotalCutOff());
			wsSaleOrder.setTotalMoney( totalSale);
			wsSaleOrder.setTotalCutOff(totalCutOff);
			
			//wsSaleOrder.setUpdateTime(mySaleOrder.getUpdateTime().toString());
			// wsSaleOrder.setUpdateUser(mySaleOrder.getUpdateBy());
			//wsSaleOrder.setCreateTime(mySaleOrder.getCreateTime().toString());
			//wsSaleOrder.setCreateUser(mySaleOrder.getCreateBy());
			
			wsSaleOrder.setMemberID("");
			
			
			mWsSaleOrders.add(wsSaleOrder);
		}
		
		res.setValue(new Data<List<WsSaleOrder>>(mWsSaleOrders));
		
		res.setResult(1);
		
		res.setSynTime( new Date() );
		
		return res;
	}

	@Override
	public ResponseData<Integer> GetSaleRecordCount(String storeId, String entId, int dateType) {
		ResponseData<Integer> res=new ResponseData<Integer>();
		
		if (StringUtils.isEmpty(storeId)) {
			res.setResult(-1);
			res.setError("门店id不能为空");
			return res;
		}
		
		// call util: get startTime and endTime
		TimeRange timeRange = DateUtil.getTimeRangeByDateType(dateType);
		
		StoreSaleOrder storeSaleOrder = new StoreSaleOrder();
		storeSaleOrder.setCompanyId(storeId);
		
		// DAO action: find all sell orders
		List<StoreSaleOrder> myStoreSaleOrders = storeSaleOrderMapper.findAllWithDataType(null, storeSaleOrder, timeRange.getStartTime(), timeRange.getEndTime()); 
		
		int irecord = 0;
		
		irecord = myStoreSaleOrders.size();
		
		res.setValue(new Data<Integer>(irecord));
		
		res.setResult(1);
		
		res.setSynTime( new Date() );
		
		return res; 
	}

}
