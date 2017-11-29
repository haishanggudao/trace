package cn.rfidcer.bean;

/**   
  * @Title: 手持机验证字段
  * @author xzm
  * @date 2016年6月29日 上午9:27:07 
  * @Copyright Copyright
  * @version V1.0   
  * @Description 
*/
public class UserToken {
	/**用户名*/
	private String username;
	/**token*/
	private String token;
	//无参构造器
	public UserToken(){}
	//有参构造器
	public UserToken(String username, String token) {
		this.username = username;
		this.token = token;
	}

	/**
	 * 获取 用户名
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 设置 用户名
	 * @param username 用户名
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 获取 token
	 * @return token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * 设置 token
	 * @param token token
	 */
	public void setToken(String token) {
		this.token = token;
	}
	
	
}
