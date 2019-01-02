class AVLNodePosition {    
    AVLNodePosition left, right;
    Position data;
    int height;
    /* Constructor */
    public AVLNodePosition() {
        left = null;
        right = null;
        data = null;
        height = 0;
    }
    /* Constructor */
    public AVLNodePosition(Position n) {
        left = null;
        right = null;
        data = n;
        height = 0;
    }     
}

class AVLTreePosition {
    private AVLNodePosition root;     
    public AVLTreePosition() {
        root = null;
    }
    public boolean isEmpty() {
        return root == null;
    }
    public void insert(Position data) {
        root = insert(data, root);
    }
    private int height(AVLNodePosition t ) {
        return t == null ? -1 : t.height;
    }
    private int max(int lhs, int rhs) {
        return lhs > rhs ? lhs : rhs;
    }
    /* Function to insert data recursively */
    private AVLNodePosition insert(Position x, AVLNodePosition t) {
        if (t == null)
            t = new AVLNodePosition(x);
        else if (x.getTrueIndex() <= t.data.getTrueIndex())
        {
            t.left = insert( x, t.left );
            if( height( t.left ) - height( t.right ) == 2 )
                if( height(t.left.left) > height(t.left.right) )
                    t = rotateWithLeftLeftChild( t );   
                else
                    t = rotateWithLeftRightChild( t );
        }
        else if( x.getTrueIndex() > t.data.getTrueIndex() )
        {
            t.right = insert( x, t.right );
            if( height( t.right ) - height( t.left ) == 2 )
                if(height(t.right.right)>height(t.right.left))
                    t = rotateWithRightRightChild( t );
                else
                    t = roateWithRightLeftChild( t );
        }
        
        t.height = max( height( t.left ), height( t.right ) ) + 1;
        return t;
    }
    /* Rotate binary tree node with left child */     
    private AVLNodePosition rotateWithLeftLeftChild(AVLNodePosition k2) {
        AVLNodePosition k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = max( height( k2.left ), height( k2.right ) ) + 1;
        k1.height = max( height( k1.left ), k1.right.height ) + 1;
        return k1;
    }

    /* Rotate binary tree node with right child */
    private AVLNodePosition rotateWithRightRightChild(AVLNodePosition k1) {
        AVLNodePosition k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = max( height( k1.left ), height( k1.right ) ) + 1;
        k2.height = max( height( k2.right ), k1.height ) + 1;
        return k2;
    }
    /**
     * Double rotate binary tree node: first left child
     * with its right child; then node k3 with new left child */
    private AVLNodePosition rotateWithLeftRightChild(AVLNodePosition k3) {
        AVLNodePosition k2 = k3.left;
        AVLNodePosition k1 = k2.right;
        k3.left = k1.right;
        k2.right = k1.left;
        k1.left = k2;
        k1.right = k3;
        k3.height = max(height(k3.left),height(k3.right))+1;
        k2.height = max(height(k2.left),height(k2.right))+1;
        k1.height = max(k2.height,k3.height)+1;
        return k1;
    }
    /**
     * Double rotate binary tree node: first right child
     * with its left child; then node k1 with new right child */      
    private AVLNodePosition roateWithRightLeftChild(AVLNodePosition k1) {
        AVLNodePosition k2 = k1.right;
        AVLNodePosition k3 = k2.left;
        k1.right = k3.left;
        k2.left = k3.right;
        k3.left =k1;
        k3.right = k2;
        k1.height = max(height(k1.left),height(k1.right))+1;
        k2.height = max(height(k2.left),height(k2.right))+1;
        k3.height = max(k2.height,k1.height)+1;
        return k3;
    }    
    /* Functions to count number of nodes */
    public int countNodes() {
        return countNodes(root);
    }
    private int countNodes(AVLNodePosition r) {
        if (r == null)
            return 0;
        else {
            int l = 1;
            l += countNodes(r.left);
            l += countNodes(r.right);
            return l;
        }
    }
    /* Functions to search for an element */
    public boolean search(int val) {
        return search(root, val);
    }
    public boolean search(Position val) {
        return search(root,val.getTrueIndex());
    }
    private boolean search(AVLNodePosition r, int val) {
        boolean found = false;
        while ((r != null) && !found) {
            int rval = r.data.getTrueIndex();  // finding val // Taking out Wi from node
            if (val < rval)
                r = r.left;
            else if (val > rval)
                r = r.right;
            else {
                found = true;
                break;
            }
            found = search(r, val);
        }
        return found;
    }

    public MyLinkedlist<Position> inorder() {
        MyLinkedlist<Position> x = new MyLinkedlist<Position>();
        inorder(root,x);
        return x;
    }
    private void inorder(AVLNodePosition r,MyLinkedlist<Position> x) {
        if (r != null) {
            inorder(r.left,x);
            x.InsertRear(r.data);
            inorder(r.right,x);
        }
    } 
}
