package com.itay.wrapper;

public class WrapperConfig {
	
	public static enum CacheMode {
		
		/**
		 * When the object is created it will call the native handlers to set the data and than just set it when needed
		 */
		ON_CREATE,
		
		/**
		 * The local Java data will be saved only once the get method is called once it will change it dynamically
		 */
		ON_CALL,
		
		/**
		 * The local Java data is never saved
		 */
		NEVER
	}
	
	/**
	 * Defines how the data is shared between the Java and the Native side.
	 * Note: Values which the native side might change will not be saved!
	 */
	public static CacheMode cacheMode = CacheMode.ON_CREATE;
	
}
