package sp.graphics.shaders;

import java.nio.ByteBuffer;
import java.util.List;

import com.itay.wrapper.Memory;
import com.itay.wrapper.NativeClass;

import sp.maths.Matrix4;
import sp.maths.Vector2;
import sp.maths.Vector3;
import sp.maths.Vector4;
import sp.utils.FileUtils;

public class Shader extends NativeClass {
	
	public Shader(long handler) {
		super(handler);
	}
	
	public Shader(String name, String source) {
		super(jniCreate(name, source));
	}
	
	private static native long jniCreate(String name, String source);
	
	private static native void jniDelete(long handler);
	protected void finalize() throws Throwable {
		jniDelete(handler);
	}
	
	private static native void native_SetUniform1f(long handler, String name, float value);
	public void SetUniform1f(String name, float value) {
		native_SetUniform1f(handler, name, value);
	}
	
	private static native void native_SetUniform1fv(long handler, String name, ByteBuffer values, int count);
	public void SetUniform1fv(String name, float[] values) {
		ByteBuffer buffer = Memory.malloc(Float.BYTES * values.length);
		buffer.asFloatBuffer().put(values);
		native_SetUniform1fv(handler, name, buffer, values.length);
		Memory.free(buffer);
	}
	
	private static native void native_SetUniform1i(long handler, String name, int value);
	public void SetUniform1i(String name, int value) {
		native_SetUniform1i(handler, name, value);
	}
	
	private static native void native_SetUniform1iv(long handler, String name, ByteBuffer values, int count);
	public void SetUniform1iv(String name, int[] values) {
		ByteBuffer buffer = Memory.malloc(Integer.BYTES * values.length);
		buffer.asIntBuffer().put(values);
		native_SetUniform1iv(handler, name, buffer, values.length);
		Memory.free(buffer);
	}
	
	private static native void native_SetUniform2f(long handler, String name, float x, float y);
	public void SetUniform2f(String name, Vector2 vector) {
		native_SetUniform2f(handler, name, vector.x, vector.y);
	}
	
	private static native void native_SetUniform3f(long handler, String name, float x, float y, float z);
	public void SetUniform3f(String name, Vector3 vector) {
		native_SetUniform3f(handler, name, vector.x, vector.y, vector.z);
	}
	
	private static native void native_SetUniform4f(long handler, String name, float x, float y, float z, float w);
	public void SetUniform4f(String name, Vector4 vector) {
		native_SetUniform4f(handler, name, vector.x, vector.y, vector.z, vector.w);
	}
	
	private static native void native_SetUniformMat4(long handler, String name, ByteBuffer matrix);
	public void SetUniformMat4(String name, Matrix4 matrix) {
		ByteBuffer buffer = Memory.malloc(4 * 4 * Float.BYTES);
		buffer.asFloatBuffer().put(matrix.elements);
		native_SetUniformMat4(handler, name, buffer);
		Memory.free(buffer);
	}
	
	private static native void native_SetUniform(long handler, String name, ByteBuffer data);
	public void SetUniform(String name, byte[] data) {
		ByteBuffer buffer = Memory.malloc(data.length);
		buffer.put(data);
		native_SetUniform(handler, name, buffer);
		Memory.free(buffer);
	}
	
	/** @deprecated TODO */
	public void ResolveAndSetUniform(ShaderUniformDeclaration uniform, byte[] data, int offset) {
		
	}
	
	private static native void native_ResolveAndSetUniform(long handler, int index, ByteBuffer data);
	public void ResolveAndSetUniform(int index, byte[] data) {
		ByteBuffer buffer = Memory.malloc(data.length);
		buffer.put(data);
		native_ResolveAndSetUniform(handler, index, buffer);
		Memory.free(buffer);
	}
	
	private static native void native_ResolveAndSetUniforms(long handler, ByteBuffer data, int count);
	public void ResolveAndSetUniforms(byte[] data) {
		ByteBuffer buffer = Memory.malloc(data.length);
		buffer.put(data);
		native_ResolveAndSetUniforms(handler, buffer, data.length);
		Memory.free(buffer);
	}
	
	private static native void native_Bind(long handler);
	public void Bind() {
		native_Bind(handler);
	}
	
	private static native void native_Unbind(long handler);
	public void Unbind() {
		native_Unbind(handler);
	}
	
	private static native boolean native_HashUniform(long handler, String name);
	public boolean HashUniform(String name) {
		return native_HashUniform(handler, name);
	}
	
	// TODO: Find a way to do this (maybe pass a byte buffer or something)
	/** @deprecated returns null for now */
	public ShaderUniformDeclaration GetUniformDeclaration() {
		return null;
	}
	
	/** @deprecated returns null for now */
	public List<ShaderUniformDeclaration> GetUniformDeclarations() {
		return null;
	}
	
	public static Shader FromFile(String name, String filepath) {
		String shader = FileUtils.readFile(filepath);
		return new Shader(name, shader);
	}
	
	public static Shader FromSource(String name, String source) {
		return new Shader(name, source);
	}
	
}
