package cn.rfidcer.util;

import java.io.IOException;
import java.util.Properties;

public class ConfigUtil {
	private static Properties properties;

	static{
		properties=new Properties();
		try {
			properties.load(ConfigUtil.class.getClassLoader().getResourceAsStream("resources.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String key){
		return properties.getProperty(key);
	}
	
}
