package sp.embed;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Embedded {
	
	public static byte[] DEFAULT_FONT;
	
	static {
		//DEFAULT_FONT = readEmbeddedFile("/SourceSansPro-Light.ttf");
	}
	
	public static byte[] readEmbeddedFile(String name) {
		try {
			InputStream in = Embedded.class.getResourceAsStream(name);
			byte[] buffer = new byte[8192];
		    int bytesRead;
		    ByteArrayOutputStream output = new ByteArrayOutputStream();
		    while ((bytesRead = in.read(buffer)) != -1)
		    {
		        output.write(buffer, 0, bytesRead);
		    }
		    return output.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new byte[0];
	}
	
}
