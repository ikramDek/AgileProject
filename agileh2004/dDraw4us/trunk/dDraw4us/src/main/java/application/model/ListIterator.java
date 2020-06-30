package application.model;

import java.util.List;

public class ListIterator implements Iterator {

	private List<Layout> layoutList ;
	private int index ;

	public ListIterator(List<Layout> list) {
		super();
		this.layoutList = list;
	}

	@Override
	public boolean hasNext() {
		if(index < layoutList.size()){
			return true;
		}
		return false;
	}

	@Override
	public Object next() {
		if(this.hasNext()){
			return layoutList.get(index++);
		}
		return null;
	}		
}

