package sp.graphics.postfx;

import com.itay.wrapper.NativeClass;

import sp.graphics.shaders.Shader;

public class PostEffectsPass extends NativeClass {
	
	public PostEffectsPass(long handler) {
		super(handler);
	}
	
	public PostEffectsPass(Shader shader) {
		super(jniCreate(shader.getNativeHandler()));
	}
	
	private static native long jniCreate(long shader);

	private static native void jniDelete(long handler);
	protected void finalize() throws Throwable {
		jniDelete(handler);
	}
	
}
