package sp.graphics;

import java.nio.Buffer;

import com.itay.wrapper.Memory;
import com.itay.wrapper.NativeClass;
import com.itay.wrapper.Wrapper.CacheMode;

import sp.maths.Vector2;

public class Font extends NativeClass {
	
	private float size = -1;
	private Vector2 scale = null;
	private String name = null, filename = null;
	private Texture texture = null;
	private int id = -1;

	/** We need reference to the buffer in order to free it (Will be removed in the future (?) ) */
	private static Buffer bufferRef;
	
	public Font(long handler) {
		super(handler);
		
		this.name = "NULL";
		this.filename = "NULL";
		if(cache == CacheMode.ON_CREATE) {
			this.size = native_GetSize(handler);
			float x = native_GetScale_X(handler), y = native_GetScale_Y(handler);
			scale = new Vector2(x, y);
			long texturePointer= native_GetTexture(handler);
			if(texturePointer != 0)
				this.texture = new Texture(native_GetTexture(handler));
			this.id = native_GetID(handler);
		}
	}
	
	public Font(String name, String filename, float size) {
		super(jniCreate(filename, size));
		
		this.name = name;
		this.filename = filename;
		if(cache == CacheMode.ON_CREATE) {
			this.size = size;
			this.scale = new Vector2(1.0f, 1.0f);
			long texturePointer= native_GetTexture(handler);
			if(texturePointer != 0)
				this.texture = new Texture(native_GetTexture(handler));
			this.id = native_GetID(handler);
		}
	}
	
	public Font(String name, byte[] data, float size) {
		super(jniCreate(bufferRef = Memory.malloc(data.length).put(data), data.length, size));
		Memory.free(bufferRef);
		
		this.name = name;
		this.filename = "NULL";
		if(cache == CacheMode.ON_CREATE) {
			this.size = size;
			this.scale = new Vector2(1.0f, 1.0f);
			long texturePointer= native_GetTexture(handler);
			if(texturePointer != 0)
				this.texture = new Texture(native_GetTexture(handler));
			this.id = native_GetID(handler);
		}
	}
	
	private static native long jniCreate(String filename, float size);
	private static native long jniCreate(Buffer buffer, int datasize, float size);
	
	private static native void native_SetScale(long handler, float x, float y);
	public void SetScale(Vector2 scale) {
		if(cache != CacheMode.NEVER) {
			this.scale = scale;
		}
		native_SetScale(handler, scale.x, scale.y);
	}
	
	private static native float native_GetScale_X(long handler);
	private static native float native_GetScale_Y(long handler);
	public Vector2 GetScale() {
		if(cache == CacheMode.ON_CREATE) {
			return scale;
		}else if(cache == CacheMode.ON_CALL) {
			if(scale == null) {
				float x = native_GetScale_X(handler), y = native_GetScale_Y(handler);
				scale = new Vector2(x, y);
			}
			return scale;
		}else {
			float x = native_GetScale_X(handler), y = native_GetScale_Y(handler);
			scale = new Vector2(x, y);
			return scale;
		}
	}
	
	public String GetName() {
		return name;
	}
	
	public String GetFilename() {
		return filename;
	}
	
	private static native float native_GetSize(long handler);
	public float GetSize() {
		if(cache == CacheMode.ON_CREATE) {
			return size;
		}else if(cache == CacheMode.ON_CALL) {
			if(size == -1) size = native_GetSize(handler);
			return size;
		}else {
			return native_GetSize(handler);
		}
	}
	
	private static native long native_GetTexture(long handler);
	public Texture getTexture() {
		if(cache == CacheMode.ON_CREATE) {
			return texture;
		}else if(cache == CacheMode.ON_CALL) {
			if(texture == null) {				
				long texturePointer= native_GetTexture(handler);
				if(texturePointer != 0)
					this.texture = new Texture(native_GetTexture(handler));
			}
			return texture;
		}else {
			long texturePointer= native_GetTexture(handler);
			if(texturePointer != 0) return new Texture(native_GetTexture(handler));
			else return null;
		}
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
	
	private static native void jniDelete(long handler);
	protected void finalize() throws Throwable {
		super.finalize();
		jniDelete(handler);
	}
	
}
