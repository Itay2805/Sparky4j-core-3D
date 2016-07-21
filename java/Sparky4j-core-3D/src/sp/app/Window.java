package sp.app;

import com.itay.wrapper.NativeClass;
import com.itay.wrapper.WrapperConfig.CacheMode;

import sp.app.Input.InputManager;
import sp.embed.Embedded;
import sp.graphics.Font;
import sp.graphics.FontManager;
import sp.graphics.TextureManager;
import sp.maths.Vector2;

public class Window extends NativeClass {
	
	private int width = -1, height = -1;
	private InputManager inputManager = null;
	
	// TODO: Window event callback
	
	public Window(long handler) {
		super(handler);
		
		if(cache == CacheMode.ON_CREATE) {
			this.width = native_GetWidth(handler);
			this.height = native_GetHeight(handler);
			inputManager = new InputManager(native_GetInputManager(handler));
		}
	}
	
	public Window(String name, int width, int height) {
		this(jniCreate(name, width, height));
		
		if(cache == CacheMode.ON_CREATE) {
			this.width = width;
			this.height = height;
			inputManager = new InputManager(native_GetInputManager(handler));
		}
		
		FontManager.SetScale(new Vector2(width / 32.0f, height / 18.0f));
		//FontManager.Add(new Font("SourceSansPro", Embedded.DEFAULT_FONT, 32));
		
	}
	
	private static native long jniCreate(String name, int width, int height);
	
	private static native void native_Clear(long handler);
	public void Clear() {
		native_Clear(getNativeHandler());
	}
	
	private static native void native_Update(long handler);
	public void Update() {
		native_Update(getNativeHandler());
	}
	
	private static native boolean native_Closed(long handler);
	public boolean Closed() {
		return native_Closed(getNativeHandler());
	}
	
	private static native int native_GetWidth(long handler);
	public int getWidth() {
		if(cache == CacheMode.ON_CREATE) {
			return width;			
		}else if(cache == CacheMode.ON_CALL) {
			if(width == -1) width = native_GetWidth(getNativeHandler()); 
			return width;
		}else {
			return native_GetWidth(getNativeHandler());
		}
	}
	
	private static native int native_GetHeight(long handler);
	public int getHeight() {
		if(cache == CacheMode.ON_CREATE) {
			return height;			
		}else if(cache == CacheMode.ON_CALL) {
			if(height == -1) height = native_GetHeight(getNativeHandler()); 
			return height;
		}else {
			return native_GetHeight(getNativeHandler());
		}
	}
	
	private static native long native_GetInputManager(long handler);
	public InputManager getInputManager() {
		if(cache == CacheMode.ON_CREATE) {
			return inputManager;
		}else if(cache == CacheMode.ON_CALL) {
			if(inputManager == null) inputManager = new InputManager(native_GetInputManager(handler));
			return inputManager;
		}else {
			return new InputManager(native_GetInputManager(handler));
		}
	}
	
	private static native void jniDelete(long handler);
	protected void finalize() throws Throwable {
		FontManager.Clean();
		TextureManager.Clean();
		jniDelete(handler);
	}
	
	
	
}
