public class Myset {
    class Node {
        public Object data = new Object();
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
    public Boolean isEmpty() {
        return head == null;
    }
    public Boolean isEqual (Object x,Object y) {
        return x.equals(y);        
    }
    public Boolean isMember(Object o) {
        Node x = head;
        while(x!=null) {
            if(isEqual(o,x.data)) 
                return true;
            x = x.next;
        }
        return false;
    }
    public void Insert(Object o) {
        if(!isMember(o)) {
            // Insertion at rear
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
    }
    public void Delete (Object o) {
        if(isMember(o)) {
            Node x = head;
            while(x!=null) {
                if(isEqual(o, x.data)) {
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
    public Myset Union (Myset b) {
        Myset a = new Myset();
        Node x = head;
        while(x!=null) {
            a.Insert(x.data);
            x = x.next;
        }
        x = b.getLinkedList();
        while(x!=null) {
            a.Insert(x.data);
            x = x.next;
        }
        return a;
    }
    public Myset Intersection (Myset b) {
        Myset a = new Myset();
        Node y = head;
        while(y!=null) {
            if(b.isMember(y.data))
                a.Insert(y.data);
            y = y.next;
        }
        return a;
    }
    public void printMyset () {
        Node x = head;
        while(x!=null) {
            System.out.println(x.data.toString());
            x = x.next;
        }
    }
}