package sp.graphics.layers;

import java.util.ArrayList;
import java.util.List;

import sp.graphics.Renderable2D;
import sp.graphics.Renderer2D;
import sp.maths.Matrix4;

public class Group {
	
	private List<Renderable2D> renderables = new ArrayList<>();
	private Matrix4 transformationMatrix;
	
	public Group(Matrix4 transform) {
		this.transformationMatrix = transform;
	}
	
	public void Add(Renderable2D renderable) {
		renderables.add(renderable);
	}
	
	public void Submit(Renderer2D renderer) {
		renderer.Push(transformationMatrix);
		
		for(Renderable2D renderable : renderables) {
			renderable.Submit(renderer);
		}
		
		renderer.Pop();
	}
	
	public Matrix4 getTransformationMatrix() {
		return transformationMatrix;
	}
	
}
