package sp.graphics;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.Stack;

import com.itay.wrapper.Memory;
import com.itay.wrapper.NativeClass;
import com.itay.wrapper.WrapperConfig.CacheMode;

import sp.maths.Matrix4;
import sp.maths.Rectangle;
import sp.maths.Vector2;
import sp.utils.Log;

public class Renderer2D extends NativeClass {
	
	public enum RenderTarget{
		
		SCREEN(0),
		BUFFER(1);
		
		public final int id;
		private RenderTarget(int id) {
			this.id = id;
		}
	}
	
	public Renderer2D(long handler) {
		super(handler);
		
		cache = CacheMode.NEVER;
	}
	
	private RenderTarget target = null;
	
	private Stack<Matrix4> transformationStack = null;
	private Matrix4 transformationBack = null;
	
	protected Renderer2D() {
		super(jniCreate());
		
		if(cache != CacheMode.NEVER) {
			transformationStack = new Stack<>();
		}
		
		if(cache == CacheMode.ON_CREATE) {
			target = RenderTarget.SCREEN;
			transformationStack.add(Matrix4.identity());
			transformationBack = transformationStack.get(0);
		}
	}
	
	private static native long jniCreate();

	private static native void native_Push(long handler, Buffer buffer, boolean override);
	public void Push(Matrix4 matrix) { Push(matrix, false); }
	public void Push(Matrix4 matrix, boolean override) {
		if(cache != CacheMode.NEVER) {
			if(override)
				transformationStack.push(matrix);
			else
				transformationStack.push(Matrix4.multiply(transformationStack.peek(), matrix));
			
			transformationBack = transformationStack.peek();
		}
		ByteBuffer buffer = Memory.malloc(matrix.elements.length * Float.BYTES);
		buffer.asFloatBuffer().put(matrix.elements);
		native_Push(handler, buffer, override);
		Memory.free(buffer); // TODO: Maybe free it on the native side (?)
	}
	
	private static native void native_Pop(long handler);
	public void Pop() {
		if(cache != CacheMode.NEVER) {
			if(transformationStack.size() > 1)
				transformationStack.pop();
			
			transformationBack = transformationStack.peek();
		}
		native_Pop(handler);
	}
	
	private static native void native_SetRenderTarget(long handler, int target);
	public void SetRenderTarget(RenderTarget target) {
		if(cache == CacheMode.ON_CREATE) {
			this.target = target;
		}
		native_SetRenderTarget(handler, target.id);
	}
	
	private static native int native_GetRenderTarget(long handler);
	public RenderTarget GetRenderTarget() {
		if(cache == CacheMode.ON_CREATE) {
			return target;
		}else if(cache == CacheMode.ON_CALL) {
			int id = -1;
			if(target == null) id = native_GetRenderTarget(handler);
			switch(id) {
				case 0: return target = RenderTarget.SCREEN;
				case 1: return target = RenderTarget.BUFFER;
				default:
					Log.SP_FATAL("Could not find RenderTarget ID: " + id);
					throw new IllegalArgumentException("Could not find RenderTarget ID: " + id);
			}
		}else {
			int id = native_GetRenderTarget(handler);
			switch(id) {
				case 0: return target = RenderTarget.SCREEN;
				case 1: return target = RenderTarget.BUFFER;
				default:
					Log.SP_FATAL("Could not find RenderTarget ID: " + id);
					throw new IllegalArgumentException("Could not find RenderTarget ID: " + id);
			}
		}
	}
	
	public void Begin() { native_Begin(handler); }
	public void Submit(Renderable2D renderable) {}
	
	public void DrawLine(float x0, float y0, float x1, float y1, float thickness, int color) { native_DrawLine(handler, x0, y0, x1, y1, thickness, color); }
	public void DrawLine(float x0, float y0, float x1, float y1, float thickness) { DrawLine(x0, y0, x1, y1, thickness, 0xffffffff); }
	public void DrawLine(float x0, float y0, float x1, float y1) { DrawLine(x0, y0, x1, y1, 0.2f); }
	
	public void DrawLine(Vector2 start, Vector2 end, float thickness, int color) { native_DrawLine(handler, start.x, start.y, end.x, end.y, thickness, color); }
	public void DrawLine(Vector2 start, Vector2 end, float thickness) { DrawLine(start, end, thickness, 0xffffffff); }
	public void DrawLine(Vector2 start, Vector2 end) { DrawLine(start, end, 0.2f); }
	
	
	public void DrawRect(float x, float y, float width, float height, int color) { native_DrawRect(handler, x, y, width, height, color); }
	public void DrawRect(float x, float y, float width, float height) { DrawRect(x, y, width, height, 0xffffffff); }
	public void DrawRect(Rectangle rectangle, int color) { DrawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height, color);}
	public void DrawRect(Rectangle rectangle) { DrawRect(rectangle, 0xffffffff);}
	
	public void DrawString(String text, Vector2 position, Font font, int color) { native_DrawString(handler, text, position, font.getNativeHandler(), color); }
	public void DrawString(String text, Vector2 position, Font font) { DrawString(text, position, FontManager.Get(), 0xffffffff); }
	public void DrawString(String text, Vector2 position) { DrawString(text, position, FontManager.Get()); }

	public void FillRect(float x, float y, float width, float height, int color) { native_DrawRect(handler, x, y, width, height, color); }
	public void FillRect(float x, float y, float width, float height) { DrawRect(x, y, width, height, 0xffffffff); }
	public void FillRect(Rectangle rectangle, int color) { DrawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height, color);}
	public void FillRect(Rectangle rectangle) { DrawRect(rectangle, 0xffffffff);}

	
	public void End() { native_End(handler); }
	public void Present() { native_Present(handler); }
	
	
	private static native void native_Begin(long handler);
	private static native void native_Submit(long handler, long renderable);
	
	private static native void native_DrawLine(long handler, float x0, float y0, float x1, float y1, float thickness, int color);
	private static native void native_DrawRect(long handler, float x, float y, float width, float height, int color);
	private static native void native_DrawString(long handler, String text, Vector2 position, long font, int color);
	
	private static native void native_FillRect(long handler, float x, float y, float width, float height, int color);
	
	private static native void native_End(long handler);
	private static native void native_Present(long handler);

	private static native void jniDelete(long handler);
	protected void finalize() throws Throwable {
		jniDelete(handler);
		super.finalize();
	}
	
}
