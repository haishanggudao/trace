package cn.rfidcer.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

public interface MyCache<K, V> extends Cache<K, V>{

	/**新增自定义的过期缓存
	 * @param key
	 * @param value
	 * @param expire
	 * @return
	 * @throws CacheException
	 */
	public V put(K key, V value,int expire) throws CacheException;
}
