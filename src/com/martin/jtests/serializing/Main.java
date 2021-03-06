package com.martin.jtests.serializing;

import java.util.Scanner;

public class Main {

	private static IndexManager manager;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		manager = new IndexManager(sc);
		String input;
		displayCommands();
		System.out.print("\n");

		while (!(input = Io.in()).toLowerCase().contentEquals("end")) {
			try {
				Action action = Parser.getAction(input);
				doAction(action);
				System.out.print("\n");
			}
			catch (Exception e) {
				Io.out("A strange exception has just occurred: " + e.getMessage() + "\nBut don't worry, business as usual\n");
			}
		}

		sc.close();
	}

	private static void doAction(Action action) {
		switch (action.getCommand()) {
		case GENERATE:
			manager.generate(Integer.parseInt(action.getData()));
			break;
		case WRITE:
			manager.write(action.getData());
			break;
		case READ:
			manager.read(action.getData());
			break;
		case ADD:
			manager.add(action.getData());
			break;
		case SEARCH:
			manager.search(action.getData());
			break;
		case LIST:
			manager.list(action.getData());
			break;
		case DELETE:
			manager.delete(action.getData());
			break;
		case DISPLAY:
			manager.display(action.getData());
			break;
		case HELP:
			displayCommands();
			break;
		case UNKNOWN:
			Io.out("Unknown command");
			break;
		}
	}

	private static void displayCommands() {
		Io.out("\nAvailable commands:\n");
		Io.out(" - End (Exit the application)");
		System.out
				.println(" - Generate [amount] (Add a number of random Entries with random titles and tags to the currently loaded collection)");
		System.out
				.println(" - Write [name] (Save the currently loaded collection to disk)");
		Io.out(" - Read [name] (Load a collection from a file)");
		System.out
				.println(" - Add [title] (Add a new entry to the currently loaded collection)");
		System.out
				.println(" - Search [part of title or tag] (Search the currently loaded collection)");
		System.out
				.println(" - List [start] [end (optional)] (List entries [start] to [end]");
		Io.out(" - Delete [number] (Delete this entry)");
		Io.out(" - Display [number] (Display this entry)");
		Io.out(" - Help (Display this help)");
	}

}
