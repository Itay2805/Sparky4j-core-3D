package sp.entity;

import java.util.ArrayList;
import java.util.List;

import sp.entity.component.Component;

public abstract class Entity {
	
	protected List<Component> components;
	
	public Entity() {
		components = new ArrayList<>();
	}
	
	public void addComponent(Component component) {
		components.add(component);
	}
	
	// TODO: find better solution :/
	@SuppressWarnings("unchecked")
	public <T extends Component> T getComponent(String type) {
		return (T) getComponentInternal(type);
	}
	
	private Component getComponentInternal(String type) {
		for(Component component : components) {
			if(component.getType().name.equals(type))
				return component;
		}
		return null;
	}
	
}
