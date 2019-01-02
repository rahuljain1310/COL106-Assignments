import java.util.ArrayList;
import java.util.Arrays;
class SearchEngine {
    InvertedPageIndex IPI;
    SearchEngine () {
       IPI = new InvertedPageIndex();
    }
    String printPagesinRelevance (Myset<SearchResult> searchSet) {
        ArrayList<SearchResult> searchList = MySort.sortThisList(searchSet);
        String res = "";
        for(SearchResult sr: searchList) {
            res=res+=sr.page.getWebpageName()+", ";
        }
        if(res!="") {
            res = res.substring(0,res.length()-2);
            return res;
        } else {
            return "No search result found";
        }
    }
    Myset<SearchResult> getSearchResultSet (Myset<PageEntry> x,String[] str,boolean isphrase) {
        Myset<SearchResult> y = new Myset<SearchResult>();
        for(int i=0;i<x.Size();i++) {
            PageEntry page = x.ElementAt(i);
            float revelance = page.getRelevanceOfPage(str, isphrase);
            SearchResult sr = new SearchResult(page, revelance);
            y.Insert(sr);
        }
        return y;
    }

    String filterWord (String x) {
        switch(x) {
            case "structures":
            case "stacks":
            case "applications":
            x = x.substring(0,x.length()-1);
        }
        x = x.toLowerCase();
        return x;
    }

    void filterWordArray (String str[]) {
        for(int i=0;i<str.length;i++) {
            str[i] = filterWord(str[i]);
        }
    }
   

    String performAction (String actionMessage) {
        String input[] = actionMessage.split(" ");
        switch(input[0]) {
            case "addPage":{
                String x = input[1];
                PageEntry p = new PageEntry(x);
                IPI.addPage(p);
                return "";
            }
            case "queryFindPagesWhichContainWord" : {
                input[1]=filterWord(input[1]);
                Myset<PageEntry> x = IPI.getPagesWhichContainWord(input[1]);
                String res = "";
                for(int i=0;i<x.Size();i++) {
                    res = res + x.ElementAt(i).getWebpageName()+", ";
                }
                if(res!="") {
                    res = res.substring(0,res.length()-2);
                } else {
                    res = "No webpage contains word "+input[1];
                }
                return res;
            }
            case "queryFindPositionsOfWordInAPage" : {
                input[1] = filterWord(input[1]);
                String word = input[1];
                String Webpage = input[2];
                MyLinkedlist<Integer> res = IPI.getPostionsinWebpage(word, Webpage);
                // System.out.println(res.Size());
                String x = "";
                for(int i=0;i<res.Size();i++) {
                    x = x+res.ElementAt(i).toString()+", ";
                }
                if(x!=""){
                    x = x.substring(0,x.length()-2);
                } else {
                    x = "Webpage "+Webpage+" does not contain word "+word;
                }
                return x;
            }
            case "queryFindPagesWhichContainAllWords" : {
                String str[] = Arrays.copyOfRange(input, 1, input.length);
                filterWordArray(str);
                Myset<PageEntry> x = IPI.getPagesWhichContainAllWords(str);
                Myset<SearchResult> y = getSearchResultSet(x,str,false);
                return printPagesinRelevance(y);

            }
            case "queryFindPagesWhichContainAnyOfTheseWords" : {
                String str[] = Arrays.copyOfRange(input, 1, input.length);
                filterWordArray(str);
                Myset<PageEntry> x = IPI.getPagesWhichContainAnyWord(str);
                Myset<SearchResult> y = getSearchResultSet(x,str,false);
                return printPagesinRelevance(y);
            }
            case "queryFindPagesWhichContainPhrase" : {
                String str[] = Arrays.copyOfRange(input, 1, input.length);
                filterWordArray(str);
                Myset<PageEntry> x = IPI.getPagesWhichContainPhrase(str);
                Myset<SearchResult> y = getSearchResultSet(x,str,true);
                return printPagesinRelevance(y);
            }
        }
        return "";
    } 
}