package Ash;

import java.util.Iterator;

public class EntityList implements Iterable<Entity> {
    private Entity head;
    private Entity tail;
    private int size;

    public EntityList() {
        size = 0;
    }

    public void add(Entity entity) {
        if (isEmpty()) {
            head = entity;
            tail = entity;
            entity.setNext(null);
            entity.setPrevious(null);
        } else {
            tail.setNext(entity);
            entity.setPrevious(tail);
            entity.setNext(null);
            tail = entity;
        }
        size++;
    }

    public void remove(Entity entity) {
        if (head == entity) {
            head = head.getNext();
        }

        if (tail == entity) {
            tail = tail.getPrevious();
        }

        if (entity.hasPrevious()) {
            entity.getPrevious().setNext(entity.getNext());
        }

        if (entity.hasNext()) {
            entity.getNext().setPrevious(entity.getPrevious());
        }
        size--;
    }

    public void clear() {
        while (!isEmpty()) {
            Entity entity = head;
            head = entity.getNext();
            remove(entity);
        }
        tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public Entity getHead() {
        return head;
    }

    public void setHead(Entity head) {
        this.head = head;
    }

    public Entity getTail() {
        return tail;
    }

    public void setTail(Entity tail) {
        this.tail = tail;
    }

    public int getSize() {
        return size;
    }

    @Override
    public Iterator<Entity> iterator() {
        return new Iterator<Entity>() {
            private Entity current = head;
            
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Entity next() {
                current = current.getNext();
                return current;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

}
