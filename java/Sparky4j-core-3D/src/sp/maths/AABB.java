package sp.maths;

public class AABB {
	
	public Vector3 min;
	public Vector3 max;
	
	public AABB() {
		min = new Vector3();
		max = new Vector3();
	}

	public AABB(Vector2 min, Vector2 max) {
		this.min = new Vector3(min);
		this.max = new Vector3(max);
	}

	public AABB(Vector3 min, Vector3 max) {
		this.min = min;
		this.max = max;
	}

	public AABB(float x, float y, float width, float height) {
		min = new Vector3(x, y, 0.0f);
		max = new Vector3(width, height, 0.0f);
	}

	public AABB(float x, float y, float z, float width, float height, float depth) {
		min = new Vector3(x, y, z);
		max = new Vector3(width, height, depth);
	}
	
	public boolean intersects(AABB other) {
		return (max.greater(other.min) && min.less(other.max)) || (min.greater(other.max) && max.less(other.min));
	}
	
	public boolean contains(Vector2 point) {
		return new Vector3(point).greater(min) && new Vector3(point).less(max);
	}
	
	public boolean contains(Vector3 point) {
		return point.greater(min) && point.less(max);
	}
	
	public Vector3 center() {
		return Vector3.multiply(Vector3.subtract(min, max), 0.5f);
	}
	
	public boolean greater(AABB other) {
		return min.greater(other.max);
	}
	
	public boolean less(AABB other) {
		return max.less(other.min);
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof AABB) {
			AABB other = (AABB) obj;
			return min.equals(other.min) && max.equals(other.max);
		}
		return false;
	}
	
	public String toString() {
		return super.toString();
	}
	
}
