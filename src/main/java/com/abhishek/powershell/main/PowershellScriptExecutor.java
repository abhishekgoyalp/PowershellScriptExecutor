package com.abhishek.powershell.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.abhishek.powershell.util.PowershellPathGetter;
import com.abhishek.powershell.util.ScriptPathChecker;

public class PowershellScriptExecutor {
	long pid;
	int exitValue;
	String scriptPath;
	String executablePath = PowershellPathGetter.getPowershellPath();
	List<String> arguments;
	long timeOutInSec = 60;
	private static final Logger logger = Logger.getLogger("Powershell script executor logger");
	private static final String SCRIPT_NOT_EXISTS_ERROR_MSG = " script path does not exists on your system.";
	
	public PowershellScriptExecutor(String scriptPath) {
		this.scriptPath = scriptPath;
	}
	
	public PowershellScriptExecutor(String executablePath, String scriptPath) {
		this.scriptPath = scriptPath;
		this.executablePath = executablePath;
	}
	
	public PowershellScriptExecutor(String scriptPath, List<String> args) {
		this.scriptPath = scriptPath;
		this.arguments = args;
	}
	
	public PowershellScriptExecutor(String executablePath, String scriptPath, List<String> args) {
		this.scriptPath = scriptPath;
		this.executablePath = executablePath;
		this.arguments = args;
	}
	
	public PowershellScriptExecutor(String scriptPath, long timeout) {
		this.scriptPath = scriptPath;
		this.timeOutInSec = timeout;
	}
	
	public PowershellScriptExecutor(String executablePath, String scriptPath, long timeout) {
		this.scriptPath = scriptPath;
		this.executablePath = executablePath;
		this.timeOutInSec = timeout;
	}
	
	public PowershellScriptExecutor(String scriptPath, List<String> args, long timeout) {
		this.scriptPath = scriptPath;
		this.arguments = args;
		this.timeOutInSec = timeout;
	}
	
	public PowershellScriptExecutor(String executablePath, String scriptPath, List<String> args, long timeout) {
		this.scriptPath = scriptPath;
		this.executablePath = executablePath;
		this.arguments = args;
		this.timeOutInSec = timeout;
	}
	
	private List<String> createBuilderArguements(List<String> args){
		List<String> processArguments = new ArrayList<>();
		processArguments.add(executablePath);
		processArguments.add("-Command");
		processArguments.add(scriptPath);
		processArguments.addAll(args);
		return processArguments;
	}
	
	public String executeScript() {
		if(!ScriptPathChecker.isFileExists(scriptPath))
			logger.info(SCRIPT_NOT_EXISTS_ERROR_MSG);
		try {
			ProcessBuilder builder = new ProcessBuilder(createBuilderArguements(this.arguments));
			builder.redirectErrorStream(true);
			
			Process process = builder.start();
			this.pid = process.pid();
			
			Thread outputThread = new OutputThread().startOutputThread(process.getInputStream());
			
			if(!process.waitFor(this.timeOutInSec, TimeUnit.SECONDS)) {
				logger.info("Process takes longer than " + this.timeOutInSec);
			}
			this.exitValue = process.exitValue();
			logger.info(String.format("Script executed with exit code: %s", this.exitValue));
			outputThread.join();
			
		} catch (IOException | InterruptedException e) {
			logger.info(String.format("Error while running Powershell Script: %s", e.getMessage()));
			if(e instanceof InterruptedException)
				Thread.currentThread().interrupt();
		}
		return OutputThread.getResult();
	}

	public long getPid() {
		return this.pid;
	}
	
	public int getExitValue() {
		return this.exitValue;
	}
	
}
