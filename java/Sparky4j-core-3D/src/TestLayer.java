import com.itay.wrapper.Keys;

import sp.app.Application;
import sp.event.Event;
import sp.graphics.Label;
import sp.graphics.Renderer2D;
import sp.graphics.Renderer2D.RenderTarget;
import sp.graphics.Sprite;
import sp.graphics.Texture;
import sp.graphics.Texture.TextureFilter;
import sp.graphics.Texture.TextureWrap;
import sp.graphics.layers.Layer2D;
import sp.graphics.shaders.Shader;
import sp.graphics.shaders.ShaderFactory;
import sp.maths.Matrix4;
import sp.utils.Log;

public class TestLayer extends Layer2D {
	
	private Label[] debugInfo;
	private Renderer2D renderer;
	
	public TestLayer() {
		super(ShaderFactory.DefaultShader(), Matrix4.orthographic(-16.0f, 16.0f, -9.0f, 9.0f, -1.0f, 1.0f));
		this.renderer = null;
	}
	
	public void OnInit(Renderer2D renderer, Shader shader) {
		this.renderer = renderer;
		
		renderer.SetRenderTarget(RenderTarget.BUFFER);
		// TODO: PostEffects
		
		Texture.SetFilter(TextureFilter.NEAREST);
		Add(new Sprite(0.0f, 0.0f, 8, 8, new Texture("Tex", "res/tb.png")));
		Add(new Sprite(-8.0f, -8.0f, 6, 6, 0xffff00ff));
		
		debugInfo = new Label[10];
		debugInfo[0] = new Label("", -15.5f, 6.8f, 0xffffffff);
		debugInfo[1] = new Label("", -15.5f, 5.8f, 0xffffffff);
		Add(debugInfo[0]);
		Add(debugInfo[1]);
		
		Texture.SetWrap(TextureWrap.CLAMP_TO_BORDER);
		// TODO: mask
	}
	
	@Override
	public void OnTick() {
		Application app = Application.GetApplication();
		Log.SP_INFO(app.GetUPS() + " ups, " + app.GetFPS() + " fps");
	}
	
	public void OnUpdate() {
		// TODO: Make on keyPressedEvent 
		if(renderer == null)
			return;
		
		Renderer2D renderer = this.renderer;
		
		if(window.getInputManager().IsKeyPressed(Keys.SP_KEY_T)) {
			renderer.SetRenderTarget(renderer.GetRenderTarget() == RenderTarget.SCREEN ? RenderTarget.BUFFER : RenderTarget.SCREEN);
		}
		
		if(window.getInputManager().IsKeyPressed(Keys.SP_KEY_P)) {
			// TODO: Post Effects
		}
	}
	
	public void OnEvent(Event event) {
		// TODO: Work on event Dispatcher
	}
	
	public void OnRender(Renderer2D renderer) {		
		debugInfo[0].text = "Target: " + (renderer.GetRenderTarget() == RenderTarget.SCREEN ? "Screen" : "Buffer");
		debugInfo[1].text = "PostFX: " + "Off";
	}
	
}
