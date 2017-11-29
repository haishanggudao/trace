package cn.rfidcer.service.impl;


import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import cn.rfidcer.bean.PackageGoodsItem;
import cn.rfidcer.bean.PackageMain; 
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.dao.PackageGoodsItemMapper;
import cn.rfidcer.dao.PackageMainMapper;
import cn.rfidcer.interceptor.Page;
import cn.rfidcer.service.PackageService;
import cn.rfidcer.util.CommonImportUtils;
import cn.rfidcer.util.ResultMsgUtil;
import cn.rfidcer.util.UUIDGenerator;

@Service
public class PackageServiceImpl implements PackageService {
	
	private Logger logger=LoggerFactory.getLogger(PackageServiceImpl.class);
	
	@Autowired
	private PackageMainMapper packageMainDao;
	
	@Autowired
	private PackageGoodsItemMapper packageGoodsItemDao;

	@Override
	public List<PackageMain> findAll(Page page, PackageMain packageMain) { 
		return packageMainDao.findAll(page, packageMain);
	}
	
	@Override
	public List<PackageGoodsItem> findAllItems(Page page, PackageGoodsItem packageGoodsItem) { 
		return packageGoodsItemDao.findAll(page, packageGoodsItem);
	}
	
	@Override
	public List<PackageMain> findParentalPackageMains(Page page, PackageMain packageMain) { 
		return packageMainDao.findParentPackageMains(page, packageMain);
	}

	@Override
	public ResultMsg createOrUpdatePackage(PackageMain packageMain, User user) { 
		int res=0;
		String msg=null;
		
		String myMainPackageID = "";
		
		if(StringUtils.isEmpty( packageMain.getPackageMainId() )){
			logger.info("新增包装绑定信息："+packageMain);
			msg="新增包装绑定信息";
			CommonImportUtils.setCreateAndUpdateTime(packageMain, user);//设置创建日期和修改日期	
			
			myMainPackageID = UUIDGenerator.generatorUUID();
			packageMain.setPackageMainId( myMainPackageID );
			
			// assign value to operator
			packageMain.setTransNodeInteriorId("1");
			packageMain.setOperator(user.getId());
			
			// action: insert the main package
			res = packageMainDao.insertSelective(packageMain);
			
			// action: insert the items of main package
			if ( res == 1) {
				addPackageItems(packageMain, user);
			}
		} else {
			logger.info("编辑包装绑定信息："+packageMain);
			msg="编辑包装绑定信息";
			CommonImportUtils.setUpdateTime(packageMain, user);//设置修改日期
			myMainPackageID = packageMain.getPackageMainId();
			
			// action: update the main package
			res = packageMainDao.updateByPrimaryKeySelective(packageMain);
			
			
			if ( res == 1) {
				// action: delete the items by main ID
				packageGoodsItemDao.deletePackageGoodsItemsByPackageMainId(myMainPackageID);
				// action: insert the items of main package
				addPackageItems(packageMain, user);
			}
		}
		return ResultMsgUtil.getReturnMsg(res, msg);
	}
	
	/**
	 * add a new goods item of main package; created by jie.jia at 2016-01-12 14:12
	 * @param packageMain
	 * @param user
	 */
	private void addPackageItems(PackageMain packageMain, User user) {
		for (PackageGoodsItem  packageGoodsItem: packageMain.getPackageGoodsItems() ) {
			CommonImportUtils.setCreateAndUpdateTime(packageGoodsItem, user);//设置创建日期和修改日期	
			packageGoodsItem.setPackageGoodsItemId( UUIDGenerator.generatorUUID() );
			packageGoodsItem.setPackageMainId( packageMain.getPackageMainId() );
			packageGoodsItemDao.insertSelective(packageGoodsItem);
		}
	}

	@Override
	public ResultMsg deletePackage(PackageMain packageMain) { 
		logger.info("删除包装绑定信息 " + packageMain);
		int res=0;
		
		// action: delete the items of main package
		packageGoodsItemDao.deletePackageGoodsItemsByPackageMainId( packageMain.getPackageMainId() );
		
		// action: delete the main package
		res = packageMainDao.deleteByPrimaryKey( packageMain.getPackageMainId());
		
		return ResultMsgUtil.getReturnMsg(res, "删除包装绑定信息");
	}

	

	
	
}
