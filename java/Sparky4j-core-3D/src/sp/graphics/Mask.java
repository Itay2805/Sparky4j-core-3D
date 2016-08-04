package sp.graphics;

import sp.maths.Matrix4;
import sp.maths.Vector3;

public class Mask{
	
	public Texture texture;
	public Matrix4 transform;
	
	public Mask(Texture texture) { this(texture, Matrix4.identity()); }
	public Mask(Texture texture, Matrix4 transform) {
		this.texture = texture;
		this.transform = transform;
		this.transform = Matrix4.scale(new Vector3((float)texture.GetWidth() / (float)texture.GetHeight(), 1.0f, 1.0f));
	}
	
}
