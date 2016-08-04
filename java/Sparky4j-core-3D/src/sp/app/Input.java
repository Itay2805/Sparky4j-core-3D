package sp.app;

import com.itay.wrapper.NativeClass;

import sp.maths.Vector2;

public class Input {
	
	private static InputManager inputManager;
	
	public static boolean IsKeyPressed(int keycode) {
		return inputManager.IsKeyPressed(keycode);
	}
	
	public static boolean IsMouseButtonPressed(int button) {
		return inputManager.IsMousePressed(button);
	}
	
	public static boolean IsMouseButtonClicked(int button) {
		return inputManager.IsMouseButtonClicked(button);
	}
	
	public static Vector2 GetMousePosition() {
		return inputManager.GetMousePosition();
	}
	
	public static class InputManager extends NativeClass {
		
		public InputManager(long handler) {
			super(handler);
		}
		
		/** @deprecated Will not detect any input */
		public InputManager() {
			super(jniCreate());
			
			Input.inputManager = this;
		}
		
		private static native long jniCreate();
		
		private static native void native_Update(long handler);
		public void Update() {
			native_Update(handler);
		}
		
		private static native void native_PlatformUpdate(long handler);
		public void PlatformUpdate() {
			native_PlatformUpdate(handler);
		}
		
		private static native boolean native_IsKeyPressed(long handler, int keycode);
		public boolean IsKeyPressed(int keycode) {
			return native_IsKeyPressed(handler, keycode);
		}
		
		private static native boolean native_IsMousePressed(long handler, int button);
		public boolean IsMousePressed(int button) {
			return native_IsMousePressed(handler, button);
		}
		
		private static native boolean native_IsMouseButtonClicked(long handler, int button);
		public boolean IsMouseButtonClicked(int button) {
			return native_IsMouseButtonClicked(handler, button);
		}
		
		private static native float native_GetMousePosition_X(long handler);
		private static native float native_GetMousePosition_Y(long handler);
		public Vector2 GetMousePosition() {
			float x = native_GetMousePosition_X(handler);
			float y = native_GetMousePosition_Y(handler);
			return new Vector2(x, y);
		}
		
		private static native void native_SetMousePosition(long handler, float x, float y);
		public void SetMousePosition(Vector2 position) {
			native_SetMousePosition(handler, position.x, position.y);
		}
		
		private static native boolean native_IsMouseGrabbed(long handler);
		public boolean IsMouseGrabbed() {
			return native_IsMouseGrabbed(handler);
		}
		
		private static native void native_SetMouseGrabbed(long handler, boolean grabbed);
		public void SetMouseGrabbed(boolean grabbed) {
			native_SetMouseGrabbed(handler, grabbed);
		}
		
		private static native void native_SetMouseCursor(long handler, int cursor);
		public void SetMouseCursor(int cursor) {
			native_SetMouseCursor(handler, cursor);
		}
		
		private static native void native_ClearKeys(long handler);
		public void ClearKeys() {
			native_ClearKeys(handler);
		}
		
		private static native void native_ClearMouseButtons(long handler);
		public void ClearMouseButtons() {
			native_ClearMouseButtons(handler);
		}
		
		private static native void jniDelete(long handler);
		protected void finalize() throws Throwable {
			jniDelete(handler);
			super.finalize();
		}
		
	}
	
}
