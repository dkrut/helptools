package com.github.dkrut.Tools.CmdTool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Denis Krutikov on 21.01.2019.
 */

public class CmdTool {

    public void executeCommand(String command) {
        try {
            Runtime rt = Runtime.getRuntime();
            Process p = rt.exec("cmd /c " + command);
            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(p.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getCommandOutput(String command) {
        String result;
        try {
            Runtime rt = Runtime.getRuntime();
            Process p = rt.exec("cmd /c " + command);
            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(p.getInputStream()));

            while ((result = stdInput.readLine()) != null) {
            return result;
            }
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
