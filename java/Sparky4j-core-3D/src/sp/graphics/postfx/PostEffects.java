package sp.graphics.postfx;

import com.itay.wrapper.NativeClass;

public class PostEffects extends NativeClass {

	public PostEffects(long handler) {
		super(handler);
	}
	
	public PostEffects() {
		super(jniCreate());
	}
	
	private static native void native_Push(long handler, long pass);
	public void Push(PostEffectsPass pass) {
		native_Push(handler, pass.getNativeHandler());
	}
	
	private static native void native_Pop(long handler);
	public void Pop() {
		native_Pop(handler);
	}
	
	private static native long jniCreate();
	
	private static native void jniDelete(long handler);
	protected void finalize() throws Throwable {
		jniDelete(handler);
	}
	
}
