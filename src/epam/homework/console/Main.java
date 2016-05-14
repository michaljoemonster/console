package epam.homework.console;

import java.util.Scanner;

public class Main {
	private static String command;
	private static final String myShellSequence ="[My Shell]";
	private static String myConsoleVariableSequence = "$";
	private static final String sharpBracket = ">";
	public static void main(String[] args) {

		try (Scanner in = new Scanner (System.in,"UTF-8"))
		{
		System.out.print(myShellSequence + " " + myConsoleVariableSequence + sharpBracket);
		
		while (true)
		{
			String line = in.nextLine();
		command = CheckInput(line);
		System.out.println(command); // for testing actual command
		PrepareOutput(line, command);
		}
		}
	}
	private static String CheckInput(String line)
	{
		return "";
	}
	private static void PrepareOutput(String line, String command)
	{
		
	}
}
