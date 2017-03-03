package com.jftt.wifi.common;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author:chenrui
 * @version:2015-5-22
 */
public class MyApplicationContext {
	static ClassPathXmlApplicationContext applicationContext = null;
	public static ClassPathXmlApplicationContext getInsatnce() {
		if (applicationContext == null) {
			return applicationContext = new ClassPathXmlApplicationContext("classpath:resourceConfig/springConfig/*.xml");
		}
		return applicationContext;
	}
}
