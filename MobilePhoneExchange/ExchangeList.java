class ExchangeList {
    class Node {
        public Exchange data;
        public Node next = null;
        public Node previous = null; 
    }
    private Node head = null;
    private int size = 0;
    public Node getLinkedList () {  // might/should be private
        return head;
    }
    public Boolean isEmpty() {
        return head == null;
    }
    public ExchangeList Reverse () {
        Node x = head;
        if(x == null)
            return new ExchangeList();
        while(x.next!=null) {
            x = x.next;
        }
        ExchangeList reverse = new ExchangeList();
        while(x!=null) {
            reverse.Insert(x.data);
            x = x.previous;
        }
        return reverse;
    }
    public String AllExchageString() {
        Node x = head;
        String res = "";
        while(head!=null) {
            res = res+head.data.getExchangeID()+", ";
            head = head.next;
        }
        return res.substring(0,res.length()-2);
    }
    public int Size () {
        return size;
    }
    public Boolean isEqual (Exchange x,Exchange y) {
        return x.equals(y);
    }
    public Boolean isMember(Exchange o) {
        Node x = head;
        while(x!=null) {
            if(isEqual(o,x.data)) 
                return true;
            x = x.next;
        }
        return false;
    }
    public void Insert(Exchange o) {
        // Insertion at rear
        Node x = new Node();
        x.data = o;
        x.next = null;
        if(head == null) {
            x.previous = null;
            x.next = null;
            head = x;
        } else {
            Node y = head;
            while(y.next!=null) {
                y = y.next;
            }
            x.previous = y;
            y.next= x;
        }
        size++;
    }
    public Exchange Node_i (int i) {
        Node x = head;
        while(i!=0){
            x=x.next;
            i--;
        }
        return x.data;
    }
    public void Delete (Exchange o) {
        if(isMember(o)) {
            Node x = head;
            while(x!=null) {
                if(isEqual(o, x.data)) {
                    if(x.previous == null) {
                        head = x;
                        return;
                    }
                    else {
                        Node a = x.previous;
                        a.next = x.next;
                        return;
                    }
                }
                x = x.next;
            }
            size--;
        } else {
            System.out.println("Not a member In exchange list");
        }
    }
}