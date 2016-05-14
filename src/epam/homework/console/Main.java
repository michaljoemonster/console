package epam.homework.console;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
	private static String command;
	private static final String myShellSequence ="[MyShell]";
	private static String myConsoleVariableSequence = "$";
	private static final String sharpBracket = ">";
	private static String cdParameter;
	private static Path path = Paths.get(System.getProperty("user.dir"));
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
				if (line.length()<8) return "noPromptParameter";
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
				if (line.length()<4) return "noCdParameter";
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
		switch(command)
		{
		case "unknown":
			if (line.indexOf(" ")>= 0)System.out.print((line.substring(0,line.indexOf(" "))+" : unknown command"
		+ System.getProperty("line.separator")));
			else System.out.println( line.substring(0)+" : unknown command");
			System.out.print( myShellSequence  + " " + myConsoleVariableSequence + sharpBracket);
			break;
		case "reset":
			myConsoleVariableSequence = "$";
			System.out.print( myShellSequence  + " " + myConsoleVariableSequence + sharpBracket);
			break;
		case "repeat":
			myConsoleVariableSequence = line.substring(7);
			System.out.print( myShellSequence + myConsoleVariableSequence + sharpBracket);
			break;
		case "currentDir":
			myConsoleVariableSequence = path.toString();
			System.out.print( myShellSequence + myConsoleVariableSequence + sharpBracket);
		break;
		case "exit":
			System.out.println("Bye");
			System.exit(0);
		case "noPromptParameter":
			System.out.println("Brak parametru dla komendy prompt");
			System.out.print( myShellSequence + myConsoleVariableSequence + sharpBracket);
			break;
		case "noCdParameter":
			System.out.println("Brak parametru dla komendy cd");
			System.out.print( myShellSequence + myConsoleVariableSequence + sharpBracket);
			break;
		case "dir":
			try(DirectoryStream<Path> entries = Files.newDirectoryStream(path))
			{
				System.out.println("Content of " + path.toString());
				for (Path entry : entries)
				{
					File file = entry.toFile();
					if (file.isDirectory()) System.out.println("DIR" + "\u0009" + file.getName());
					else System.out.println("FILE" + "\u0009" + file.getName());
				}
				System.out.print(myShellSequence  + " " + myConsoleVariableSequence + sharpBracket);
				
			} catch (IOException e) {
				e.printStackTrace();
				System.out.print(myShellSequence  + " " + myConsoleVariableSequence + sharpBracket);
			}
			break;
		case "tree":
			System.out.println(path.getFileName());
			MyDirVisitor myDirVisitor = new MyDirVisitor(path,"\u002D");
			myDirVisitor.Visit();
			System.out.print(myShellSequence  + " " + myConsoleVariableSequence + sharpBracket);
			break;
		}
	}
}
