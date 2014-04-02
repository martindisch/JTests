package com.martin.jtests.serializing;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input;
		System.out.println("Available commands: Generate [amount], Write2file [name], ReadFromFile[name]");
		
		while (!(input = sc.nextLine()).toLowerCase().contentEquals("end")) {
			Action action = Parser.getAction(input);
		}
		
		sc.close();
	}

}
