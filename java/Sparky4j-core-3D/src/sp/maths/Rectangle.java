package sp.maths;

public class Rectangle {
	
	public float x, y, width, height;
	
	public Rectangle() {
		x = 0.0f;
		y = 0.0f;
		width = 0.0f;
		height = 0.0f;
	}
	
	public Rectangle(Vector2 position, Vector2 size) {
		x = position.x;
		y = position.y;
		width = size.x;
		height = size.y;
	}
	
	public Rectangle(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public boolean intersects(Rectangle other) {
		return (size().greater(other.position()) && position().less(other.size())) || (position().greater(other.size()) && size().less(other.position()));
	}
	
	public boolean contains(Vector2 point) {
		return point.greater(position()) && point.less(Vector2.add(position(), size()));
	}
	
	public boolean contains(Vector3 point) {
		return contains(new Vector2(point));
	}
	
	public Vector2 center() {
		return Vector2.multiply((Vector2.add(position(), size())), 0.5f);
	}
	
	public boolean greater(Rectangle other) {
		return size().greater(other.size());
	}
	
	public boolean less(Rectangle other) {
		return size().less(other.size());
	}
	
	public Vector2 position() {
		return new Vector2(x, y);		
	}
	
	public Vector2 size() {
		return new Vector2(width, height);
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Rectangle) {
			Rectangle other = (Rectangle) obj;
			return position().equals(other.position()) &&
					size().equals(other.size());
		}
		return false;
	}
	
	public String toString() {
		return super.toString();
	}
	
	
}
