package epam.homework.console;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyDirVisitor {

	private Path path;
	private String prefix;
	
	public MyDirVisitor(Path p, String prfx) {
		path = Paths.get(p.toString());
		prefix = new String(prfx);
	}
public void Visit()
{

	File file = path.toFile();
	File [] files = file.listFiles();
		for (File f: files)
		{
			if(f.isDirectory() && !f.isHidden())
			{
				System.out.println(prefix + f.getName());
				if(f.listFiles()!= null && f.listFiles().length > 0) 
				{
					MyDirVisitor myDirVisitor = new MyDirVisitor(f.toPath(),"\u002D" + prefix);
					myDirVisitor.Visit();
				}
			}
		}
}
}
