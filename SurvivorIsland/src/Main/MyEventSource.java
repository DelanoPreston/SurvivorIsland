package Main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MyEventSource implements Serializable{
	private static final long serialVersionUID = 1515868116459166516L;
	private List<MyEventClassListener> _listeners = new ArrayList<>();

	public synchronized void addEventListener(MyEventClassListener listener) {
		_listeners.add(listener);
	}

	public synchronized void removeEventListener(MyEventClassListener listener) {
		_listeners.remove(listener);
	}

	// call this method whenever you want to notify
	// the event listeners of the particular event
	public synchronized Entity findEntityEvent(Entity entity, String s) {
//		MyEventClass event = new MyEventClass(this);
//		MyEventClass event = new MyEventClass(location);
		//MyEventClass event = new MyEventClass(entity);
//		Iterator<MyEventClassListener> i = _listeners.iterator();
//		while (i.hasNext()) {
////			((MyEventClassListener) i.next()).handleMyEventClassEvent(event);
//			((MyEventClassListener) i.next()).handleMyEventClassEvent(entity);
//		}
		return _listeners.get(0).handleFindEntityEvent(entity, s);
	}
	public synchronized void removeEntityEvent(Entity entity, String s){
		_listeners.get(0).handleRemoveEntityEvent(entity, s);
	}
	public synchronized int getEntityCountEvent(String s){
		return _listeners.get(0).handleGetEntityCountEvent(s);
	}
}
