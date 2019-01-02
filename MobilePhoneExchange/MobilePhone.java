class MobilePhone {
    private int MobileID;
    private Boolean on = true;
    private Exchange location = null;
    MobilePhone(int number) {
        MobileID = number;
    }
    public Boolean equals (MobilePhone a) {
        return MobileID == a.number();
    }
    public int number() {
        return MobileID;
    }
    public Boolean status() {
        return on;
    }
    public void switchOn() {
        on=true;
    }
    public void switchOff() {
        on=false;
        location = null;
    }
    public void registerExchange (Exchange a) {
        location = a;
    }
    public Exchange location() {
        return location;
    }
}