package fruithost;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Driver;
import java.io.BufferedInputStream;
import java.util.Properties;
import java.io.File;

public class Connector {
	private Properties config = new Properties();
	private Driver driver;
	
	public Connector(File file) {
		try {
			BufferedInputStream stream = new BufferedInputStream(new FileInputStream(file));
			config.load(stream);
			stream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.loadLibrary(file.getParentFile().getAbsolutePath());
	}
	
	private void loadLibrary(String path) {
		URLClassLoader loader	= null;
		String library			= this.getLibrary();
		File file				= new File(path + File.separator + library);
		
	    try {
			loader					= new URLClassLoader(new URL[] { file.toURI().toURL() }, System.class.getClassLoader() );
			Class<?> driver			= loader.loadClass("com.mysql.jdbc.Driver");
			this.driver				= (Driver) driver.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public String getName() {
		return config.getProperty("name");
	}
	
	public String getLibrary() {
		return config.getProperty("library");
	}

	public boolean hasMonitor() {
		try {
			String value = config.getProperty("monitor").toLowerCase();
			
			switch(value) {
				case "ok":
				case "true":
				case "yes":
				case "t":
				case "1":
					return true;
				
				default:
					return false;
			}
		} catch(Exception e) {
			return false;
		}
	}
	
	public IConnector getInstance() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		return Connector.class.getClassLoader().loadClass(this.getName()).asSubclass(IConnector.class).newInstance();
	}

	public Driver getDriver() {
		return driver;
	}
}
