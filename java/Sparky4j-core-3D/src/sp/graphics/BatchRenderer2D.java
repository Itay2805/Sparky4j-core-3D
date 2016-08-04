package sp.graphics;

public class BatchRenderer2D extends Renderer2D {

	public BatchRenderer2D(long handler) {
		super(handler);
	}
	
	public BatchRenderer2D(int width, int height) {
		super(jniCreate(width, height));
	}
	
	// TODO: tvec<int>
	
	private static native long jniCreate(int width, int height);
	
	public void Submit(Renderable2D renderable) {
		super.Submit(renderable);
	}
	
	// TODO: Set screen and viewport size
	// TODO: Get screen and viewport size
	
	
}
