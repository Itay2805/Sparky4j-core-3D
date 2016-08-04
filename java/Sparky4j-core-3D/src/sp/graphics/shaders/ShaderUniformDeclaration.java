package sp.graphics.shaders;

public class ShaderUniformDeclaration {
	
	public enum Type {
		NONE(1),
		FLOAT32(2),
		INT32(3),
		VEC2(4),
		VEC3(5),
		VEC4(6),
		MAT3(7),
		MAT4(8),
		SAMPLER2D(9);
	
		public final int id;
		private Type(int id) {
			this.id = id;
		}
	}
	
	private Type type;
	private String name;
	private int size;
	@SuppressWarnings("unused")
	private int count;
	private int offset;
	@SuppressWarnings("unused")
	private Shader shader;
	private int location;
	
	public ShaderUniformDeclaration(Type type, String name, Shader shader, int count) {
		this.type = type;
		this.name = name;
		this.shader = shader;
		this.count = count;
		this.size = SizeOfUniformType(type) * count;
	}
	
	public ShaderUniformDeclaration(Type type, String name, Shader shader) {
		this(type, name, shader, 1);
	}
	
	public int GetSize() {
		return size;
	}
	
	public int GetLocation() {
		return location;
	}
	
	public int GetOffset() {
		return offset;
	}
	
	public String GetName() {
		return name;
	}
	
	public Type GetType() {
		return type;
	}
	
	private int SizeOfUniformType(Type type) {
		switch(type) {
		case FLOAT32: 	return 4;
		case INT32: 	return 4;
		case VEC2: 		return 4 * 2;
		case VEC3: 		return 4 * 3;
		case VEC4: 		return 4 * 4;
		case MAT3: 		return 4 * 3 * 3;
		case MAT4: 		return 4 * 4 * 4;
		case SAMPLER2D: return 4;
		default:		return 0;
		}
	}
	
}
