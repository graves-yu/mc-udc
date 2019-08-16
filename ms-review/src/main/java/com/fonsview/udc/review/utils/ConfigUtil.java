package com.fonsview.udc.review.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.fonsview.udc.review.constants.Constants;

public class ConfigUtil {

	private static final Logger log = Logger.getLogger(ConfigUtil.class);
	public static final Properties properties = new Properties();
	private static File file = null;
	
	/**
	 * 获取配置文件属性
	 */
	 public static void init()  {
		FileReader reader = null;
		try {
			file = new File(Constants.SYNC_DATA_ETC_PATH+"config.properties");
			reader = new FileReader(file);
			properties.load(reader);
		} catch (Exception e) {
			log.error("init config.properties exception.", e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {

				}
			}
		}
	}
	 public static String getProperties(String key){
		 if(StringUtils.isEmpty(key)){
			 return null;
		 }else{
			return  properties.getProperty(key.toLowerCase());
		 }
	 }
}
