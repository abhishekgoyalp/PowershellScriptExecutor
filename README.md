# Java: PowerShell Script Executor

Author: Abhishek Goyal

Date: Aug 08, 2023

# Introduction

PowerShell is a versatile command-line shell and scripting language developed by Microsoft. It enables efficient system administration and task automation on Windows and some other platforms. Combining commands, known as cmdlets, with .NET framework functionality, PowerShell simplifies complex tasks through concise and scriptable actions.

This straightforward PowerShell script executor allows effortless interaction with the PowerShell console using Java.

# Requirements

The fundamental prerequisite is the installation of PowerShell Core on your system.

To install PowerShell Core, proceed with the following steps:

1. For Windows OS - [Install PowerShell Core in Windows](https://learn.microsoft.com/en-us/powershell/scripting/install/installing-powershell-on-windows?view=powershell-7.3)
2. For Linux OS - [Install PowerShell Core in Linux](https://learn.microsoft.com/en-us/powershell/scripting/install/installing-powershell-on-linux?view=powershell-7.3)
3. For Mac OS - [Install PowerShell Core in Mac OS](https://learn.microsoft.com/en-us/powershell/scripting/install/installing-powershell-on-macos?view=powershell-7.3#installation-via-direct-download)

If the provided steps are challenging, you can turn to GitHub for PowerShell Core releases. These releases encompass various types of PowerShell package files, enabling you to effortlessly install PowerShell on diverse operating systems.

Link - [GitHub PowerShell releases](https://github.com/PowerShell/powershell/releases)

# How you can use it

To set up the PowerShell Script Executor, include the following Maven dependency within your project's pom.xml file during the installation process.

```XML
<dependency>
 <groupId>com.abhishek.powershell</groupId>
 <artifactId>PowershellScriptExecutor</artifactId>
 <version>0.0.1-SNAPSHOT</version>
</dependency>
```

Construct an instance of the **PowershellScriptExecutor** class. This class offers multiple constructors tailored to different parameters, allowing versatile initialization options.

```Java
String scriptPath = "/path/to/script.ps1"
PowershellScriptExecutor psScriptExecutor = new PowerShellScript(scriptPath);
String scriptOutput = psScriptExecutor.executeScript();
```

Various parameters can be employed as a constructor arguments:

1. **Script Path:** Specifies the script's execution path.
2. **Executable Path:** Defines the location of the PowerShell executable, useful if not in the default location.
3. **Max Wait:** Establishes the upper time limit in seconds for script execution. Exceeding this duration triggers an error message: 'Process takes longer than maxWait.'
4. **List of arguments:** Enumerate the arguments intended to accompany the script. For instance, if executing "Script.ps1 -Username foo -Password bar," you must furnish the arguments as follows: -Username foo, -Password bar.