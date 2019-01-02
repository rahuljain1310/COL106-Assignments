import java.util.*; 
import java.io.*;
import java.lang.Math;
class PageEntry {
    private PageIndex WebpageIndex = new PageIndex();
    private InvertedPageIndex SEngine_IPI;
    private String WebpageName;
    PageEntry (String pagename) {
        WebpageName = pagename;
        try {
            String file_str = "";
            FileInputStream fstream = new FileInputStream (pagename);
            Scanner scanner = new Scanner (fstream);
            file_str = scanner.nextLine();
            while (scanner.hasNextLine()) {
                file_str = file_str + " " + scanner.nextLine();
            }
            scanner.close();
            char[] charArray = file_str.toCharArray();
            for(int i=0;i<charArray.length;i++) {
                switch(charArray[i]) {
                    case '{':
                    case '}':
                    case '!':
                    case '[':
                    case ']':
                    case '<':
                    case '>':
                    case '=':
                    case '(':
                    case ')':
                    case '.':
                    case ',':
                    case ';':
                    case '"':
                    case '\'':
                    case '#':
                    case '?':
                    case ':':
                    case '-':
                    charArray[i] = ' ';
                }
            }
            String content = new String (charArray);
            int wordindex = 0;
            int trueindex = 0;
            String[] input = content.split(" ");
            for (int i=0;i<input.length;i++) {
                String x = input[i];
                if(x.equals(" ") || x.equals("")) continue; 
                x = x.toLowerCase();
                wordindex++; 
                switch(x) {
                    case "a":
                    case "an":
                    case "the":
                    case "they":
                    case "these":
                    case "this":
                    case "for":
                    case "is":
                    case "are":
                    case "was":
                    case "of":
                    case "or":
                    case "and":
                    case "does":
                    case "will":
                    case "":
                    case "whose":
                        continue;
                    case "stacks":
                    case "structures":
                    case "applications":
                        x = x.substring(0,x.length()-1);
                        break;
                }
                trueindex++;
                Position temp = new Position(this, wordindex, trueindex);
                WebpageIndex.addPositionForWord(x, temp);
            }
            // System.out.println("Hello To be printed");
            // WebpageIndex.printPageIndex();
        } catch (Exception e) {
            System.out.println("File not found");
        }
    }
    public String getWebpageName () {
        return WebpageName;
    }
    PageIndex getPageIndex(){
        return WebpageIndex;
    }
    void setInvertedPageIndex (InvertedPageIndex x) {
        SEngine_IPI = x;
    }
    float getRelevanceOfPage(String str[], boolean doTheseWordsRepresentAPhrase ) {

        if(doTheseWordsRepresentAPhrase) {
            float m = (float )WebpageIndex.phraseFrequency(str);
            float k = (float)str.length;
            int Wp = WebpageIndex.totalWords;
            float N = (float) SEngine_IPI.PageSet.Size();
            float Nw = (float) SEngine_IPI.getPagesWhichContainPhrase(str).Size();
            float idf = (float) Math.log(N/Nw);
            float tf = m/(Wp - m*(k-1));
            return idf*tf;

        } else {
            float PageRelevance = 0;
            for(int i=0;i<str.length;i++) {
                float WordRelevance = getRelevanceOfWord(str[i]);
                PageRelevance+=WordRelevance;
            }
            return PageRelevance;
        }
    }
    float getRelevanceOfWord (String word) {
        float tf = WebpageIndex.getTermFrequency(word);
        float N = (float) SEngine_IPI.PageSet.Size();
        float Nw = (float) SEngine_IPI.getPagesWhichContainWord(word).Size();
        float idf = (float) Math.log(N/Nw);
        return tf*idf;
    }
    boolean ContainAllWords (String str[]) {
        for(int i=0;i<str.length;i++) {
            if (WebpageIndex.getWordEntry(str[i]) == null )
                return false;
        }
        return true;
    }
    boolean ContainAnyWord (String str[]) {
        for(int i=0;i<str.length;i++) {
            if (WebpageIndex.getWordEntry(str[i]) != null )
                return true;
        }
        return false;
    }
    public static void main(String[] args) {
        String pagename = "stack_datastructure_wiki";
        try {
            FileInputStream fstream = new FileInputStream (pagename);
            Scanner scanner = new Scanner (fstream);
            String file_str = "";
            file_str = scanner.nextLine();
            while (scanner.hasNextLine()) {
                file_str = file_str + " " + scanner.nextLine();
            }
            scanner.close();
            char[] charArray = file_str.toCharArray();
            for(int i=0;i<charArray.length;i++) {
                switch(charArray[i]) {
                    case '{':
                    case '}':
                    case '!':
                    case '[':
                    case ']':
                    case '<':
                    case '>':
                    case '=':
                    case '(':
                    case ')':
                    case '.':
                    case ',':
                    case ';':
                    case '"':
                    case '\'':
                    case '#':
                    case '?':
                    case ':':
                    case '-':
                    charArray[i] = ' ';
                }
            }
            String content = new String(charArray);
            content = content.toLowerCase();
            // System.out.println(content);
            String[] input = content.split(" ");
            for (int i=0;i<input.length;i++) {
                String x = input[i];
                switch(x) {
                    case "a":
                    case "an":
                    case "the":
                    case "they":
                    case "these":
                    case "this":
                    case "for":
                    case "is":
                    case "are":
                    case "was":
                    case "of":
                    case "or":
                    case "and":
                    case "does":
                    case "will":
                    case "whose":
                        continue;
                    case "stacks":
                    case "structures":
                    case "applications":
                        x = x.substring(0,x.length()-1);
                        break;
                }
                System.out.println(x);
            }
        } catch (Exception e) {
            System.out.println("File not found");
        }
    }

    
}