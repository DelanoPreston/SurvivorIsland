package Main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Items.Item;

public class MyEventSource {
	private List<MyEventClassListener> _listeners = new ArrayList<>();

	public synchronized void addEventListener(MyEventClassListener listener) {
		_listeners.add(listener);
	}

	public synchronized void removeEventListener(MyEventClassListener listener) {
		_listeners.remove(listener);
	}

	// call this method whenever you want to notify
	// the event listeners of the particular event
	public synchronized double[] fireEntityEvent(Entity entity) {
//		MyEventClass event = new MyEventClass(this);
//		MyEventClass event = new MyEventClass(location);
		//MyEventClass event = new MyEventClass(entity);
//		Iterator<MyEventClassListener> i = _listeners.iterator();
//		while (i.hasNext()) {
////			((MyEventClassListener) i.next()).handleMyEventClassEvent(event);
//			((MyEventClassListener) i.next()).handleMyEventClassEvent(entity);
//		}
		return _listeners.get(0).handleMyEventClassEvent(entity);
	}
}
