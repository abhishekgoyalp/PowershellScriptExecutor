package com.abhishek.powershell.util;

import java.io.File;

public class ScriptPathChecker {
	
	private ScriptPathChecker() {}
	
	/**
	 * This function is responsible for check that file exists or not
	 * @param filePath
	 * @return {@linkplain Boolean}
	 */
	public static boolean isFileExists(String filePath) {
		File file = new File(filePath);
		return file.exists();
	}
}
