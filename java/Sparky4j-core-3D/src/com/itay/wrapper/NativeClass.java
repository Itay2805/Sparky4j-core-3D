package com.itay.wrapper;

import com.itay.wrapper.WrapperConfig.CacheMode;

public class NativeClass {
	
	protected final long handler;
	protected CacheMode cache = WrapperConfig.cacheMode;
	
	public NativeClass(long handler) {
		this.handler = handler;
	}
	
	public long getNativeHandler() {
		return handler;
	}
	
}
