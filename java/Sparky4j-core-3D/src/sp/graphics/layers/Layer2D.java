package sp.graphics.layers;

import java.util.ArrayList;
import java.util.List;

import sp.app.Window;
import sp.graphics.BatchRenderer2D;
import sp.graphics.Mask;
import sp.graphics.Renderable2D;
import sp.graphics.Renderer2D;
import sp.graphics.shaders.Shader;
import sp.maths.Matrix4;

public class Layer2D extends Layer {
	
	private Renderer2D renderer;
	
	protected List<Renderable2D> renderables = new ArrayList<>();
	protected List<Renderable2D> submittedRenderables = new ArrayList<>();
	protected Shader shader;
	protected Matrix4 projectionMatrix;
	
	public Layer2D(Shader shader, Matrix4 projectionMatrix) {
		this.renderer = new BatchRenderer2D(Window.GetWindowClass().getWidth(), Window.GetWindowClass().getHeight());
		this.shader = shader;
		this.projectionMatrix = projectionMatrix;
		
		shader.Bind();
		shader.SetUniformMat4("pr_matrix", projectionMatrix);
	
		int[] texIDs = {
							0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
							10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
							20, 21, 22, 23, 24, 25, 26, 27, 28, 29,
							30, 31
						};
		
		shader.SetUniform1iv("textures", texIDs);
		
		shader.Unbind();
	}
	
	public void Init() {
		OnInit(renderer, shader);
	}
	
	public void OnInit(Renderer2D renderer, Shader shader) {}
	
	public void SetMask(Mask mask) {
		renderer.SetMask(mask);
	}
	
	public Renderable2D Add(Renderable2D renderable) {
		renderables.add(renderable);
		return renderable;
	}
	
	public List<Renderable2D> GetRenderables() {
		return renderables;
	}
	
	public Renderable2D Submit(Renderable2D renderable) {
		submittedRenderables.add(renderable);
		return renderable;
	}
	
	public void OnRender(Renderer2D renderer) {
		
	}
	
	public void OnRender() {
		shader.Bind();
		renderer.Begin();
		
		for(Renderable2D renderable : renderables) {
			renderable.Submit(renderer);
		}
		
		for(Renderable2D renderable : submittedRenderables) {
			renderable.Submit(renderer);
		}
		
		renderer.End();
		renderer.Present();
		
		OnRender(renderer);
		
		submittedRenderables.clear();
	}
	
}
