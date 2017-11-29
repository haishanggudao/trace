package cn.rfidcer.bean;


/**   
  * @Title: 用户表
  * @author xzm
  * @date 2016年6月29日 上午9:25:29 
  * @Copyright Copyright
  * @version V1.0   
  * @Description 
*/
public class User {
	/**编号*/
    private String id;
    /**用户名*/
    private String username;
    /**密码*/
    private String password;
    /**加密密码的盐*/
    private String salt;
    /**用户昵称*/
    private String nickName;
    private String userCompanyIdsText;
    /**是否锁定，默认不锁定*/
    private Boolean locked = Boolean.FALSE;
    //无参构造器
    public User() {
    }
    //有参构造器
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    /**
	 * 获取 编号
	 * @return id
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置 编号
	 * @param id 编号
	 */
	public void setId(String id) {
		this.id = id;
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
	 * 获取 密码
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置 密码
	 * @param password 密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取 加密密码的盐
	 * @return salt
	 */
	public String getSalt() {
		return salt;
	}
	/**
	 * 设置 加密密码的盐
	 * @param salt 加密密码的盐
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}
	/**
	 * 获取 用户昵称
	 * @return nickName
	 */
	public String getNickName() {
		return nickName;
	}
	/**
	 * 设置 用户昵称
	 * @param nickName 用户昵称
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	/**
	 * 获取 是否锁定，默认不锁定
	 * @return locked
	 */
	public Boolean getLocked() {
		return locked;
	}
	/**
	 * 设置 是否锁定，默认不锁定
	 * @param locked 是否锁定，默认不锁定
	 */
	public void setLocked(Boolean locked) {
		this.locked = locked;
	}
	/**
	 * 获取 证书
	 * @return username + salt
	 */ 
    public String getCredentialsSalt() {
        return username + salt;
    }

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", salt=" + salt + ", locked="
				+ locked + "]";
	}
	public String getUserCompanyIdsText() {
		return userCompanyIdsText;
	}
	public void setUserCompanyIdsText(String userCompanyIdsText) {
		this.userCompanyIdsText = userCompanyIdsText;
	}
}
