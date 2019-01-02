import java.util.ArrayList;
import java.util.Collections;
class MySort {
    static ArrayList<SearchResult> sortThisList( Myset<SearchResult> listOfSortableEntries) {
        Myset<SearchResult> resultSet = listOfSortableEntries;
        ArrayList<SearchResult> SortedArray = new ArrayList<>();
        for(int i=0;i<resultSet.Size();i++) {
            SortedArray.add(resultSet.ElementAt(i));
        }
        Collections.sort(SortedArray);
        return SortedArray;
    }
}