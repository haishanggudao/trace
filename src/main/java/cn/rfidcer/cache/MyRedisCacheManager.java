package cn.rfidcer.cache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.shiro.cache.CacheException;
import org.crazycake.shiro.RedisManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**   
* @Title: MyRedisCacheManager.java 
* @Package cn.rfidcer.cache 
* @Description: 自定义redis缓存
* @author 席志明
* @Copyright Copyright
* @date 2016年8月18日 下午2:40:22 
* @version V1.0   
*/
public class MyRedisCacheManager implements MyCacheManager{

	private static final Logger logger = LoggerFactory
			.getLogger(MyRedisCacheManager.class);

	// fast lookup by name map
	@SuppressWarnings("rawtypes")
	private final ConcurrentMap<String, MyCache> caches = new ConcurrentHashMap<String, MyCache>();

	private RedisManager redisManager;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <K, V> MyCache<K, V> getCache(String name)
			throws CacheException {
		logger.debug("获取名称为: " + name + " 的RedisCache实例");
		
		MyCache c = caches.get(name);
		
		if (c == null) {

			// initialize the Redis manager instance
			redisManager.init();
			
			// create a new cache instance
			c = new RedisExpireCache<K, V>(redisManager, name+":");
			
			// add it to the cache collection
			caches.put(name, c);
		}
		return c;
	}

	/**获取#{bare_field_comment}
	 * @return the #{bare_field_comment}
	 */
	public RedisManager getRedisManager() {
		return redisManager;
	}

	/**设置#{bare_field_comment}
	 * @param redisManager the #{bare_field_comment} to set
	 */
	public void setRedisManager(RedisManager redisManager) {
		this.redisManager = redisManager;
	}
	
}
