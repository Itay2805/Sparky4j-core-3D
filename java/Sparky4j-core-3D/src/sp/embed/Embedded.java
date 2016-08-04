package sp.embed;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Embedded {
	
	public static byte[] DEFAULT_FONT;
	
	static {
		try {
			//DEFAULT_FONT = readEmbeddedFile("/SourceSansPro-Light.ttf");
			DEFAULT_FONT = Files.readAllBytes(Paths.get("res/SourceSansPro-Light.ttf"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static byte[] readEmbeddedFile(String name) throws IOException {
		InputStream in = Embedded.class.getResourceAsStream(name);
		byte[] buffer = new byte[8192];
	    int bytesRead;
	    ByteArrayOutputStream output = new ByteArrayOutputStream();
	    while ((bytesRead = in.read(buffer)) != -1)
	    {
	        output.write(buffer, 0, bytesRead);
	    }
	    return output.toByteArray();
	}
	
}
