package databaas.util.java;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {

	public static Path getJarPath() {
		Path jarPath = null;
		try {
			jarPath = Paths.get(FileUtils.class.getProtectionDomain().getCodeSource().getLocation().toURI());
		} catch (URISyntaxException e) { }
		return jarPath;
	}

}
