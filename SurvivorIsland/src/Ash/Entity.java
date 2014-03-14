package Ash;

import java.util.HashMap;
import java.util.Map;

public class Entity {
    private Entity next, previous;
    private Map<Class<? extends Component>, Component> components;

    public Entity() {
        components = new HashMap<Class<? extends Component>, Component>();
    }

    /**
     * 
     * @param component
     * @return
     */
    public Entity add(Component component) {
        return add(component.getClass(), component);
    }

    /**
     * Faster way to add a component to the Entity
     * 
     * @param klass
     * @param component
     * @return
     */
    public Entity add(Class<? extends Component> klass, Component component) {
        if (has(klass)) {
            remove(klass);
        }
        component.setEntity(this);
        components.put(klass, component);
        return this;
    }

    public Component remove(Class<? extends Component> klass) {
        if (has(klass)) {
            Component component = components.remove(klass);
            component.setEntity(null);
            return component;
        }
        return null;
    }

    public boolean has(Class<? extends Component> klass) {
        return components.containsKey(klass);
    }

    /**
     * Gets and array of all the components
     * 
     * @return
     */
    public Component[] allComponents() {
        return components.values().toArray(new Component[0]);
    }

    public Entity getNext() {
        return next;
    }

    public void setNext(Entity next) {
        this.next = next;
    }

    public boolean hasNext() {
        return next != null;
    }

    public Entity getPrevious() {
        return previous;
    }

    public void setPrevious(Entity previous) {
        this.previous = previous;
    }

    public boolean hasPrevious() {
        return previous != null;
    }
}
