package sp.event;

import java.util.function.Predicate;

public class EventDispatcher {
	
	private Event event;
	
	public EventDispatcher(Event event) {
		this.event = event;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Event> void dispatch(Class<T> type, Predicate<T> func) {
		if(event.getClass().equals(type)) {			
			event.handled = func.test((T)event);
		}
	}
	
}
