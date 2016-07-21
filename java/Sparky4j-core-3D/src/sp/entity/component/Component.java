package sp.entity.component;

import sp.entity.Entity;

public abstract class Component {
	
	public class ComponentType {
		public String name;
	}
	
	protected Entity entity;
	
	public Entity getEntity() {
		return entity;
	}
	
	public ComponentType getType() {
		return null;
	}
	
}
