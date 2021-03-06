package Event;

import java.util.EventListener;

import Entity.Entity;
import Maps.Map;

public interface CustomEventClassListener extends EventListener{
	public Entity handleFindEntityEvent(HumanEntityEvent e);
	public void handleRemoveEntityEvent(EntityEvent e);
	public int handleGetEntityCountEvent(StringEvent e);
	public int[] handleGetAdjacentTileLocation(EntityEvent e);
	public Map handleGetMap();
//	public Location handleGetTileAtLocation(Location e);
	public void handleCreateStructure(EntityEvent e);
}
