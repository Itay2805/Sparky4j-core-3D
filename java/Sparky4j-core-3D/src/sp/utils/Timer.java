package sp.utils;

public class Timer {
	
	private long start;
	
	public Timer() {
		
		Reset();
	}
	
	public void Reset() {
		start = System.currentTimeMillis();
	}
	
	public float Elapsed() {
		return (System.currentTimeMillis() - start) / 1000.0f;
	}
	
}
