package cn.rfidcer.service.impl.yz;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.rfidcer.bean.AreaInfo;
import cn.rfidcer.bean.ClientUser;
import cn.rfidcer.bean.Company;
import cn.rfidcer.bean.Customers;
import cn.rfidcer.bean.yz.GoodsInfoList;
import cn.rfidcer.bean.yz.Province;
import cn.rfidcer.bean.yz.SaleTotal;
import cn.rfidcer.bean.yz.SaleTotalMoney;
import cn.rfidcer.bean.yz.StoreSaleItem;
import cn.rfidcer.bean.yz.StoreSaleOrder;
import cn.rfidcer.bean.yz.SysUser;
import cn.rfidcer.bean.yz.WsSale;
import cn.rfidcer.bean.yz.WsSaleDetail;
import cn.rfidcer.bean.yz.WsSaleOrder;
import cn.rfidcer.bean.yz.WsSellStore;
import cn.rfidcer.bean.yz.YzProductType;
import cn.rfidcer.bean.yz.YzStoreSale;
import cn.rfidcer.bean.yz.YzUnit;
import cn.rfidcer.dao.ClientUserMapper;
import cn.rfidcer.dao.CompanyMapper;
import cn.rfidcer.dao.CustomersMapper;
import cn.rfidcer.dao.yz.GoodsInfoMapper;
import cn.rfidcer.dao.yz.StoreSaleItemMapper;
import cn.rfidcer.dao.yz.StoreSaleOrderMapper;
import cn.rfidcer.dao.yz.YzProductTypeMapper;
import cn.rfidcer.dto.Data;
import cn.rfidcer.dto.ResponseData;
import cn.rfidcer.dto.TimeRange;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.AreaInfoService;
import cn.rfidcer.service.yz.IOSPosService;
import cn.rfidcer.util.DateUtil;
import cn.rfidcer.util.PageUtil;

@Service
public class IOSPosServiceImpl implements IOSPosService {
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AreaInfoService areaInfoService;
	
	@Autowired
	private ClientUserMapper clientUserMapper;
	
	@Autowired
	private CompanyMapper companyMapper;
	
	@Autowired
	private CustomersMapper customersMapper;
	
	@Autowired
	private StoreSaleOrderMapper storeSaleOrderMapper;
	
	@Autowired
	private StoreSaleItemMapper storeSaleItemMapper;
	
	@Autowired
	private GoodsInfoMapper goodsInfoMapper;

	@Autowired
	private YzProductTypeMapper yzProductTypeMapper;
	 
	@Override 
	public ResponseData<ArrayList<Province>> getAllProvince() {
		ResponseData<ArrayList<Province>> myResponse = new ResponseData<ArrayList<Province>>();
		
		List<AreaInfo> areaInfos = areaInfoService.getProvinces();
		
		ArrayList<Province> myProvices = new ArrayList<Province>();
		
		for (AreaInfo areaInfo : areaInfos) {
			Province province = new Province();
			province.setRegion_code( areaInfo.getId() );
			province.setRegion_name( areaInfo.getCatgName());
			myProvices.add(province);  
		} 
		
		myResponse.setValue(new Data<ArrayList<Province>>(myProvices));
		
		myResponse.setResult(1);
		
		myResponse.setSynTime( new Date() );
		
		return myResponse;
	}

	@Override
	public ResponseData<SysUser> SysLogin(SysUser sysUser) {
		ResponseData<SysUser> res=new ResponseData<SysUser>();
		res.setSynTime(new Date());
		if(sysUser==null||StringUtils.isEmpty(sysUser.getEnterpriseNodeNo())||StringUtils.isEmpty(sysUser.getPsw())){
			res.setResult(-1);
			res.setError("用户名或密码不能为空");
			return res;
		}
		ClientUser user = clientUserMapper.selectByUserName(sysUser.getEnterpriseNodeNo());
		if(user==null){
			res.setResult(-2);
			res.setError("用户不存在");
		}else if(user.getClientPassword().equals(sysUser.getPsw())){
			String companyId = user.getCompanyId();
			Company company = companyMapper.selectByPrimaryKey(companyId);
			if(company==null){
				res.setResult(-4);
				res.setError("企业不存在");
			}else{
				res.setResult(1);
				sysUser.setGuid(companyId);
				sysUser.setEnterpriseName(company.getName());
				sysUser.setPsw(null);
				res.setValue(new Data<SysUser>(sysUser));
			}
		}else{
			res.setResult(-3);
			res.setError("密码不正确");
		}
		return res;
	}

