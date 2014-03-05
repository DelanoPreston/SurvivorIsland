package Event;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Entity.Entity;
import Main.Location;
import Maps.Map;

public class CustomEventSource implements Serializable{
	private static final long serialVersionUID = 1515868116459166516L;
	private List<CustomEventClassListener> _listeners = new ArrayList<>();

	public synchronized void addEventListener(CustomEventClassListener listener) {
		_listeners.add(listener);
	}

	public synchronized void removeEventListener(CustomEventClassListener listener) {
		_listeners.remove(listener);
	}

	// call this method whenever you want to notify
	// the event listeners of the particular event
	public synchronized Entity findEntityEvent(Entity entity, String sType) {
		EntityEvent event = new EntityEvent(this, entity, sType);
		Iterator<CustomEventClassListener> i = _listeners.iterator();
		while (i.hasNext()) {
			return ((CustomEventClassListener) i.next()).handleFindEntityEvent(event);
		}
		return null;
	}
	public synchronized void removeEntityEvent(Entity entity, String sType){
		EntityEvent event = new EntityEvent(this, entity, sType);
		Iterator<CustomEventClassListener> i = _listeners.iterator();
		while (i.hasNext()) {
			((CustomEventClassListener) i.next()).handleRemoveEntityEvent(event);
		}
	}
	public synchronized int getEntityCountEvent(String s, String subS){
		StringEvent event = new StringEvent(this, s, subS);
		Iterator<CustomEventClassListener> i = _listeners.iterator();
		while (i.hasNext()) {
			return ((CustomEventClassListener) i.next()).handleGetEntityCountEvent(event);
		}
		return 0;
	}
	public synchronized int[] getAdjacentTileLocation(Entity entity, String sType){
		EntityEvent event = new EntityEvent(this, entity, sType);
		Iterator<CustomEventClassListener> i = _listeners.iterator();
		while (i.hasNext()) {
			return ((CustomEventClassListener) i.next()).handleGetAdjacentTileLocation(event);
		}
		return null;
	}
	public synchronized Map getMap(){
		Iterator<CustomEventClassListener> i = _listeners.iterator();
		while (i.hasNext()) {
			return ((CustomEventClassListener) i.next()).handleGetMap();
		}
		return null;
	}
	public synchronized Location getTileAtLocation(Location inLoc){
		Iterator<CustomEventClassListener> i = _listeners.iterator();
		while (i.hasNext()) {
			return ((CustomEventClassListener) i.next()).handleGetTileAtLocation(inLoc);
		}
		return null;
	}
}
