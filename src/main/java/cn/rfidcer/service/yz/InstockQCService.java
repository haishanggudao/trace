package cn.rfidcer.service.yz;

import java.util.List;

import cn.rfidcer.bean.ResultMsg;
import cn.rfidcer.bean.User;
import cn.rfidcer.bean.Instockqc;
import cn.rfidcer.interceptor.Page;

/**
 * 
 * @author 黎学扬
 * 
 * @date 2016年6月28日 下午4:00:19
 *
 */
public interface InstockQCService {

	/**
	 * @param page
	 * @param instockqc
	 * @return
	 */
	List<Instockqc> findAll(Page page, Instockqc instockqc);

	/**
	 * @param yzinstockqc
	 * @param currentUser
	 * @return
	 */
	ResultMsg updateInstockQC(Instockqc yzinstockqc, User currentUser);

	/**
	 * @param yzinstockqc
	 * @param currentUser
	 * @return
	 */
	ResultMsg saveInstockQC(Instockqc yzinstockqc, User currentUser);

	/**
	 * @param id
	 * @param url
	 * @return
	 */
	ResultMsg delteImage(String id, String url);
	
}