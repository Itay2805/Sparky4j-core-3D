package sp.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileUtils {
	
	public static String readFile(String filepath) {
		try {
			String text = "";
			List<String> lines = Files.readAllLines(Paths.get(filepath));
			for(String line : lines)
				text += line + "\n";
			return text;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
