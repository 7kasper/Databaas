package database.util;

import java.io.File;
import java.nio.file.Path;

import databaas.util.java.FileUtils;

public class TestUtils {

	public static Path getTestPath() {
		return FileUtils.getJarPath().getParent().getParent().resolve("tests");
	}

	public static File getSqliteTestDir() {
		File sqliteTestDir = getTestPath().resolve("sqlite").toFile();
		sqliteTestDir.mkdirs();
		return sqliteTestDir;
	}

}
