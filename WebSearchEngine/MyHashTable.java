class MyHashTable {
    class WordEntryNode {
        WordEntry Wentry;
        WordEntryNode next;
        WordEntryNode (WordEntry w) {
            Wentry = w;
            next = null;
        }
    }
    WordEntryNode[] Bitbucket ;
    MyHashTable () {
        Bitbucket = new WordEntryNode[676];
        for(int i=0;i<676;i++) {
            Bitbucket[i] = null;
        }
    }
    private int getHashIndex(String str) {
        // System.out.println(str);
        str = str.toLowerCase();
        if(str.length()>=2) {
            int index = (26*(str.charAt(0)-'a')+(str.charAt(1)-'a')+6760)%676;
            // System.out.println(index);
            return index;
        } else {
            int index = (26*(str.charAt(0)-'a')+6760)%676;
            return index;
        }
       
    }
    void addPositionsForWord(WordEntry w) {
        int index = getHashIndex(w.getWord());
        WordEntryNode head = Bitbucket[index];
        if(head == null){
            WordEntry n = new WordEntry(w.getWord());
            n.addPositions(w.getAllPositionsForThisWord());
            Bitbucket[index] = new WordEntryNode(n);
            return ;
        } else {
            WordEntryNode tail = head;
            while(head!=null) {
                WordEntry x = head.Wentry;
                if(x.getWord().equals(w.getWord())) {
                    x.addPositions(w.getAllPositionsForThisWord());
                    return;
                }
                tail = head;
                head = head.next;
            }
            WordEntry n = new WordEntry(w.getWord());
            n.addPositions(w.getAllPositionsForThisWord());
            tail.next = new WordEntryNode(n);

        }
    }
    WordEntry getWordEntry (String word) {
        int index = getHashIndex(word);
        WordEntryNode head = Bitbucket[index];
        while(head!=null) {
            WordEntry x = head.Wentry;
            if(x.getWord().equals(word)) {
                return x;
            }
            head=head.next;
        }
        return new WordEntry("");
    }

}