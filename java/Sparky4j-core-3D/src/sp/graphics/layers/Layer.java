package sp.graphics.layers;

import sp.app.Window;
import sp.event.Event;

public class Layer {
	
	protected Window window;
	protected boolean visible;
	
	public Layer() {
		this.window = Window.GetWindowClass();
		this.visible = true;
	}
	
	public boolean IsVisible() {
		return visible;
	}
	
	public void SetVisible(boolean visible) {
		this.visible = visible;
	}
	
	public void Init() {}
	public void OnEvent(Event event) {}
	public void OnTick() {}
	public void OnUpdate() {}
	public void OnRender() {}
	
}
