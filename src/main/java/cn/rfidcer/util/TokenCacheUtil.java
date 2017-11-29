package cn.rfidcer.util;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import cn.rfidcer.bean.UserToken;

/**手持机token缓存工具类
 * @author xzm
 *
 */
/**
 * @author Administrator
 *
 */
public class TokenCacheUtil {

	private CacheManager cacheManager;
	
	private Cache<String, String> cache;
	
	private Cache<String,String> getTokenCache(){
		if(cache==null){
			cache=cacheManager.getCache("handtokenCache");
		}
		return cache;
	}
	
	/**
	 * 写入缓存; created by jie.jia at 2016-03-02 13:10
	 * @param clientUserName
	 * @param token
	 */
	public void putTokenToCache(String clientUserName, String token){
		getTokenCache().put(clientUserName, token);
	}
	

	/**比较缓存中的token
	 * @param userToken
	 * @return
	 */
	public boolean compareUserTokenFromCache(UserToken userToken){
		String token = getTokenCache().get(userToken.getUsername());
		if(token!=null&&token.equals(userToken.getToken())){
			return true;
		}
		return false;
	}


	public CacheManager getCacheManager() {
		return cacheManager;
	}


	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}
	
}
