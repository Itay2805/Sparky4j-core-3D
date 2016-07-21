package sp.event;

import java.util.function.Consumer;

public class EventDispatcher {
	
	private Event event;
	
	public EventDispatcher(Event event) {
		this.event = event;
	}
	
	void dispatch(Consumer<Event> func) {
		func.accept(event);
	}
	
}
