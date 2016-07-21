package sp.graphics;

import java.util.ArrayList;
import java.util.List;

import sp.maths.Vector2;

public class FontManager {
	
	private static List<Font> fonts = new ArrayList<>();
	private static Vector2 scale = new Vector2(1, 1);
	
	private FontManager() { }
	
	public static void SetScale(Vector2 scale) {
		FontManager.scale = scale;
	}
	
	public static void Add(Font font) {
		font.SetScale(scale);
		fonts.add(font);
	}

	public static Font Get() {
		return fonts.get(0);
	}
	
	public static Font Get(String name) {
		for(Font font : fonts) {
			if(font.GetName().equals(name))
				return font;
		}
		return null;
	}
	
	public static Font Get(int size) {
		for(Font font : fonts) {
			if(font.GetSize() == size)
				return font;
		}
		// TODO: Create font from embedded data
		return null;		
	}
	
	public static Font Get(String name, int size) {
		for(Font font : fonts) {
			if(font.GetName().equals(name) && font.GetSize() == size)
				return font;
		}
		return null;
	}
	
	public static void Clean() {
		fonts.clear();
	}
	
}
