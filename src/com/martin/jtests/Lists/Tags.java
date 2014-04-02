package com.martin.jtests.Lists;

import java.io.Serializable;
import java.util.ArrayList;

public class Tags implements Serializable {

	private static final long serialVersionUID = 4755597217915780438L;
	private ArrayList<String> tags;
	
	public Tags() {
		super();
		tags = new ArrayList<String>();
	}

	public boolean searchTags(String term) {
		boolean contains = false;
		for (String act : tags) {
			if (act.contains(term)) {
				contains = true;
			}
		}
		return contains;
	}
	
	public void addTag(String tag) {
		tags.add(tag);
	}
}
