package cn.rfidcer.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * token生成器
 * @author jie.jia
 * @date 2016-05-09 15:50
 */
public class TokenProcessor {

	private static TokenProcessor instance = new TokenProcessor();
	
	public static TokenProcessor getInstance() {
		return instance;
	}
	
	protected TokenProcessor(){
		super();
	}
	
	public String generateToken(HttpServletRequest request){
		HttpSession session = request.getSession();
		try {
			byte id[] = session.getId().getBytes();
			byte now[] = new Long(System.currentTimeMillis()).toString().getBytes();
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(id);
			md.update(now);
			return this.toHex(md.digest());
		} catch (IllegalStateException e) {
			return null;
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
	
	public String toHex(byte buffer[]) {
		StringBuffer sb = new StringBuffer();
		String s = null;
		for (int i = 0; i < buffer.length; i++) {
			s = Integer.toHexString((int) buffer[i] & 0xff);
			if (s.length() < 2) {
				sb.append('0');
			}
			sb.append(s);
		}
		return sb.toString();
	}
}
