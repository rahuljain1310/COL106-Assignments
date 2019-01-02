public class Exchange {
    private int exchangeID;
    private Exchange parentExchange = null;
    private ExchangeList childrenList = new ExchangeList();
    public MobilePhoneSet MobileConnected = new MobilePhoneSet();
    Exchange (int number) {
        exchangeID = number;
    }
    public Exchange parent() {
        return parentExchange;
    }
    public int getExchangeID () {
        return exchangeID;
    }
    public Boolean equals(Exchange o) {
        return exchangeID == o.exchangeID;
    }
    public int numChildren() {
        return childrenList.Size();
    }
    public Exchange child (int i) {
        return childrenList.Node_i(i);
    }
    public void addChild (Exchange a) {
        childrenList.Insert(a);
        a.parentExchange = this;
    }
    public Boolean isRoot() {
        return parentExchange == null;
    }
    public void registerMobile (MobilePhone a) {   
        MobileConnected.Insert(a);
    }
    public void deregisterMobile (MobilePhone a) {
        MobileConnected.Delete(a);
    }
    public RoutingMapTree subtree (int i) {
        RoutingMapTree x = new RoutingMapTree(child(i));
        return x;
    }
    public MobilePhoneSet residentSet() {
        return MobileConnected;
    }
}