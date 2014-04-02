package com.martin.jtests.lists;

import java.util.Scanner;

public class Runner {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input;
		
		System.out.println("Welcome to Indexy. You can add, remove, get or update entries.");
		while (!(input = sc.nextLine()).toLowerCase().contentEquals("end")) {
			if (input.toLowerCase().contentEquals("add")) {
				// Navigate to adding macro
			}
			else if (input.toLowerCase().contentEquals("remove")) {
				// Navigate to remove
			}
			else if (input.toLowerCase().contentEquals("get")) {
				// Navigate to getting stuff
			}
			else if (input.toLowerCase().contentEquals("update")) {
				// Get updating
			}
			else {
				System.out.println("Say again?");
			}
			System.out.println("Available commands: add, remove, get, update");
		}
		
		sc.close();
	}

}
