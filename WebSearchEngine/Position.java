class Position {
    private PageEntry page;
    private int wordindex;
    private int trueindex;
    Position (PageEntry p,int word_i,int true_i) {
        page = p;
        wordindex = word_i;
        trueindex = true_i;
    }
    PageEntry getPageEntry() {
        return page;
    }
    int getWordIndex () {
        return wordindex;
    }
    int getTrueIndex () {
        return trueindex;
    } 
    void printPosition () {
        System.out.println("( "+page+ " "+wordindex+")");
    }
}