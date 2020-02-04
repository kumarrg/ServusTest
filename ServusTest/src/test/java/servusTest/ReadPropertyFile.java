package servusTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadPropertyFile {
	
	protected Properties prop = null;
	protected InputStream input = ReadPropertyFile.class.getResourceAsStream("input.properties");
	
	public ReadPropertyFile() throws IOException {
		prop = new Properties();
		prop.load(input);
	}
	
	public String GetUsername() {
		return prop.getProperty("username");
	}
	
	public String GetPass() {
		return prop.getProperty("password");
	}
}
