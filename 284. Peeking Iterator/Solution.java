// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
class PeekingIterator implements Iterator<Integer> {

    private ArrayList<Integer> list;
    private int n;
    
	public PeekingIterator(Iterator<Integer> iterator) {
	    // initialize any member here.
        list = new ArrayList<Integer>();
        while(iterator.hasNext()) {
            list.add(iterator.next());
        }
        n = -1;
	}

    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
        return list.get(n+1);
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
        n++;
	    return list.get(n);
	}

	@Override
	public boolean hasNext() {
        return list.size() - 1 != n;
	}
}