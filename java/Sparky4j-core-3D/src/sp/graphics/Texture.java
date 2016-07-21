package sp.graphics;

import com.itay.wrapper.NativeClass;
import com.itay.wrapper.WrapperConfig.CacheMode;

public class Texture extends NativeClass {
	
	public enum TextureWrap {
		REPEAT(0x2901),
		CLAMP(0x2900),
		MIRRORED_REPEAT(0x8370),
		CLAMP_TO_EDGE(0x812F),
		CLAMP_TO_BORDER(0x812D);
		
		private final int id;
		private TextureWrap(int id) {
			this.id = id;
		}	
	}
	
	public enum TextureFilter {
		LINEAR(0x2601),
		NEAREST(0x2600);
		
		private final int id;
		private TextureFilter(int id) {
			this.id = id;
		}	
	}
	
	private static native void native_SetWrap(int id);
	public static void SetWrap(TextureWrap wrap) {
		native_SetWrap(wrap.id);
	}
	
	private static native void native_SetFilter(int id);
	public static void SetFilter(TextureFilter wrap) {
		native_SetFilter(wrap.id);
	}
	
	public Texture(long handler) {
		super(handler);
		
		this.name = "NULL";
		
		if(cache == CacheMode.ON_CREATE) {
			this.width = native_GetWidth(handler);
			this.height = native_GetHeight(handler);
			this.id = native_GetID(handler);
		}
	}
	
	private String name = null;
	private int width = -1, height = -1;
	private int id = -1;
	
	public Texture(int width, int height) { this(width, height, 24); }
	public Texture(int width, int height, int bits) {
		super(jniCreate(width, height, bits));
		
		this.name = "NULL";
		
		if(cache == CacheMode.ON_CREATE) {
			this.width = width;
			this.height = height;
			this.id = native_GetID(handler);
		}
	}
	
	public Texture(String name, String filename) {
		super(jniCreate(name, filename));
		
		this.name = name;
		
		if(cache == CacheMode.ON_CREATE) {
			this.width = native_GetWidth(handler);
			this.height = native_GetHeight(handler);
			this.id = native_GetID(handler);
		}
	}
	
	public Texture(int id) {
		super(jniCreate(id));
		
		this.name = "NULL";
		
		if(cache == CacheMode.ON_CREATE) {
			this.width = native_GetWidth(handler);
			this.height = native_GetHeight(handler);
			this.id = id;
		}
	}
	
	private static native long jniCreate(int width, int height, int bits);
	private static native long jniCreate(String name, String filename);
	private static native long jniCreate(int id);
	
	private static native void native_Bind(long handler);
	public void Bind() {
		native_Bind(handler);
	}
	
	private static native void native_Unbind(long handler);
	public void Unbind() {
		native_Unbind(handler);
	}
	
	private static native int native_GetID(long handler);
	public int GetID() {
		if(cache == CacheMode.ON_CREATE) {
			return id;
		}else if(cache == CacheMode.ON_CALL) {
			if(id == -1) id = native_GetID(handler);
			return id;
		}else {
			return native_GetID(handler);
		}
	}
	
	private static native int native_GetWidth(long handler);
	public int GetWidth() {
		if(cache == CacheMode.ON_CREATE) {
			return id;
		}else if(cache == CacheMode.ON_CALL) {
			if(id == -1) id = native_GetID(handler);
			return id;
		}else {
			return native_GetID(handler);
		}
	}
	
	private static native int native_GetHeight(long handler);
	public int GetHeight() {
		if(cache == CacheMode.ON_CREATE) {
			return height;
		}else if(cache == CacheMode.ON_CALL) {
			if(height == -1) height = native_GetHeight(handler);
			return height;
		}else {
			return native_GetHeight(handler);
		}
	}
	
	public String getName() {
		return name;
	}
	
	private static native void jniDelete(long handler);
	protected void finalize() throws Throwable {
		jniDelete(handler);
		super.finalize();
	}
}
