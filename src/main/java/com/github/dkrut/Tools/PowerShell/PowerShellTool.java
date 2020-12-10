package com.github.dkrut.Tools.PowerShell;

import com.profesorfalken.jpowershell.PowerShell;

/**
 * Created by Denis Krutikov on 08.01.2019.
 */

public class PowerShellTool {
    private PowerShell powerShell;

    private void createSession(){
        powerShell = PowerShell.openSession();
    }

    private void closeSession(){
        powerShell.close();
    }

    public String getScriptOutput(String scriptPath){
        createSession();
        String commandOutput = powerShell.executeScript(scriptPath).getCommandOutput();
        closeSession();
        return commandOutput;
    }

    public String getScriptWithParamsOutput(String scriptPath, String params){
        createSession();
        String commandOutput = powerShell.executeScript(scriptPath, params).getCommandOutput();
        closeSession();
        return commandOutput;
    }

    public String getCommandOutput(String scriptPath){
        createSession();
        String commandOutput = powerShell.executeCommand(scriptPath).getCommandOutput();
        closeSession();
        return commandOutput;
    }
}
