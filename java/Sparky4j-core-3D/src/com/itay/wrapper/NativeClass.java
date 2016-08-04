package com.itay.wrapper;

import com.itay.wrapper.Wrapper.CacheMode;

public class NativeClass {
	
	protected final long handler;
	
	protected final CacheMode cache = Wrapper.cacheMode;
	
	public NativeClass(long handler) {
		this.handler = handler;
	}
	
	public long getNativeHandler() {
		return handler;
	}
	
}
