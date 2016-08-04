package sp.graphics;

import java.util.ArrayList;
import java.util.List;

import com.itay.wrapper.NativeClass;
import com.itay.wrapper.Wrapper.CacheMode;

import sp.maths.Vector2;
import sp.maths.Vector3;
import sp.maths.Vector4;

public class Renderable2D extends NativeClass {

	private Vector3 position = null;
	private Vector2 size = null;
	private int color = 0;
	private boolean colorCached = false;
	private List<Vector2> UVs = null;
	private Texture texture = null;
	private boolean textureCached = false;
	private boolean visible = false;
	private boolean visibleCached = false;
	
	protected Renderable2D() {
		this(true);
	}
	
	/**
	 *  Be carefull when setting doFullCache as false, This means only the texture and the UVs will be cached and this might cause a crash. Make sure to set them manually when setting it to false
	 */
	protected Renderable2D(boolean doFullCache) {
		super(jniCreate());

		if(cache == CacheMode.ON_CREATE) {
			this.UVs = GetDefaultUVs();
			this.texture = null;
			this.textureCached = true;
			
			if(doFullCache) {
				float x = native_GetPosition_X(handler);
				float y = native_GetPosition_Y(handler);
				float z = native_GetPosition_Z(handler);
				this.position = new Vector3(x, y, z);
				
				x = native_GetSize_X(handler);
				y = native_GetSize_Y(handler);
				this.size = new Vector2(x, y);
				
				this.color = native_GetColor(handler);
				colorCached = true;
				
				this.visible = native_IsVisible(handler);
				visibleCached = true;				
			}
			
		}
	}
	
	public Renderable2D(long handler) {
		super(handler);
		
		if(cache == CacheMode.ON_CREATE) {
			this.UVs = GetDefaultUVs();
			this.texture = null;
			this.textureCached = true;
			
			float x = native_GetPosition_X(handler);
			float y = native_GetPosition_Y(handler);
			float z = native_GetPosition_Z(handler);
			this.position = new Vector3(x, y, z);
			
			x = native_GetSize_X(handler);
			y = native_GetSize_Y(handler);
			this.size = new Vector2(x, y);
			
			this.color = native_GetColor(handler);
			colorCached = true;
			
			this.visible = native_IsVisible(handler);
			visibleCached = true;
		}
	}
	
	public Renderable2D(Vector3 position, Vector2 size, int color) {
		super(jniCreate(position.x, position.x, position.z, size.x, size.y, color));
		
		if(cache == CacheMode.ON_CREATE) {
			this.position = position;
			this.size = size;
			this.color = color;
			this.colorCached = true;
			this.texture = null;
			this.textureCached = true;
			this.visible = true;
			this.visibleCached = true;
			this.UVs = GetDefaultUVs();
		}
	}
	
	private static native long jniCreate();
	private static native long jniCreate(float xPos, float yPos, float zPos,
										 float xSize, float ySize,
										 int color);
	
	public void Submit(Renderer2D renderer) {
		renderer.Submit(this);
	}

	// TODO: Find better way to pass vectors
	private static native float native_GetPosition_X(long handler);
	private static native float native_GetPosition_Y(long handler);
	private static native float native_GetPosition_Z(long handler);
	public Vector3 GetPosition() {
		if(cache == CacheMode.ON_CREATE) {
			return position;
		}else if(cache == CacheMode.ON_CALL) {
			if(position == null) {
				float x = native_GetPosition_X(handler);
				float y = native_GetPosition_Y(handler);
				float z = native_GetPosition_Z(handler);
				this.position = new Vector3(x, y, z);
			}
			return position;
		}else {
			float x = native_GetPosition_X(handler);
			float y = native_GetPosition_Y(handler);
			float z = native_GetPosition_Z(handler);
			return new Vector3(x, y, z);
		}
	}
	
	private static native void native_SetPosition(long handler, float x, float y, float z);
	protected void SetPosition(Vector3 position) {
		native_SetPosition(handler, position.x, position.y, position.z);
		
		if(cache != CacheMode.NEVER) {
			this.position = position;
		}
	}
	
