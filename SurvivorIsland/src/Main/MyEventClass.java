package Main;

public class MyEventClass extends java.util.EventObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2516985610201628221L;
	Entity entity;
	// here's the constructor
	public MyEventClass(Object source) {
		super(source);
//		entity = source;
	}
}
