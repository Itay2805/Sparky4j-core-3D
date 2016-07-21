package sp.maths;

public class Vector4 {
	
	public float x, y, z, w;
	
	public Vector4() {
		x = 0.0f;
		y = 0.0f;
		z = 0.0f;
		w = 0.0f;
	}
	
	public Vector4(Vector4 vector) {
		x = vector.x;
		y = vector.y;
		z = vector.z;
		w = vector.w;
	}
	
	public Vector4(float x, float y, float z, float w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	public Vector4 add(Vector4 other) {
		x += other.x;
		y += other.y;
		z += other.z;
		w += other.w;
		return this;
	}
	
	public Vector4 subtract(Vector4 other) {
		x -= other.x;
		y -= other.y;
		z -= other.z;
		w -= other.w;
		return this;
	}
	
	public Vector4 multiply(Vector4 other) {
		x *= other.x;
		y *= other.y;
		z *= other.z;
		w *= other.w;
		return this;
	}
	
	public Vector4 divide(Vector4 other) {
		x /= other.x;
		y /= other.y;
		z /= other.z;
		w /= other.w;
		return this;
	}
	
	public static Vector4 add(Vector4 vec1, Vector4 vec2) {
		Vector4 ret = new Vector4(vec1);
		ret.add(vec2);
		return ret;
	}
	
	public static Vector4 subtract(Vector4 vec1, Vector4 vec2) {
		Vector4 ret = new Vector4(vec1);
		ret.subtract(vec2);
		return ret;
	}
	
	public static Vector4 multiply(Vector4 vec1, Vector4 vec2) {
		Vector4 ret = new Vector4(vec1);
		ret.multiply(vec2);
		return ret;
	}
	
	public static Vector4 divide(Vector4 vec1, Vector4 vec2) {
		Vector4 ret = new Vector4(vec1);
		ret.divide(vec2);
		return ret;
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Vector4) {
			Vector4 vec = (Vector4) obj;
			return vec.x == x &&
					vec.y == y &&
					vec.z == z &&
					vec.w == w;
		}
		return false;
	}
	
	public String toString() {
		return "vec4: (x: " + x + ", y: " + y + ", z: " + z + ", w: " + w + ")";
	}
		
}
