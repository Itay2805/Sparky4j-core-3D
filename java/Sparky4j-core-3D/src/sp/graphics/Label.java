package sp.graphics;

import sp.maths.Vector2;
import sp.maths.Vector3;
import sp.utils.Log;

public class Label extends Renderable2D {
	
	public Font font;
	public String text;
	
	public Label(String text, float x, float y, int color) {
		this.text = text;
		SetPosition(new Vector3(x, y, 0.0f));
		SetColor(color);
		this.font = FontManager.Get("SourceSansPro");
	}
	
	public Label(String text, float x, float y, Font font, int color) {
		this.text = text;
		SetPosition(new Vector3(x, y, 0.0f));
		SetColor(color);
		this.font = font;
	}

	public Label(String text, float x, float y, String font, int color) {
		this.text = text;
		SetPosition(new Vector3(x, y, 0.0f));
		SetColor(color);
		
		this.font = FontManager.Get(font);
		
		ValidateFont(font);
	}

	public Label(String text, float x, float y, String font, int size, int color) {
		this.text = text;
		SetPosition(new Vector3(x, y, 0.0f));
		SetColor(color);
		
		this.font = FontManager.Get(font, size);
		
		ValidateFont(font, size);
	}
	
	public void Submit(Renderer2D renderer) {
		//renderer.DrawString(text, new Vector2(GetPosition()), font, GetColor());
	}
	
	public void ValidateFont(String name) { ValidateFont(name, -1); }
	public void ValidateFont(String name, int size) {
		if(font != null)
			return;
		
		Log.SP_WARN("NULL FONT! Font=" + name + (size > 0 ? ", Size=" + size : ""));
		font = FontManager.Get("SourceSansPro");
	}
	
}
