class SearchResult implements Comparable<SearchResult> {
    PageEntry page;
    float relevance = 0;
    SearchResult (PageEntry p, float x) {
        page = p;
        relevance = x;
    }
    public PageEntry getPageEntry() {
        return page;
    }
    public float getRelevance() {
        return relevance;
    }
    public int compareTo(SearchResult otherObject) {
        Float x = new Float(relevance);
        Float y = new Float(otherObject.getRelevance());
        return y.compareTo(x);
    }
}