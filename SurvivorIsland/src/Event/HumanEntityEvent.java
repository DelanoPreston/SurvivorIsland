package Event;

import java.util.EventObject;

import NoGoodAnymore.Human;

public class HumanEntityEvent extends EventObject {
	private static final long serialVersionUID = -2516985610201628221L;
	public Human entity;
	public String entityType;

	// here's the constructor
	public HumanEntityEvent(Object source, Human inHumanEntity, String inEntityType) {
		super(source);
		this.entity = inHumanEntity;
		entityType = inEntityType;
	}
}
