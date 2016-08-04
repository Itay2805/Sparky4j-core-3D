package sp.app;

import java.util.ArrayList;
import java.util.List;

import com.itay.wrapper.Wrapper;

import sp.event.Event;
import sp.graphics.layers.Layer;
import sp.utils.Timer;

public abstract class Application {
	
	private static Application instance;
	
	public Window window;
	// TODO: Debug Layer
	
	private boolean running/*, suspended*/;
	private Timer m_Timer;
	private int updatesPerSecond, framesPerSecond;
	
	private String name;
	private int width, height;
	
	private List<Layer> layerStack = new ArrayList<>();
	private List<Layer> overlayStack = new ArrayList<>();
	
	public Application(String name, int width, int height) {
		this.name = name;
		this.width = width;
		this.height = height;
		
		instance = this;
		Wrapper.nativeInit();
	}
	
	public void Init() {
		PlaformInit();
		
		// TODO: Stuff in debug layer
	}
	
	public void PushLayer(Layer layer) {
		layerStack.add(layer);
		layer.Init();
	}
	
	public Layer PopLayer() {
		Layer layer = layerStack.get(layerStack.size() - 1);
		layerStack.remove(layer);
		return layer;
	}
	
	public Layer PopLayer(Layer layer) {
		for(int i = 0; i < layerStack.size(); i++) {
			if(layerStack.get(i).equals(layer)) {
				layerStack.remove(i);
				break;
			}
		}
		return layer;
	}
	
	public void PushOverlay(Layer layer) {
		overlayStack.add(layer);
		layer.Init();
	}
	
	public Layer PopOverlay() {
		Layer layer = overlayStack.get(overlayStack.size() - 1);
		overlayStack.remove(layer);
		return layer;
	}
	
	public Layer PopOverlay(Layer layer) {
		for(int i = 0; i < overlayStack.size(); i++) {
			if(overlayStack.get(i).equals(layer)) {
				overlayStack.remove(i);
				break;
			}
		}
		return layer;
	}
	
	public void Start() {
		Init();
		running = true;
		//suspended = false;
		Run();
	}
	
	public void Suspend() {
		//suspended = true;
	}
	
	public void Resume() {
		//suspended = false;
	}
	
	public void Stop() {
		running = false;
	}
	
	public int GetFPS() {
		return framesPerSecond;
	}
	
	public int GetUPS() {
		return updatesPerSecond;
	}
	
	private void PlaformInit() {
		window = new Window(name, width, height);
		// TODO: set event callback
	}
	
	private void Run() {
		m_Timer = new Timer();
		float timer = 0.0f;
		float updateTimer = 0.0f;
		float updateTick = 1.0f / 60.0f;
		int frames = 0;
		int updates = 0;
		while(running) {
			window.Clear();
			if(m_Timer.Elapsed() - updateTimer > updateTick) {
				OnUpdate();
				updates++;
				updateTimer += updateTick;
			}
			OnRender();
			frames++;
			window.Update();
			if(m_Timer.Elapsed() - timer > 1.0f) {
				timer += 1.0f;
				framesPerSecond = frames;
				updatesPerSecond = updates;
				frames = 0;
				updates = 0;
				OnTick();
			}
			if(window.Closed())
				running = false;
		}
	}
	
	public void OnEvent(Event event) {
		// TODO: DebugLayer OnEvent;
		if(event.IsHandled())
			return;
		
		for(int i = overlayStack.size() - 1; i >= 0; i--) {
			overlayStack.get(i).OnEvent(event);
			if(event.IsHandled())
				return;
		}
		
		for(int i = layerStack.size() - 1; i >= 0; i--) {
			layerStack.get(i).OnEvent(event);
			if(event.IsHandled())
				return;
		}
	}
	
	public void OnTick() {
		for(int i = overlayStack.size() - 1; i >= 0; i--) {
			overlayStack.get(i).OnTick();
		}
		
		for(int i = layerStack.size() - 1; i >= 0; i--) {
			layerStack.get(i).OnTick();
		}
	}
	
	public void OnUpdate() {
		for(int i = overlayStack.size() - 1; i >= 0; i--) {
			overlayStack.get(i).OnUpdate();
		}
		
		for(int i = layerStack.size() - 1; i >= 0; i--) {
			layerStack.get(i).OnUpdate();
		}
	}
	
	public void OnRender() {
		for(int i = layerStack.size() - 1; i >= 0; i--) {
			if(layerStack.get(i).IsVisible())
				layerStack.get(i).OnRender();
		}
		
		for(int i = overlayStack.size() - 1; i >= 0; i--) {
			if(overlayStack.get(i).IsVisible())
				overlayStack.get(i).OnRender();
		}
	}
	
	public static Application GetApplication() {
		return instance;
	}
	
}
