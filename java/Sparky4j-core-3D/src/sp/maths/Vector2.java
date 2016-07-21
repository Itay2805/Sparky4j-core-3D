package sp.maths;

public class Vector2 {
	
	public float x, y;
	
	public Vector2() {
		x = 0.0f;
		y = 0.0f;
	}
	
	public Vector2(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2(Vector3 other) {
		x = other.x;
		y = other.y;
	}
	
	public Vector2(Vector2 other) {
		x = other.x;
		y = other.y;
	}
	
	/* */
	
	public Vector2 add(Vector2 other) {
		x += other.x;
		y += other.y;
		return this;
	}
	
	public Vector2 subtract(Vector2 other) {
		x -= other.x;
		y -= other.y;
		return this;
	}
	
	public Vector2 multiply(Vector2 other) {
		x *= other.x;
		y *= other.y;
		return this;
	}
	
	public Vector2 divide(Vector2 other) {
		x /= other.x;
		y /= other.y;
		return this;
	}
	
	/* */
	
	public static Vector2 add(Vector2 vec1, Vector2 vec2) {
		Vector2 ret = new Vector2(vec1);
		ret.add(vec2);
		return ret;
	}
	
	public static Vector2 subtract(Vector2 vec1, Vector2 vec2) {
		Vector2 ret = new Vector2(vec1);
		ret.subtract(vec2);
		return ret;
	}
	
	public static Vector2 multiply(Vector2 vec1, Vector2 vec2) {
		Vector2 ret = new Vector2(vec1);
		ret.multiply(vec2);
		return ret;
	}
	
	public static Vector2 divide(Vector2 vec1, Vector2 vec2) {
		Vector2 ret = new Vector2(vec1);
		ret.divide(vec2);
		return ret;
	}
	
	/* */
	
	public static Vector2 add(Vector2 vec, float other) {
		return new Vector2(vec.x + other, vec.y + other);
	}
	
	public static Vector2 multiply(Vector2 vec, float other) {
		return new Vector2(vec.x - other, vec.y - other);
	}
	
	/* */
	
	public boolean greater(Vector2 other) {
		return x > other.x &&
				y > other.y;
	}
	
	public boolean less(Vector2 other) {
		return x < other.x &&
				y < other.y;
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Vector2) {
			Vector2 vec = (Vector2) obj;
			return vec.x == x &&
					vec.y == y;
		}
		return false;
	}
	
	/* */
	
	
	public float magnitude() {
		return (float) Math.sqrt(x * x + y * y);
	}
	
	public Vector2 normalize() {
		float length = magnitude();
		return new Vector2(x / length, y / length);
	}
	
	public float distance(Vector2 other) {
		float a = x - other.x;
		float b = y - other.y;
		return (float) Math.sqrt(a * a + b * b );
	}
	
	public float dot(Vector2 other) {
		return x * other.x + y * other.y;
	}
	
	/* */
	
	public String toString() {
		return "vec2: (x: " + x + ", y: " + y + ")";
	}
	
}
