package Main;

public interface MyEventClassListener {
	public Entity handleFindEntityEvent(Entity e);
	public void handleRemoveEntityEvent(Entity e);
	public int handleGetEntityCountEvent();
//	public void hangleMyOtherEventClass(EventObject e);
}
