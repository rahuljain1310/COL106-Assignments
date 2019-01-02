public class RoutingMapTree {
    private Exchange Rootnode = new Exchange(0);
    RoutingMapTree (){
        Rootnode = new Exchange(0);
    }
    RoutingMapTree (Exchange root) {
        Rootnode = root;
    }
    public Boolean containsNode (Exchange a) {
        if(Rootnode.equals(a))
            return true;
        boolean x = false;
        Exchange s = Rootnode;
        for(int i=0;i<s.numChildren();i++) {
            x = x || s.subtree(i).containsNode(a);
        }
        return x;
    }
    public void switchOn (MobilePhone a,Exchange b) {
        a.registerExchange(b);
        while(b!=null) {
            b.registerMobile(a);
            b = b.parent();
        }
    }
    public void switchOff (MobilePhone a) {
        Exchange b = a.location();
        while(b!=null) {
            // System.out.println(b.getExchangeID());
            b.deregisterMobile(a);
            b = b.parent();
        }
        a.switchOff();
    }
    public Exchange findExchangeWithId (int a) {
        return findExchange(new Exchange(a));
    }
    public Exchange findExchange (Exchange a) {
        if(Rootnode.equals(a)) {   /// this equal is valid because equals is defined in the Exchange
            return Rootnode;
        }
        for(int i=0;i<Rootnode.numChildren();i++) {
            Exchange x = Rootnode.subtree(i).findExchange(a);
            if(x!=null) {
                return x;
            }
        }
        return null;
    }
    public Exchange findPhone (MobilePhone m) {
        if(m!=null && m.status()) 
            return m.location();
        else {
            return null;
        }
    }
    public MobilePhone MobilePhoneWithNumber (int x) {
        return Rootnode.MobileConnected.MobilewithID(x);
    }
    public Exchange lowestRouter (Exchange a, Exchange b) {
        ExchangeList A = new ExchangeList();
        Exchange temp = a;
        while(temp!=null) {
            A.Insert(temp);
            temp = temp.parent();
        }
        temp = b;
        Exchange result = null;
        while(temp!=null) {
            if(A.isMember(temp)) {
                result = temp;
                break;
            }
            temp = temp.parent();
        } 
        return result;
    }
    public ExchangeList routeCall (MobilePhone a, MobilePhone b) {
        Exchange A = a.location();
        Exchange B = b.location();
        Exchange lowestRouterExchange = lowestRouter(A, B);
        ExchangeList left = new ExchangeList();
        while(A!=lowestRouterExchange) {
            left.Insert(A);
            A = A.parent();
        }
        ExchangeList right = new ExchangeList();
        while(B!=lowestRouterExchange.parent()) {
            right.Insert(B);
            B = B.parent();
        }
        right = right.Reverse();
        for(int i=0;i<right.Size();i++) {
            left.Insert(right.Node_i(i));
        }
        return left;
    }
    public void movePhone(MobilePhone a, Exchange b) {
        switchOff(a);
        switchOn(a,b);        
    }
    public String performAction (String actionMessage) {
        String input[] = actionMessage.split(" ");
        switch(input[0]) {
            case "addExchange": {
                int a = Integer.parseInt(input[1]);
                int b = Integer.parseInt(input[2]);
                try {
                    Exchange A = findExchangeWithId(a);
                    A.addChild(new Exchange(b));
                } catch (Exception e) {
                    System.out.println("No exchange with Identifier "+a);
                }
                return "";
            }
            case "switchOnMobile": {
                int a = Integer.parseInt(input[1]);
                int b = Integer.parseInt(input[2]);
                Exchange B = findExchangeWithId(b);
                if(B!=null) {
                    MobilePhone A = MobilePhoneWithNumber(a);
                    if(A==null) {
                        A = new MobilePhone(a);
                        switchOn(A, B);
                    } else {
                        System.out.println("Mobile is already switched On");
                    }
                } else {
                    System.out.println("Exchange Not Found");
                }
                return "";
            }
            case "switchOffMobile": {
                int a = Integer.parseInt(input[1]);
                MobilePhone A = MobilePhoneWithNumber(a);
                if(A!=null) {
                    switchOff(A);
                    return "";
                } else
                    return "Error - No mobile phone with identifier "+a+" found in the network"; 
            }
            case "queryNthChild": {
                int a = Integer.parseInt(input[1]);
                int b = Integer.parseInt(input[2]);
                Exchange A = findExchange(new Exchange(a));
                if(A == null) {
                    return "Error - No Exchange with identifier "+a+" found in the network"; 
                } else {
                    Exchange B = A.child(b);
                    if(B!=null) 
                        return actionMessage+": "+ B.getExchangeID();
                    else
                        return "Error - bth Child not present"; 
                }
            }
            case "queryMobilePhoneSet": {
                int a = Integer.parseInt(input[1]);
                Exchange A = findExchange(new Exchange(a));
                MobilePhoneSet x = A.residentSet();
                return actionMessage+": "+x.AllIdentifierString();
            }
            case "findPhone":
            case "queryFindPhone": {
                int a = Integer.parseInt(input[1]);
                actionMessage = "queryFindPhone "+a;
                MobilePhone A = MobilePhoneWithNumber(a);
                if(A==null) return actionMessage+": "+"Error - No mobile phone with identifier "+a+" found in the network";
                Exchange res = findPhone(A);
                return "queryFindPhone "+a+": "+res.getExchangeID();
            }
            case "lowestRouter": {
                int a = Integer.parseInt(input[1]);
                int b = Integer.parseInt(input[2]);
                Exchange A = findExchangeWithId(a);
                Exchange B = findExchangeWithId(b);
                Exchange res = lowestRouter(A, B);
                return "queryLowestRouter "+a+" "+b+": "+res.getExchangeID();
            }
            case "findCallPath": 
            case "queryFindCallPath": {
                int a = Integer.parseInt(input[1]);
                int b = Integer.parseInt(input[2]);
                actionMessage = "queryFindCallPath "+a+" "+b;
                MobilePhone A = MobilePhoneWithNumber(a);
                if(A==null) return actionMessage+": "+"Error - Mobile phone with identifier "+a+" is currently switched off";
                MobilePhone B = MobilePhoneWithNumber(b);
                if(B==null) return actionMessage+": "+"Error - Mobile phone with identifier "+b+" is currently switched off";
                ExchangeList res = routeCall(A,B);
                return actionMessage+": "+res.AllExchageString();
            }
            case "movePhone" : {
                int a = Integer.parseInt(input[1]);
                int b = Integer.parseInt(input[2]);
                MobilePhone A = MobilePhoneWithNumber(a);
                if(A==null) return actionMessage+": "+"No Mobile Phone with number"+a+" found";
                Exchange B = findExchangeWithId(b);
                if(B==null) return actionMessage+": "+"No Exchange with Exchange ID"+b+" found";
                movePhone(A,B);
                return "";
            }
        }
        return null;
    }
}