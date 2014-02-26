package Main;

public interface MyEventClassListener {
	public Entity handleFindEntityEvent(Entity e, String s);
	public void handleRemoveEntityEvent(Entity e, String s);
	public int handleGetEntityCountEvent(String s);
//	public void hangleMyOtherEventClass(EventObject e);
}
