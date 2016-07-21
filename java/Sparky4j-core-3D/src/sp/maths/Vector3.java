package sp.maths;

public class Vector3 {
	
	public float x, y, z;
	
	public Vector3() {
		x = 0.0f;
		y = 0.0f;
		z = 0.0f;
	}
	
	public Vector3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3(Vector2 other) {
		x = other.x;
		y = other.y;
		z = 0.0f;
	}
	
	public Vector3(float x, float y) {
		this.x = x;
		this.y = y;
		this.z = 0.0f;
	}
	
	public Vector3(Vector3 other) {
		x = other.x;
		y = other.y;
		z = other.z;
	}
	
	/* */
	
	public static Vector3 up() { return new Vector3(0.0f, 1.0f, 0.0f); };
	public static Vector3 down() { return new Vector3(0.0f, -1.0f, 0.0f); };
	public static Vector3 left() { return new Vector3(1.0f, 0.0f, 0.0f); };
	public static Vector3 right() { return new Vector3(1.0f, 1.0f, 0.0f); };
	public static Vector3 zero() { return new Vector3(0.0f, 0.0f, 0.0f); };
	
	public static Vector3 xAxis() { return new Vector3(1.0f, 0.0f, 0.0f); };
	public static Vector3 yAxis() { return new Vector3(0.0f, 1.0f, 0.0f); };
	public static Vector3 zAxis() { return new Vector3(0.0f, 0.0f, 1.0f); };
	
	/* */
	
	public Vector3 add(Vector3 other) {
		x += other.x;
		y += other.y;
		z += other.z;
		return this;
	}
	
	public Vector3 subtract(Vector3 other) {
		x -= other.x;
		y -= other.y;
		z -= other.z;
		return this;
	}
	
	public Vector3 multiply(Vector3 other) {
		x *= other.x;
		y *= other.y;
		z *= other.z;
		return this;
	}
	
	public Vector3 divide(Vector3 other) {
		x /= other.x;
		y /= other.y;
		z /= other.z;
		return this;
	}
	
	/* */
	
	public Vector3 add(float other) {
		x += x;
		y += y;
		z += z;
		return this;
	}
	
	public Vector3 subtract(float other) {
		x -= x;
		y -= y;
		z -= z;
		return this;
	}
	
	public Vector3 multiply(float other) {
		x *= x;
		y *= y;
		z *= z;
		return this;
	}
	
	public Vector3 divide(float other) {
		x /= x;
		y /= y;
		z /= z;
		return this;
	}
	
	/* */
	
	public static Vector3 add(Vector3 vec1, Vector3 vec2) {
		Vector3 ret = new Vector3(vec1);
		ret.add(vec2);
		return ret;
	}
	
	public static Vector3 subtract(Vector3 vec1, Vector3 vec2) {
		Vector3 ret = new Vector3(vec1);
		ret.subtract(vec2);
		return ret;
	}
	
	public static Vector3 multiply(Vector3 vec1, Vector3 vec2) {
		Vector3 ret = new Vector3(vec1);
		ret.multiply(vec2);
		return ret;
	}
	
	public static Vector3 divide(Vector3 vec1, Vector3 vec2) {
		Vector3 ret = new Vector3(vec1);
		ret.divide(vec2);
		return ret;
	}
	
	/* */
	
	public static Vector3 add(Vector3 vec1, float other) {
		Vector3 ret = new Vector3(vec1);
		ret.add(other);
		return ret;
	}
	
	public static Vector3 subtract(Vector3 vec1, float other) {
		Vector3 ret = new Vector3(vec1);
		ret.subtract(other);
		return ret;
	}
	
	public static Vector3 multiply(Vector3 vec1, float other) {
		Vector3 ret = new Vector3(vec1);
		ret.multiply(other);
		return ret;
	}
	
	public static Vector3 divide(Vector3 vec1, float other) {
		Vector3 ret = new Vector3(vec1);
		ret.divide(other);
		return ret;
	}
	
	public static Vector3 neg(Vector3 vector) {
		return new Vector3(-vector.x, -vector.y, -vector.z);
	}
	
	/* */
	
	public boolean greater(Vector3 other) {
		return x > other.x &&
				y > other.y &&
				z > other.z;
	}
	
	public boolean less(Vector3 other) {
		return x < other.x &&
				y < other.y &&
				z < other.z;
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Vector3) {
			Vector3 vec = (Vector3) obj;
			return vec.x == x &&
					vec.y == y &&
					vec.z == z;
		}
		return false;
	}
	
	/* */
	
	public Vector3 cross(Vector3 other) {
		return new Vector3(y * other.z - z * other.y, z * other.x - x * other.z, x * other.y - y * other.x);
	}
	
	public float dot(Vector3 other) {
		return x * other.x + y * other.y + z * other.z;
	}
	
	public float magnitude() {
		return (float) Math.sqrt(x * x + y * y + z * z);
	}
	
	public Vector3 normalize() {
		float length = magnitude();
		return new Vector3(x / length, y / length, z / length);
	}
	
	public float distance(Vector3 other) {
		float a = x - other.x;
		float b = y - other.y;
		float c = z - other.z;
		return (float) Math.sqrt(a * a + b * b + c * c);
	}
	
	/* */
	
	public String toString() {
		return "vec3: (x: " + x + ", y: " + y + ", z: " + z + ")";
	}

}
