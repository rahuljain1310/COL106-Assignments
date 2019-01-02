class MyLinkedlist<T> {
    class Node {
        public T data ;
        public Node next = null;
        public Node previous = null; 
    }
    private Node head = null;
    private int size = 0;
    public Node getLinkedList () {  // might/should be private
        return head;
    }
    public int Size () {
        return size;
    }
    public T ElementAt(int i) {
        if(i>=size) {
            System.out.println("Out of Bounds LinkedList");
            return null;
        }  
        Node y = head;
        while(i!=0) {
            y=y.next;
            i--;
        }
        return y.data;
    }
    public Boolean isEmpty() {
        return head == null;
    }
    public Boolean isMember(T o) {
        Node x = head;
        while(x!=null) {
            if(x.data.equals(o)) 
                return true;
            x = x.next;
        }
        return false;
    }
    public void InsertRear(T o) {
        Node x = new Node();
        x.data = o;
        x.next = null;
        x.previous =null;
        if(head==null)
            head=x;
        else {
            Node t = head;
            while(t.next!=null) {
                t=t.next;
            }
            t.next = x;
            x.previous = t;
        }
        size++;
    }
    public void InsertFront (T o) {
        Node x = new Node();
        x.data = o;
        x.next = null;
        x.previous =null;
        if(head==null)
            head=x;
        else {
            x.next = head;
            head.previous = x;
            head = x;
        }
        size++;
    }
    public void Delete (T o) {
        if(isMember(o)) {
            Node x = head;
            while(x!=null) {
                if(x.data.equals(o)) {
                    if(x == head) {
                        if(head.next==null)
                            head = null;
                        else {
                            head = head.next;
                            head.previous = null;
                        }
                        return;
                    }
                    else {
                        Node a = x.previous;
                        a.next = x.next;
                        if(x.next!=null) {
                            x.next.previous = a;
                        }
                        return;
                    }
                }
                x = x.next;
            }
            size--;
        } else {
            System.out.println("Cannt be deleted not in the Set");
        }
    }
    public void printlinkedlist () {
        Node x = head;
        while(x!=null) {
            System.out.println(x.data.toString());
            x = x.next;
        }
    }
}