	@Override
	public ResponseData<ArrayList<Province>> getAllCity(String provinceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseData<ArrayList<WsSellStore>> GetAllStoreList(String entId) {
		ResponseData<ArrayList<WsSellStore>> res=new ResponseData<ArrayList<WsSellStore>>();
		
		if (StringUtils.isEmpty(entId)) {
			res.setResult(-1);
			res.setError("企业id不能为空");
			return res;
		}
		
		// DAO action: get customers by entId
		List<Customers> myCustomers = customersMapper.findCustomerList(entId);
		
		if (myCustomers.size() == 0) {
			res.setResult(-2);
			res.setError("查不到门店信息");
			return res;
		}
		
		ArrayList<WsSellStore> myWsSellStore = new ArrayList<WsSellStore>();
		
		for (Customers customer : myCustomers) {
			WsSellStore sellStore = new WsSellStore();
			sellStore.setGuid(customer.getCustCompanyId());
			sellStore.setEnterpriseID(entId);
			sellStore.setStoreName(customer.getCustomerAlias());
			
			// DAO action: get company by custCompanyId
			Company myCompany = companyMapper.selectByPrimaryKey(customer.getCustCompanyId());
			
			sellStore.setStoreNo(myCompany.getCode());
			sellStore.setAddress(myCompany.getAddress());
			sellStore.setTelephone(myCompany.getTel());
			sellStore.setEmail(myCompany.getEmail());
			
			sellStore.setUpdateUser(myCompany.getUpdateBy());
			sellStore.setUpdateTime(myCompany.getUpdateTime().toString());
			sellStore.setCreateUser(myCompany.getCreateBy());
			sellStore.setCreateTime(myCompany.getCreateTime().toString()); 
			
			myWsSellStore.add(sellStore);
		}
		
		res.setValue(new Data<ArrayList<WsSellStore>>(myWsSellStore));
		
		res.setResult(1);
		
		res.setSynTime( new Date() );
		
		return res;
		
	}

	
	@Override
	public ResponseData<SaleTotal> AppHome(String entId) {
		ResponseData<SaleTotal> res=new ResponseData<SaleTotal>();
		if (StringUtils.isEmpty(entId)) {
			res.setResult(-1);
			res.setError("企业id不能为空");
			return res;
		}
		List<Customers> myCustomers = customersMapper.findCustomerList(entId);
		if (myCustomers.size() == 0) {
			res.setResult(-2);
			res.setError("查不到门店信息");
			return res;
		}
		
		String dateTime= DateUtil.dateTime();
		SaleTotal count =   storeSaleOrderMapper.todaySaleOrderCount(entId, dateTime+" 00:00:00", dateTime+" 23:59:59");
		if(count!=null&&!count.getSaleCount().equals("0")){
			SaleTotal saleTotal =   storeSaleOrderMapper.todaySaleTotalPrice(entId, dateTime+" 00:00:00", dateTime+" 23:59:59");
			count.setSaleAmount(saleTotal.getSaleAmount());
		}
		res.setValue(new Data<SaleTotal>(count));
		res.setResult(1);
		res.setSynTime( new Date() );
		return res;
	}
	
	@Override
	public ResponseData<SaleTotalMoney> GetSaleTotalMoney(String storeID, String entId,int dateType) {
		ResponseData<SaleTotalMoney> res = new ResponseData<SaleTotalMoney>();
		if (StringUtils.isEmpty(storeID)) {
			res.setResult(-1);
			res.setError("企业id不能为空");
			return res;
		}
		TimeRange timeRange = DateUtil.getTimeRangeByDateType(dateType);
		SaleTotalMoney saleTotalMoney =   storeSaleOrderMapper.getSaleTotalMoney(storeID, entId,timeRange.getStartTime(), timeRange.getEndTime());
		if(saleTotalMoney==null){
			saleTotalMoney=new SaleTotalMoney();
		}else{
			int totalNum = storeSaleOrderMapper.getSaleTotalCount(storeID, entId, timeRange.getStartTime(), timeRange.getEndTime());
			saleTotalMoney.setTotalNum(totalNum);
		}
		res.setValue(new Data<SaleTotalMoney>(saleTotalMoney));
		res.setResult(1);
		res.setSynTime( new Date() );
		return res;
	}

	@Override
	public ResponseData<ArrayList<GoodsInfoList>> GetGoodsInfoListByPage(String enId, String goodCodeOrName, int currPageIndex,int pageSize) {
		ResponseData<ArrayList<GoodsInfoList>> res=new ResponseData<ArrayList<GoodsInfoList>>();
		if (StringUtils.isEmpty(enId)) {
			res.setResult(-1);
			res.setError("企业id不能为空");
			return res;
		}
		
		ArrayList<GoodsInfoList> goodsInfoList = goodsInfoMapper.getGoodsInfoListByPage(PageUtil.getPage(currPageIndex, pageSize), enId, goodCodeOrName);
		for (GoodsInfoList goodsInfo : goodsInfoList) {
			if(goodsInfo.getSaleState().equals("0")){
				goodsInfo.setSaleState("销售中");
				goodsInfo.setIsForbidden(true);

			}else{
				goodsInfo.setSaleState("已下架");
				goodsInfo.setIsForbidden(false);
			}
		}
		res.setValue(new Data<ArrayList<GoodsInfoList>>(goodsInfoList));
		res.setResult(1);
		res.setSynTime( new Date() );
		logger.info(goodsInfoList.toString());
		return res;
	}

	@Override
	public ResponseData<WsSale> GetSaleDetailInfo(String saleId) {
		ResponseData<WsSale> res=new ResponseData<WsSale>();
		
		WsSale myWsSale = new WsSale();
		
		if (StringUtils.isEmpty(saleId)) {
			res.setResult(-1);
			res.setError("销售单id不能为空");
			return res;
		}
		
		// DAO action: get sale order by saleId
		StoreSaleOrder mySaleOrder = storeSaleOrderMapper.selectByPrimaryKey(saleId);
		
		if ( mySaleOrder == null ) {
			res.setResult(-2);
			res.setError("查不到销售主单信息");
			return res;
		} else {
			WsSaleOrder wsSaleOrder = new WsSaleOrder();
			wsSaleOrder.setGuid(mySaleOrder.getStoreSaleOrderId());
			wsSaleOrder.setSaleNo(mySaleOrder.getStoreSaleOrdeNo());
			wsSaleOrder.setSaleDate(DateUtil.format(mySaleOrder.getOrderTime()));
			
			Company myCompany = companyMapper.selectByPrimaryKey(mySaleOrder.getCompanyId());
			wsSaleOrder.setStoreID(mySaleOrder.getCompanyId());
			wsSaleOrder.setStoreName(myCompany.getName());
			
			wsSaleOrder.setUpdateTime(mySaleOrder.getUpdateTime().toString());
			wsSaleOrder.setUpdateUser(mySaleOrder.getUpdateBy());
			wsSaleOrder.setCreateTime(mySaleOrder.getCreateTime().toString());
			wsSaleOrder.setCreateUser(mySaleOrder.getCreateBy());
			
			wsSaleOrder.setMemberID("");
			wsSaleOrder.setTotalCutOff(0);
			
			myWsSale.setSaleInfo(wsSaleOrder);
		}
		
		StoreSaleItem myStoreSaleItem = new StoreSaleItem();
		myStoreSaleItem.setStoreSaleOrderId(saleId);
		// DAO action: get sale items by saleId
		List<StoreSaleItem> mySaleItems = storeSaleItemMapper.findAll(null, myStoreSaleItem);
		
		if (mySaleItems.size() == 0) {
			res.setResult(-3);
			res.setError("查不到销售详细信息");
			return res;
		}
		
		List<WsSaleDetail> myWsSaleDetails = new ArrayList<WsSaleDetail>();
		int DisplayNO = 1;
		for (StoreSaleItem saleItem : mySaleItems) {
			WsSaleDetail wsSaleDetail = new WsSaleDetail();
			wsSaleDetail.setGuid(saleItem.getStoreSaleItemId());
			wsSaleDetail.setSaleNo(saleItem.getStoreSaleOrderId()); 
				
			wsSaleDetail.setGoodsID(saleItem.getGoodsId());
			wsSaleDetail.setGoodsName(saleItem.getProductName());
			wsSaleDetail.setDisplayNO(DisplayNO++);
			
			wsSaleDetail.setSalePrice(saleItem.getSalePrice().doubleValue());
			wsSaleDetail.setSaleNumber(saleItem.getQuantity().doubleValue());
			wsSaleDetail.setAmount( saleItem.getTotalPrice().doubleValue());
			wsSaleDetail.setUnitName(saleItem.getUnitName());
			
			wsSaleDetail.setCreateUser(saleItem.getCreateBy());
			wsSaleDetail.setCreateTime(saleItem.getCreateTime().toString());
			wsSaleDetail.setUpdateUser(saleItem.getUpdateBy());
			wsSaleDetail.setUpdateTime(saleItem.getUpdateTime().toString());
			 
			
			
			myWsSaleDetails.add(wsSaleDetail);
		}
		myWsSale.setSaleDetailList(myWsSaleDetails);
		
		res.setValue(new Data<WsSale>(myWsSale));
		res.setResult(1);
		res.setSynTime( new Date() );
		return res;
	}

	@Override
	public ResponseData<List<YzProductType>> GetGoodsTypeList(String entId,
			String typeId) {
		ResponseData<List<YzProductType>> res = new ResponseData<List<YzProductType>>();
		if(StringUtils.isEmpty(entId)&&StringUtils.isEmpty(typeId)){
			res.setResult(0);
			res.setError("企业ID和父分类ID不能同时为空");
		}else{
			List<YzProductType> types = yzProductTypeMapper.queryYzProductTypes(entId, typeId);
			res.setValue(new Data<List<YzProductType>>(types));
			res.setResult(1);
		}
		res.setSynTime(new Date());
		return res;
	}

	@Override
	public ResponseData<List<YzStoreSale>> GetStoreSaleTop10(String entId, int dateType, int currPageIndex,
			int pageSize) {
		ResponseData<List<YzStoreSale>> res = new ResponseData<List<YzStoreSale>>();
		TimeRange timeRange = DateUtil.getTimeRangeByDateType(dateType);
		Page page = PageUtil.getPage(currPageIndex, pageSize);
		List<YzStoreSale> storeSaleTops = storeSaleOrderMapper.getStoreSaleTop10(entId, timeRange.getStartTime(), timeRange.getEndTime(), page);
		res.setResult(1);
		res.setSynTime(new Date());
		res.setValue(new Data<List<YzStoreSale>>(storeSaleTops));
		return res;
	}

	@Override
	public ResponseData<List<YzUnit>> getUnitList(String entId) {
		ResponseData<List<YzUnit>> res = new ResponseData<List<YzUnit>>();
		res.setResult(1);
		res.setSynTime(new Date());
		List<YzUnit> list=new ArrayList<YzUnit>();
		YzUnit yzUnit = new YzUnit();
		yzUnit.setGuid("bb1978f7d13645629cc8f51aa5c5608d");
		yzUnit.setUnitCode("");
		yzUnit.setUnitName("L");
		list.add(yzUnit);
		res.setValue(new Data<List<YzUnit>>(list));
		return res;
	}
}
