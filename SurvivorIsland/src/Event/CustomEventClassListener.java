package Event;

import java.util.EventListener;

import Entity.Entity;

public interface CustomEventClassListener extends EventListener{
	public Entity handleFindEntityEvent(EntityEvent e);
	public void handleRemoveEntityEvent(EntityEvent e);
	public int handleGetEntityCountEvent(StringEvent e);
	public int[] handleGetAdjacentTileLocation(EntityEvent e);
}
