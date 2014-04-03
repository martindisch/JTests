package com.martin.jtests.serializing;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import com.martin.jtests.lists.Entry;
import com.martin.jtests.lists.RandomStrings;
import com.martin.jtests.lists.Tags;

public class IndexManager {
	private ArrayList<Entry> entries;

	public IndexManager() {
		super();
		entries = new ArrayList<Entry>();
	}

	public void generate(int size) {
		System.out.println("Filling list of size " + size);
		long millis = System.currentTimeMillis();
		Random r = new Random();
		RandomStrings rs = new RandomStrings();
		entries = new ArrayList<Entry>(size);
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
	}

	public void write(String filename) {
		try {
			FileOutputStream fos = new FileOutputStream(filename);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			long millis = System.currentTimeMillis();
			System.out.println("Writing to disk...");
			oos.writeObject(entries);
			oos.close();
			System.out.println("Data has been written successfully in "
					+ (System.currentTimeMillis() - millis) + "ms");
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Some IOException just happened");
		}
	}

	@SuppressWarnings("unchecked")
	public void read(String filename) {
		try {
			FileInputStream fis = new FileInputStream(filename);
			ObjectInputStream ois = new ObjectInputStream(fis);

			long millis = System.currentTimeMillis();
			System.out.println("Reading from disk...");
			entries = (ArrayList<Entry>) ois.readObject();
			ois.close();
			System.out.println("Data has been read successfully in "
					+ (System.currentTimeMillis() - millis) + "ms");
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Some IOException just happened");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	public void add(String title) {
		Scanner sc = new Scanner(System.in);
		String input;
		Tags tags = new Tags();

		System.out.println("What folder is this entry in?");
		int folder = sc.nextInt();
		System.out.println("What index is this entry in?");
		int index = sc.nextInt();
		System.out.println("Which page is this entry?");
		int page = sc.nextInt();

		System.out.println("You can add a tag if you want. If not, type 'end'");
		while (!(input = sc.nextLine()).contentEquals("end")) {
			if (!input.contentEquals("")) {
				tags.addTag(input);
				System.out
						.println("You can add another tag if you want. Once you're done, type 'end'");
			}
		}

		Entry entry = new Entry(title, folder, index, page, tags);
		entries.add(entry);

		System.out.println("Your entry has been added successfully");
	}

	public void search(String term) {
		ArrayList<Entry> results = new ArrayList<Entry>();
		for (Entry current : entries) {
			if (current.getTitle().toLowerCase().contains(term.toLowerCase())) {
				results.add(current);
			} else if (current.getTags().searchTags(term)) {
				results.add(current);
			}
		}

		System.out.println(results.size() + " results:\n");
		for (int i = 0; i < results.size(); i++) {
			System.out.println("[" + (i + 1) + "]   "
					+ results.get(i).getTitle());
		}
	}

	private void showMem() {
		Runtime rt = Runtime.getRuntime();
		long memory = (rt.totalMemory() - rt.freeMemory()) / (1024 * 1024);
		System.out.println("Using " + memory + "MB of memory");
	}
}
