class WordEntry {
    private String word;
    public AVLTreePosition wordposAVL = new AVLTreePosition();
    private MyLinkedlist<Position> wordpos = new MyLinkedlist<Position>();
    WordEntry (String Word_string) {
        word = Word_string;
    }
    String getWord () {
        return word;
    }
    int getSize () {
        if(wordpos.Size()!=wordposAVL.countNodes()) {
            System.out.println("Size not same AVL is shit");
        }
        return wordposAVL.countNodes();
    }
    void printWordEntry() {
        MyLinkedlist<Position> wordpos = getAllPositionsForThisWord();
        for(int i=0;i<wordpos.Size();i++) {
            wordpos.ElementAt(i).printPosition();
            System.out.print(' ');
        }
        System.out.println("");
    }
    Boolean equals (WordEntry str_Entry) {
        return word.equals(str_Entry.word);
    }
    void addPosition(Position position) {
        wordposAVL.insert(position);
        wordpos.InsertRear(position);
        if(wordpos.Size()!=wordposAVL.countNodes()) {
            System.out.println("Size not same AVL is shit" + wordpos.Size()+" "+wordposAVL.countNodes());
        }
    }
    void addPositions(MyLinkedlist<Position> positions) {
        for(int i=0;i<positions.Size();i++) {
            addPosition(positions.ElementAt(i));
        }
    }
    MyLinkedlist<Position> getAllPositionsForThisWord() {
        return wordpos;
        // return wordposAVL.inorder();  
    }
}