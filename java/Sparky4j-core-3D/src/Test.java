
import sp.app.Application;

public class Test extends Application {
	
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;

	public Test() {
		super("Sandbox", WIDTH, HEIGHT);
	}
	
	public void Init() {
		super.Init();
		
		PushLayer(new TestLayer());
	}
	
	public static void main(String[] args) {
		Test game = new Test();
		game.Start();
	}
	
}
