package cn.rfidcer.cache;

import org.apache.shiro.cache.CacheException;

/**   
* @Title: MyCacheManager.java 
* @Package cn.rfidcer.cache 
* @Description:自定义缓存管理接口
* @author 席志明
* @Copyright Copyright
* @date 2016年8月18日 下午2:37:59 
* @version V1.0   
*/
public interface MyCacheManager {

	 /**获取对应缓存
	 * @param name 缓存名称
	 * @param expire 缓存时间
	 * @return
	 * @throws CacheException
	 */
	public <K, V> MyCache<K, V> getCache(String name) throws CacheException;
}
