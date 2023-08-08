package com.abhishek.powershell.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.logging.Logger;

public class OutputThread {
	private static String result;

	public Thread startOutputThread(final InputStream inputStream) {
		Logger logger = Logger.getLogger("Powershell script executor logger");
		StringBuilder output = new StringBuilder();
		Thread thread = new Thread(() -> {
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
				String line;
				while((line = reader.readLine()) != null) {
					logger.info(line);
					output.append(line).append("\n");
				}
				reader.close();
				inputStream.close();
				OutputThread.setResult(output.toString());
			} catch(IOException e) {
				logger.info("Error while reading output: " + e.getMessage());
			}
		});
		thread.start();
		return thread;
	}
	
	public static String getResult() {
		return result;
	}

	public static void setResult(String result) {
		OutputThread.result = result;
	}
	
}
