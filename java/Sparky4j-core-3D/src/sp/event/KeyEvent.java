package sp.event;

public abstract class KeyEvent extends Event {
	
	protected int keycode;
	protected int count;
	
	protected KeyEvent(int keycode, Type type) {
		super(type);
		this.keycode = keycode;
	}
	
	public int getKeycode() {
		return keycode;
	}
	
	public static class KeyPressedEvent extends KeyEvent {
		
		private int repeat;
		private int modifiers;
		
		public KeyPressedEvent(int button, int repeat, int modifiers) {
			super(button, GetStaticType());
			
			this.repeat = repeat;
			this.modifiers = modifiers;
		}
		
		public int getRepeat() {
			return repeat;
		}
		
		public int getModifiers() {
			return modifiers;
		}
		
		public boolean isModifier(int modifier) {
			return (modifier & modifier) != 0;
		}
		
		public static Type GetStaticType() {
			return Type.KEY_PRESSED;
		}
		
	}
	
	public static class KeyReleasedEvent extends KeyEvent {
		
		protected KeyReleasedEvent(int button) {
			super(button, GetStaticType());
		}
		
		public static Type GetStaticType() {
			return Type.KEY_RELEASED;
		}
		
	}
	
}
