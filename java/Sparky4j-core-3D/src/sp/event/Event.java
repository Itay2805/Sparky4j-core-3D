package sp.event;

import static sp.Common.BIT;

public abstract class Event {
	
	public static enum Type {
		KEY_PRESSED(BIT(0)),
		KEY_RELEASED(BIT(1)),
		MOUSE_PRESSED(BIT(2)),
		MOUSE_RELEASED(BIT(3)),
		MOUSE_MOVED(BIT(4));
		
		public final int bit;
		private Type(int bit) {
			this.bit = bit;
		}
	}
	
	protected boolean handled;
	protected Type type;
	
	protected Event(Type type) {
		this.type = type;
		this.handled = false;
	}
	
	public Type getType() {
		return type;
	}
	
	public boolean isHandled() {
		return handled;
	}
	
	public String toString() {
		return "Event: ";
	}
	
}
