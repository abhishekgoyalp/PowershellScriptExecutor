package com.abhishek.powershell.util;

public class PowershellPathGetter {
	private static String os = System.getProperty("os.name").toLowerCase();
	public static final String WIN_EXECUTABLE_PATH = "pwsh.exe";
	public static final String LINUX_MAC_EXECUTABLE_PATH = "/usr/local/bin/pwsh";
	
	private PowershellPathGetter() {}
	
	public static String getPowershellPath() {
		
		if(os.contains("win")) {
			return WIN_EXECUTABLE_PATH;
		}
		return LINUX_MAC_EXECUTABLE_PATH;
	}
}
