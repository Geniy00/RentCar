package ua.nau.rentcar.resource;

import java.util.ResourceBundle;

public class Resource {
	
	/**
	 * The path to properties file
	 */
	public static final String RESOURCE_PATH = "ua.nau.rentcar.resource.Resource";
	/**
	 * Resource created by some path and some location
	 */
	private static ResourceBundle resource = ResourceBundle.getBundle(RESOURCE_PATH);
	
	/**
	 * This method return string value by key
	 * @param key a name of property
	 * @return value of property
	 */
	public static String getStr(String key){
		return resource.getString(key);
	}
	
}
