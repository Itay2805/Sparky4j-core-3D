package sp.graphics.shaders;

import sp.embed.Embedded;

public class ShaderFactory {
	
	private static final String DEFAULT_SHADER = new String(Embedded.readEmbeddedFile("/shaders/Default.shader")); 
	private static final String SIMPLE_SHADER = new String(Embedded.readEmbeddedFile("/shaders/Simple.shader"));
	private static final String BASIC_LIGHT_SHADER = new String(Embedded.readEmbeddedFile("/shaders/BasicLight.shader"));
	
	
	public static Shader DefaultShader() {
		return Shader.FromSource("Default Shader", DEFAULT_SHADER);
	}
	
	public static Shader SimpleShader() {
		return Shader.FromSource("Simple Shader", SIMPLE_SHADER);
	}
	
	public static Shader BasicLightShader() {
		return Shader.FromSource("Basic Light Shader", BASIC_LIGHT_SHADER);
	}
	
}
