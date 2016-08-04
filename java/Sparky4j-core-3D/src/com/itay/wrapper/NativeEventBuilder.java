package com.itay.wrapper;

import sp.event.KeyEvent.KeyPressedEvent;
import sp.event.KeyEvent.KeyReleasedEvent;

public class NativeEventBuilder {
	
	public static KeyPressedEvent createKeyPressedEvent(int keycode, int repeat, int modifiers) {
		return new KeyPressedEvent(keycode, repeat, modifiers);
	}
	
	public static KeyReleasedEvent createKeyReleaseEvent(int keycode) {
		return new KeyReleasedEvent(keycode);
	}
	
}
