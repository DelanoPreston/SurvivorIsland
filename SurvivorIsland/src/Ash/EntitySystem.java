package Ash;

public class EntitySystem {
    private EntitySystem next, previous;

    public EntitySystem getNext() {
        return next;
    }

    public void setNext(EntitySystem next) {
        this.next = next;
    }

    public EntitySystem getPrevious() {
        return previous;
    }

    public void setPrevious(EntitySystem previous) {
        this.previous = previous;
    }

    public boolean hasNext() {
        return next != null;
    }

    public boolean hasPrevious() {
        return previous != null;
    }
}
