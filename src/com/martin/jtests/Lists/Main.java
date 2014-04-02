package com.martin.jtests.Lists;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

	private static final int size = 100000;
	private static ArrayList<Entry> entries;

	public static void main(String[] args) {

		// initialize
		System.out.println("Filling list of size " + size);
		long millis = System.currentTimeMillis();
		Random r = new Random();
		RandomStrings rs = new RandomStrings();
		entries = new ArrayList<Entry>();
		String title;
		int folder;
		int index;
		int page;
		Tags tags;
		Entry newOne;
		for (int i = 0; i < size; i++) {
			title = rs.getRandomString();
			folder = r.nextInt(20);
			index = r.nextInt(20);
			page = r.nextInt(20);
			tags = new Tags();
			for (int z = 0; z < 5; z++) {
				tags.addTag(rs.getRandomString());
			}
			newOne = new Entry(title, folder, index, page, tags);
			entries.add(newOne);
		}
		System.out.println("List filled in "
				+ (System.currentTimeMillis() - millis) + "ms");

		showMem();

		Scanner sc = new Scanner(System.in);
		String input;

		// Add some custom titles
		do {
			System.out.println("Enter a new title:");
			input = sc.nextLine();
			if (!input.toLowerCase().contentEquals("end")) {
				folder = r.nextInt(20);
				index = r.nextInt(20);
				page = r.nextInt(20);
				tags = new Tags();
				for (int z = 0; z < 5; z++) {
					tags.addTag(rs.getRandomString());
				}
				Entry e = new Entry(input, folder, index, page, tags);
				entries.add(e);
			}
		} while (!input.toLowerCase().contentEquals("end"));

		// add some custom tags
		do {
			System.out.println("Enter a new tag:");
			input = sc.nextLine();
			if (!input.toLowerCase().contentEquals("end")) {
				title = rs.getRandomString();
				folder = r.nextInt(20);
				index = r.nextInt(20);
				page = r.nextInt(20);
				tags = new Tags();
				for (int z = 0; z < 5; z++) {
					tags.addTag(rs.getRandomString());
				}
				tags.addTag(input);
				Entry e = new Entry(title, folder, index, page, tags);
				entries.add(e);
			}
		} while (!input.toLowerCase().contentEquals("end"));

		// now search
		do {
			System.out.println("Enter tag or title to be searched:");
			input = sc.nextLine();
			if (!input.toLowerCase().contentEquals("end")) {
				long now = System.currentTimeMillis();
				int tagsfound = 0;
				int titlesfound = 0;
				for (Entry current : entries) {
					if (current.getTitle().contains(input)) {
						titlesfound++;
					}
					if (current.getTags().searchTags(input)) {
						tagsfound++;
					}
				}
				System.out.println(tagsfound + " tags and " + titlesfound
						+ " titles found in "
						+ (System.currentTimeMillis() - now) + "ms");
			}
		} while (!input.toLowerCase().contentEquals("end"));

		sc.close();
	}

	private static void showMem() {
		Runtime rt = Runtime.getRuntime();
		long memory = (rt.totalMemory() - rt.freeMemory()) / (1024 * 1024);
		System.out.println("Using " + memory + "MB of memory");
	}

}
