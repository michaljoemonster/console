package epam.homework.console;

import java.util.Scanner;

public class Main {
	private static String command;
	private static final String myShellSequence ="[My Shell]";
	private static String myConsoleVariableSequence = "$";
	private static final String sharpBracket = ">";
	private static String cdParameter;
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
		if (line.length() < 1) return "justEnter";
		String command =  new String("");
		if (line.indexOf(" ") >= 0)command = line.substring(0, line.indexOf(" "));
		else command = line;
			command.toLowerCase(); // there was no explicit statement about possible letters case in the exercise description
			switch(command)
			{
			case "prompt":
				String promptParameter = line.substring(7);
					promptParameter.trim();
					
					switch(promptParameter)
					{
					case "reset":
						return "reset";
					case "$cwd":
						return "currentDir";
					}
					return "repeat";
			case "exit":
				return "exit";
			case "dir":
				return "dir";
			case "tree":
				return "tree";
			case"cd":
				if (line.length()<4) return "brakKatalogu";
				cdParameter = line.substring(3);
				cdParameter.trim();
		//		cdParameter.toLowerCase();
				
			if (cdParameter.equals(".."))return "getRootDir";
			else return "getSubDir";
	}
			return "unknown";
	}
	private static void PrepareOutput(String line, String command)
	{
		
	}
}
