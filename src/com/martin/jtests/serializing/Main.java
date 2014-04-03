package com.martin.jtests.serializing;

import java.util.Scanner;

public class Main {

	private static IndexManager manager;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		manager = new IndexManager(sc);
		String input;
		displayCommands();

		while (!(input = sc.nextLine()).toLowerCase().contentEquals("end")) {
			if (!input.contentEquals("")) {
				Action action = Parser.getAction(input);
				doAction(action);
				System.out.println("\n");
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
		case HELP:
			displayCommands();
			break;
		case UNKNOWN:
			System.out.println("Unknown command");
			break;
		}
	}

	private static void displayCommands() {
		System.out.println("\nAvailable commands:\n");
		System.out
				.println("Generate [amount]\nWrite [name]\nRead [name]\nAdd [title]\nSearch [part of title or tag]\nHelp");
	}

}
