import sp.app.Window;

public class Test {
	
	static {
		String[] libs = {"FreeImage", "OpenAL32", "SPCore", "SPAudio", "SPFont", "Sparky4j-core"};
		
		for(String str : libs) {
			System.loadLibrary(str);
		}
	}
	
	public static void main(String[] args) {
		Window window = new Window("test", 100, 100);
		while(!window.Closed()) {
			window.Update();
		}
	}
	
}
