package Event;

import java.util.EventListener;

import Maps.Map;
import NoGoodAnymore.Entityz;

public interface CustomEventClassListener extends EventListener{
	public Entityz handleFindEntityEvent(HumanEntityEvent e);
	public void handleRemoveEntityEvent(EntityEvent e);
	public int handleGetEntityCountEvent(StringEvent e);
	public int[] handleGetAdjacentTileLocation(EntityEvent e);
	public Map handleGetMap();
//	public Location handleGetTileAtLocation(Location e);
	public void handleCreateStructure(EntityEvent e);
}
