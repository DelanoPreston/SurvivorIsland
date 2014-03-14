package Ash;

public class Node {
    private Entity entity;
    private Node next, previous;

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public boolean hasNext() {
        return next != null;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public boolean hasPrevious() {
        return previous != null;
    }
}
