package Ash;

public class NodeList {
    private Node head;
    private Node tail;
    private int size;
    
    public NodeList(){
        size = 0;
    }

    public void add(Node node) {
        if (isEmpty()) {
            head = node;
            tail = node;
            node.setNext(null);
            node.setPrevious(null);
        } else {
            tail.setNext(node);
            node.setPrevious(tail);
            node.setNext(null);
            tail = node;
        }
        size++;
    }

    public void remove(Node node) {
        if (head == node) {
            head = head.getNext();
        }

        if (tail == node) {
            tail = tail.getPrevious();
        }

        if (node.hasPrevious()) {
            node.getPrevious().setNext(node.getNext());
        }

        if (node.hasNext()) {
            node.getNext().setPrevious(node.getPrevious());
        }
        size--;
    }

    public void clear() {
        while (!isEmpty()) {
            Node node = head;
            head = node.getNext();
            remove(node);
        }
        tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }

    public int getSize() {
        return size;
    }

}
