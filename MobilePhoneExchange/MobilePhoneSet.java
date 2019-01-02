public class MobilePhoneSet {
    private Myset MobileSet = new Myset();
    public Boolean isEmpty() {
        return MobileSet.isEmpty();
    }
    public void Insert (MobilePhone x) {
        MobileSet.Insert(x);
    }
    public void Delete (MobilePhone x) {
        MobileSet.Delete(x);
    }
    public Boolean isMember (MobilePhone x) {
        return MobileSet.isMember(x);
    }
    public MobilePhone MobilewithID (int a) {
        Myset.Node x = MobileSet.getLinkedList();
        MobilePhone t = new MobilePhone(a);
        while(x!=null) {
            MobilePhone temp = (MobilePhone) x.data;
            if(t.equals(temp)) {
                return temp;
            }
            x = x.next;
        }
        return null;
    }
    public String AllIdentifierString ()  {
        Myset.Node x = MobileSet.getLinkedList();
        int size = MobileSet.Size();
        String Identifiers = new String("");
        int i=0;
        while(x!=null) {
            Identifiers = Identifiers.concat(((MobilePhone)x.data).number()+", ");
            x = x.next;
        }
        return Identifiers.substring(0,Identifiers.length()-2);
    }
    public MobilePhoneSet Union (MobilePhoneSet x) {
        MobilePhoneSet union = new MobilePhoneSet();
        union.MobileSet = this.MobileSet.Union(x.MobileSet);
        return union;
    }
    public MobilePhoneSet Intersection (MobilePhoneSet b) {
        MobilePhoneSet a = new MobilePhoneSet();
        Myset.Node y = MobileSet.getLinkedList();
        while(y!=null) {
            MobilePhone temp = (MobilePhone) y.data;
            if(b.isMember(temp))
                a.Insert(temp);
            y = y.next;
        }
        return a;
    }
}