class PageIndex {
    private MyLinkedlist<WordEntry> WordEntryList = new MyLinkedlist<WordEntry>();
    public int totalWords = 0; 
    public void addPositionForWord(String str, Position p) {
        totalWords++;
        WordEntry x = new WordEntry(str);
        for(int i=0;i<WordEntryList.Size();i++) {
            WordEntry temp = WordEntryList.ElementAt(i);
            if(temp.equals(x)) {
                temp.addPosition(p);
                return;
            }
        }
        x.addPosition(p);
        WordEntryList.InsertFront(x);
    }
    MyLinkedlist<WordEntry> getWordEntries() {
        return WordEntryList;
    }

    float getTermFrequency (String word) {
        for(int i=0;i<WordEntryList.Size();i++) {
            WordEntry temp = WordEntryList.ElementAt(i);
            if(temp.getWord().equals(word)) {
                return temp.getSize()/(float)totalWords;
            }
        }
        return 0;
    }
    void printPageIndex () {
        for(int i=0;i<WordEntryList.Size();i++) {
            WordEntry temp = WordEntryList.ElementAt(i);
            temp.printWordEntry();
        }
    }
    int phraseFrequency (String str[]) {
        WordEntry y = getWordEntry(str[0]);
        if(y==null) return 0;
        MyLinkedlist<Position> x = y.getAllPositionsForThisWord();
        int res = 0;
        for(int i=0;i<x.Size();i++) {
            int phrase_start_index = x.ElementAt(i).getTrueIndex();
            if(PhraseAtPos(str,phrase_start_index))
                res++;
        }
        return res;
    }

    boolean containsPhrase (String str[]) {
        WordEntry y = getWordEntry(str[0]);
        // System.out.println(str[0]);
        if(y==null) return false;
        MyLinkedlist<Position> x = y.getAllPositionsForThisWord();
        for(int i=0;i<x.Size();i++) {
            int phrase_start_index = x.ElementAt(i).getTrueIndex();
            if(PhraseAtPos(str,phrase_start_index))
                return true;
        }
        return false;
    }
    boolean PhraseAtPos (String str[],int i) {
        for(int j=1;j<str.length;j++) {
            // if(getWordEntry(str[j])==null || !getWordEntry(str[j]).wordposAVL.search(i+j))
            //     return false;
            if(getWordEntry(str[j])==null)
                return false;
            WordEntry x = getWordEntry(str[j]);
            MyLinkedlist<Position> y = x.getAllPositionsForThisWord();
            int flag=0;
            for(int k=0;k<y.Size();k++) {
                Position m = y.ElementAt(k);
                if(m.getTrueIndex()==i+j) {
                    flag=1;
                    break;
                }
            }
            if(flag==0) return false;
        }
        return true;
    }

    WordEntry getWordEntry (String x) {
        WordEntry y = new WordEntry(x);
        for(int i=0;i<WordEntryList.Size();i++) {
            WordEntry temp = WordEntryList.ElementAt(i);
            if(temp.equals(y)) {
                return temp;
            }
        }
        return null;
    }
}