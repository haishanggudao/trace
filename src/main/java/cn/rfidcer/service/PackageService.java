package cn.rfidcer.service;

import java.util.List;
 
import cn.rfidcer.bean.PackageGoodsItem;
import cn.rfidcer.bean.PackageMain;
import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.interceptor.Page;

/**
 * 包装绑定信息Business Logic Layer
 * @author jie.jia at 2016-01-11 11:35 
 */
public interface PackageService {

	/**
	 * add the main package; created by jie.jia at 2016-01-11 17:23
	 * @param packageMain
	 * @param currentUser
	 * @return
	 */
	ResultMsg createOrUpdatePackage(PackageMain packageMain, User currentUser);
	
	/**
	 * delete the main package and its items; created by jie.jia at 2016-01-12 16:48
	 * @param packageMain
	 * @return
	 */
	ResultMsg deletePackage(PackageMain packageMain);
	
	/**
	 * query the package main; created by jie.jia at 2016-01-11 16:31
	 * @param page
	 * @param packageMain
	 * @return
	 */
	List<PackageMain> findAll(Page page, PackageMain packageMain);
	
	/**
	 * find all items of main package; created by jie.jia at 2016-01-12 14:52
	 * @param page
	 * @param packageGoodsItem
	 * @return
	 */
	List<PackageGoodsItem> findAllItems(Page page, PackageGoodsItem packageGoodsItem);
	
	/**
	 * query the parent
	 * @param page
	 * @param packageMain
	 * @return
	 */
	List<PackageMain> findParentalPackageMains(Page page, PackageMain packageMain);
	
}
