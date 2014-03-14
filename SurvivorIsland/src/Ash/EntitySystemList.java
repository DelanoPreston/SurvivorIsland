package Ash;

public class EntitySystemList {
    private EntitySystem head;
    private EntitySystem tail;

    public void add(EntitySystem system) {
        if (isEmpty()) {
            head = system;
            tail = system;
            system.setNext(null);
            system.setPrevious(null);
        } else {
            tail.setNext(system);
            system.setPrevious(tail);
            system.setNext(null);
            tail = system;
        }
    }

    public void remove(EntitySystem system) {
        if (head == system) {
            head = head.getNext();
        }

        if (tail == system) {
            tail = tail.getPrevious();
        }

        if (system.hasPrevious()) {
            system.getPrevious().setNext(system.getNext());
        }

        if (system.hasNext()) {
            system.getNext().setPrevious(system.getPrevious());
        }
    }

    public void clear() {
        while (!isEmpty()) {
            EntitySystem system = head;
            head = system.getNext();
            remove(system);
        }
        tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public EntitySystem getHead() {
        return head;
    }

    public void setHead(EntitySystem head) {
        this.head = head;
    }

    public EntitySystem getTail() {
        return tail;
    }

    public void setTail(EntitySystem tail) {
        this.tail = tail;
    }
}