	private static native float native_GetSize_X(long handler);
	private static native float native_GetSize_Y(long handler);
	public Vector2 GetSize() {
		if(cache == CacheMode.ON_CREATE) {
			return size;
		}else if(cache == CacheMode.ON_CALL) {
			if(size == null) {
				float x = native_GetSize_X(handler);
				float y = native_GetSize_Y(handler);
				this.size = new Vector2(x, y);
			}
			return size;
		}else {
			float x = native_GetSize_X(handler);
			float y = native_GetSize_Y(handler);
			return new Vector2(x, y);
		}
	}
	
	private static native void native_SetSize(long handler, float x, float y);
	protected void SetSize(Vector2 size) {
		native_SetSize(handler, position.x, position.y);
		
		if(cache != CacheMode.NEVER) {
			this.size = size;
		}
	}
	
	private static native void native_SetColor(long handler, int color);
	public void SetColor(int color) {
		native_SetColor(handler, color);
		
		if(cache != CacheMode.NEVER) {
			this.color = color;
			this.colorCached = true;
		}
	}
	
	public void SetColor(Vector4 color) {
		int r = (int)(color.x * 255.0f);
		int g = (int)(color.y * 255.0f);
		int b = (int)(color.z * 255.0f);
		int a = (int)(color.w * 255.0f);
		
		SetColor(a << 24 | b << 16 | g << 8 | r);
	}
	
	private static native int native_GetColor(long handler);
	public int GetColor() {
		if(cache == CacheMode.ON_CREATE) {
			return color;
		}else if(cache == CacheMode.ON_CALL) {
			if(!colorCached) {
				this.color = native_GetColor(handler);
				colorCached = true;
			}
			return color;
		}else {
			return native_GetColor(handler);
		}
	}
	
	/** @deprecated Not really working right now, when on NEVER cache mode it will return null, when on ON_CREATE it will give the defaults (unless changed), on ON_CALL it will give null unless you setted the UVs */
	public List<Vector2> getUVs() {
		if(cache == CacheMode.ON_CREATE) {
			return UVs;
		}else if(cache == CacheMode.ON_CALL) {
			if(UVs == null) {
				// TODO: Get the native UVs;
			}
			return UVs;
		}else {
			return null;
		}
	}
	
	public int GetTID() {
		Texture texture = GetTexture();
		return texture == null ? 0 : texture.GetID();
	}
	
	private static native long native_GetTexture(long handler);
	public Texture GetTexture() {
		if(cache == CacheMode.ON_CREATE) {
			return texture;
		}else if(cache == CacheMode.ON_CALL) {
			if(!textureCached) {
				long tex = native_GetTexture(handler);
				if(tex != 0)
					this.texture = new Texture(tex);
				textureCached = true;
			}
			return texture;
		}else {
			long tex = native_GetTexture(handler);
			if(tex != 0) return new Texture(tex);
			else return null;
		}
	}
	
	private static native void native_SetTexture(long handler, long texture);
	protected void SetTexture(Texture texture) {
		native_SetTexture(handler, texture == null ? 0 : texture.getNativeHandler());
		
		if(cache != CacheMode.NEVER) {
			this.texture = texture;
			this.textureCached = true;
		}
	}
	
	private static native boolean native_IsVisible(long handler);
	public boolean IsVisible() {
		if(cache == CacheMode.ON_CREATE) {			
			return visible;
		}else if(cache == CacheMode.ON_CALL) {
			if(!visibleCached) {
				this.visible = native_IsVisible(handler);
				visibleCached = true;
			}
			return visible;
		}else {
			return native_IsVisible(handler);
		}
	}
	
	private static native void native_SetVisible(long handler, boolean visible);
	public void SetVisible(boolean visible) {
		native_SetVisible(handler, visible);
		if(cache != CacheMode.NEVER) {
			this.visible = visible;
			this.visibleCached = true;
		}
	}
	
	public static List<Vector2> GetDefaultUVs() {
		List<Vector2> results = new ArrayList<>();
		results.add(new Vector2(0, 0));
		results.add(new Vector2(0, 1));
		results.add(new Vector2(1, 1));
		results.add(new Vector2(1, 0));
		return results;
	}
	
	private static native void jniDelete(long handler);
	protected void finalize() throws Throwable {
		jniDelete(handler);
	}
	
}
