package com.revature.valueList;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class ValueListHandler implements ValueList{
	protected List list;
	protected ListIterator listIterator;
	
	public ValueListHandler() {}
	
	

	@Override
	public int getSize() {
		int size = 0;
		if (list != null)
			size = list.size();
		return size;
	}

	@Override
	public Object getCurrentElement() {
		Object obj = null;
		if (list !=null) {
			int currIndex = listIterator.nextIndex();
			obj = list.get(currIndex);
		}
		return obj;
	}

	@Override
	public List getPreviousElement(int count) {
		int i = 0;
		Object obj = null;
		LinkedList list = new LinkedList();
		if (listIterator != null) {
			while (listIterator.hasPrevious() && (i<count)) {
				obj = listIterator.previous();
				list.add(obj);
				i++;
			}
		}
		return list;
	}

	@Override
	public List getNextElement(int count) {
		int i = 0;
		Object obj = null;
		LinkedList list = new LinkedList();
		if (listIterator != null) {
			while (listIterator.hasNext() && (i<count)) {
				obj = listIterator.next();
				list.add(obj);
				i++;
			}
		}
		return list;
	}

	@Override
	public void resetIndex() {
		if (listIterator != null) {
			listIterator = list.listIterator();
		}
		
	}

	@Override
	public void setList(List list) {
		this.list = list;
		if(list != null)
			listIterator = list.listIterator();
	}



	public ListIterator getListIterator() {
		return listIterator;
	}



	public void setListIterator(ListIterator listIterator) {
		this.listIterator = listIterator;
	}



	public List getList() {
		return list;
	}

}
