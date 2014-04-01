package com.martin.jtests.Lists;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<Entry> list = new ArrayList<Entry>();
		list.add(new Entry("Zahn", 1, 2, 15));
		list.add(new Entry("Zahn", 1, 2, 16));
		list.add(new Entry("Schlimm", 1, 2, 17));
		
		Entry cr = new Entry("Zahn2", 1, 2, 16);
		list.add(cr);
		
		Entry mine = list.get(list.indexOf(cr));
		System.out.println(mine.getName());
		
		Entry toUpdate = list.get(list.indexOf(mine));
		toUpdate.setName("New");
		list.remove(toUpdate);
		list.add(toUpdate);
		System.out.println(list.get(list.indexOf(toUpdate)).getName());
	}

}
