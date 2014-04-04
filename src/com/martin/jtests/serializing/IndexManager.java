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
	Scanner sc;

	public IndexManager(Scanner sc) {
		super();
		entries = new ArrayList<Entry>();
		this.sc = sc;
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

	public void add(String title) {
		String input;
		Tags tags = new Tags();

		try {
			System.out.println("What folder is this entry in?");
			int folder = Integer.parseInt(sc.nextLine());
			System.out.println("What index is this entry in?");
			int index = Integer.parseInt(sc.nextLine());
			System.out.println("Which page is this entry?");
			int page = Integer.parseInt(sc.nextLine());

			System.out
					.println("You can add a tag if you want. If not, type 'end'");
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
		} catch (NumberFormatException e) {
			System.out
					.println("You failed to enter a simple number, entry has not been added");
		}
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

		if (results.size() > 0) {
			System.out.println(results.size() + " results:\n");
			for (int i = 0; i < results.size(); i++) {
				if (!results.get(i).getTitle().toLowerCase()
						.contains(term.toLowerCase())) {
					System.out.println("[" + (i + 1) + "]   "
							+ results.get(i).getTitle()
							+ " (occurrence in tags)");
				} else {
					System.out.println("[" + (i + 1) + "]   "
							+ results.get(i).getTitle());
				}
			}

			try {
				System.out.println("\nWhich result do you want to display?");
				int iResult = Integer.parseInt(sc.nextLine());
				iResult--;

				Entry result = results.get(iResult);

				System.out.println("\nTitle:  " + result.getTitle());
				if (result.getTags().getList().size() > 0) {
					System.out.print("Tags:   ");
					for (String tag : result.getTags().getList()) {
						if (!tag.equals(result.getTags().getList()
								.get(result.getTags().getList().size() - 1))) {
							System.out.print(tag + ", ");
						} else {
							System.out.println(tag);
						}
					}
				}
				System.out.println("Folder: " + result.getFolder());
				System.out.println("Index:  " + result.getIndex());
				System.out.println("Page:   " + result.getPage());
			} catch (NumberFormatException e) {
				System.out.println("Input not a number, search aborted");
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Non-existent result, search aborted");
			}
		} else {
			System.out.println("No results");
		}
	}
	
	public void list(String data) {
		String[] parts = data.split(" ");
		int start = 0;
		int end = 0;
		try {
			if (Integer.parseInt(parts[0]) > entries.size()) {
				throw new IndexOutOfBoundsException();
			}
			start = Integer.parseInt(parts[0]) - 1;
			if (parts.length > 1) {
				if (Integer.parseInt(parts[1])  > entries.size()) {
					throw new IndexOutOfBoundsException();
				}
				end = Integer.parseInt(parts[1]) - 1;
			}
			else {
				end = entries.size() - 1;
			}
			
			for (int i = start; i < end + 1; i++) {
				System.out.println("[" + (i + 1) + "]   "
						+ entries.get(i).getTitle());
			}
			
			System.out.println("\nWhich result do you want to display?");
			int iResult = Integer.parseInt(sc.nextLine());
			iResult--;

			Entry result = entries.get(iResult);

			System.out.println("\nTitle:  " + result.getTitle());
			if (result.getTags().getList().size() > 0) {
				System.out.print("Tags:   ");
				for (String tag : result.getTags().getList()) {
					if (!tag.equals(result.getTags().getList()
							.get(result.getTags().getList().size() - 1))) {
						System.out.print(tag + ", ");
					} else {
						System.out.println(tag);
					}
				}
			}
			System.out.println("Folder: " + result.getFolder());
			System.out.println("Index:  " + result.getIndex());
			System.out.println("Page:   " + result.getPage());
		}
		catch (NumberFormatException e) {
			System.out.println("Input not a number, listing aborted");
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("Input is out of bounds");
		}
	}

	private void showMem() {
		Runtime rt = Runtime.getRuntime();
		long memory = (rt.totalMemory() - rt.freeMemory()) / (1024 * 1024);
		System.out.println("Using " + memory + "MB of memory");
	}
}
