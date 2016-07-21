package sp.graphics;

import java.util.ArrayList;
import java.util.List;

public class TextureManager {
	
	private static List<Texture> textures = new ArrayList<Texture>();
	
	private TextureManager() { }
	
	public static void Add(Texture texture) {
		textures.add(texture);
	}
	
	public static Texture Get(String name) {
		for(Texture texture : textures) {
			if(texture.getName().equals(name))
				return texture;
		}
		
		return null;
	}
	
	public static void Clean() {
		textures.clear();
	}
	
}
