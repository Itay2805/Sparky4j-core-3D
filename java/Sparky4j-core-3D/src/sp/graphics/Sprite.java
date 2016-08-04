package sp.graphics;

import sp.maths.Vector2;
import sp.maths.Vector3;
import sp.maths.Vector4;

public class Sprite extends Renderable2D {
	
	public Sprite(Texture texture) {
		super(new Vector3(0.0f, 0.0f, 0.0f), new Vector2(texture.GetWidth(), texture.GetHeight()), 0xffffffff);
		SetTexture(texture);
	}
	
	public Sprite(float x, float y, Texture texture) {
		super(new Vector3(x, y, 0.0f), new Vector2(texture.GetWidth(), texture.GetHeight()), 0xffffffff);
		SetTexture(texture);
	}
	
	public Sprite(float x, float y, float width, float height, int color) {
		super(new Vector3(x, y, 0.0f), new Vector2(width, height), color);
	}
	
	public Sprite(float x, float y, float width, float height, Vector4 color) {
		super(new Vector3(x, y, 0.0f), new Vector2(width, height), 0xffffffff);
		SetColor(color);
	}
	
	public Sprite(float x, float y, float width, float height, Texture texture) {
		super(new Vector3(x, y, 0.0f), new Vector2(width, height), 0xffffffff);
		SetTexture(texture);
	}
	
	// TODO: SetUVs
	
	public void SetTexture(Texture texture) {
		super.SetTexture(texture);
	}
	
	public void SetPosition(Vector3 position) {
		super.SetPosition(position);
	}
	
	public void SetSize(Vector2 size) {
		super.SetSize(size);
	}
	
}
