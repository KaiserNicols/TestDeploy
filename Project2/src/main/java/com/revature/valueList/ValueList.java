package com.revature.valueList;

import java.util.List;

public interface ValueList {
	public int getSize();
	public Object getCurrentElement();
	public List getPreviousElement(int count);
	public List getNextElement(int count);
	public void resetIndex();
	public void setList(List list);

}
