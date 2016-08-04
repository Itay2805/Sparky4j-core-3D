import com.itay.wrapper.Keys;

import sp.app.Application;
import sp.event.Event;
import sp.event.EventDispatcher;
import sp.event.KeyEvent.KeyPressedEvent;
import sp.graphics.Label;
import sp.graphics.Renderer2D;
import sp.graphics.Renderer2D.RenderTarget;
import sp.graphics.Sprite;
import sp.graphics.Texture;
import sp.graphics.Texture.TextureFilter;
import sp.graphics.layers.Layer2D;
import sp.graphics.postfx.PostEffectsPass;
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
		
		//renderer.SetRenderTarget(RenderTarget.BUFFER);
		renderer.AddPostEffectsPass(new PostEffectsPass(Shader.FromFile("Horizontal Blur", "shaders/postfx.shader")));
		renderer.SetPostEffects(false);
		
		Texture.SetFilter(TextureFilter.NEAREST);
		Add(new Sprite(0.0f, 0.0f, 8, 8, new Texture("Tex", "res/tb.png")));
		Add(new Sprite(-8.0f, -8.0f, 6, 6, 0xffff00ff));
		
		debugInfo = new Label[10];
		debugInfo[0] = new Label("", -15.5f, 6.8f, 0xffffffff);
		debugInfo[1] = new Label("", -15.5f, 5.8f, 0xffffffff);
		Add(debugInfo[0]);
		Add(debugInfo[1]);
				
		// TODO: Fix, There is a problem with this, makes everything black...
		/*Texture.SetWrap(TextureWrap.CLAMP_TO_BORDER);
		Mask mask = new Mask(new Texture("Mask", "res/mask.png"));
		mask.transform = Matrix4.multiply(Matrix4.translate(new Vector3(-16.0f, -9.0f, 0.0f)), Matrix4.scale(new Vector3(32, 18, 1)));
		SetMask(mask);*/
	}
	
	@Override
	public void OnTick() {
		Application app = Application.GetApplication();
		Log.SP_INFO(app.GetUPS() + " ups, " + app.GetFPS() + " fps");
	}
	
	public void OnUpdate() {
	}
	
	public boolean OnKeyPressedEvent(KeyPressedEvent event) {
		if(renderer == null)
			return false;
		
		Renderer2D renderer = this.renderer;
		
		if(event.getRepeat() != 0)
			return false;
		
		if(event.getKeycode() == Keys.SP_KEY_T) {
			renderer.SetRenderTarget(renderer.GetRenderTarget() == RenderTarget.SCREEN ? RenderTarget.BUFFER : RenderTarget.SCREEN);
			return true;
		}
		
		if(event.getKeycode() == Keys.SP_KEY_P) {
			renderer.SetPostEffects(!renderer.GetPostEffects());
			return true;
		}
		
		return true;
	}
	
	public void OnEvent(Event event) {
		EventDispatcher dispatcher = new EventDispatcher(event);
		dispatcher.dispatch(KeyPressedEvent.class, this::OnKeyPressedEvent);
	}
	
	public void OnRender(Renderer2D renderer) {		
		debugInfo[0].text = "Target: " + (renderer.GetRenderTarget() == RenderTarget.SCREEN ? "Screen" : "Buffer");
		debugInfo[1].text = "PostFX: " + (renderer.GetPostEffects() ? "On" : "Off");
	}
	
}
