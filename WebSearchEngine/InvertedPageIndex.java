class InvertedPageIndex {
    Myset<PageEntry> PageSet = new Myset<PageEntry>();
    MyHashTable WordHash = new MyHashTable();
    void addPage (PageEntry p) {
        PageSet.Insert(p);
        p.setInvertedPageIndex(this);
        MyLinkedlist<WordEntry> x = p.getPageIndex().getWordEntries();
        for(int i=0;i<x.Size();i++) {
            WordHash.addPositionsForWord(x.ElementAt(i));
        }
    }
    Myset<PageEntry> getPagesWhichContainWord (String str) {
        Myset<PageEntry> res = new Myset<PageEntry>();
        MyLinkedlist<Position> x = WordHash.getWordEntry(str).getAllPositionsForThisWord();
        for(int i=0;i<x.Size();i++) {
            PageEntry y = x.ElementAt(i).getPageEntry();
            res.Insert(y);
        }
        return res;
    }
    MyLinkedlist<Integer> getPostionsinWebpage (String word,String Webpage) {
        MyLinkedlist<Integer> res = new MyLinkedlist<Integer>();
        WordEntry x = WordHash.getWordEntry(word);
        MyLinkedlist<Position> y =x.getAllPositionsForThisWord();
        for(int i=0;i<y.Size();i++) {
            Position p = y.ElementAt(i);
            if(p.getPageEntry().getWebpageName().equals(Webpage)) {
                res.InsertRear(p.getWordIndex());
            }
        }
        return res;
    }

    Myset<PageEntry> getPagesWhichContainPhrase(String str[]) {
        Myset<PageEntry> res = new Myset<PageEntry>();
        for(int i=0;i<PageSet.Size();i++) {
            PageEntry y = PageSet.ElementAt(i);
            PageIndex temp = y.getPageIndex();
            if(temp.containsPhrase(str)) {
                res.Insert(y);
            }
        }
        return res;
    }

    Myset<PageEntry> getPagesWhichContainAllWords (String str[]) {
        Myset<PageEntry> res = new Myset<PageEntry>();
        for(int i=0;i<PageSet.Size();i++) {
            PageEntry x = PageSet.ElementAt(i);
            if(x.ContainAllWords(str))
                res.Insert(x);
        }
        return res;
    } 



    Myset<PageEntry> getPagesWhichContainAnyWord (String str[]) {
        Myset<PageEntry> res = new Myset<PageEntry>();
        for(int i=0;i<PageSet.Size();i++) {
            PageEntry x = PageSet.ElementAt(i);
            if(x.ContainAnyWord(str))
                res.Insert(x);
        }
        return res;
    }
}