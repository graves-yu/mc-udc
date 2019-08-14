package com.fonsview.udc;

import java.net.URL;

import org.apache.log4j.xml.DOMConfigurator;

import com.fonsview.udc.bookmark.constants.Constants;

public class Log4jLister {

	public static void init() {
		try {
			URL url = new URL("file:"+Constants.SYNC_DATA_ETC_PATH+"log4j.xml");
			DOMConfigurator.configure(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
