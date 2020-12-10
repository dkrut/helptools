package com.github.dkrut.Tools.CmdTool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Denis Krutikov on 21.01.2019.
 */

public class CmdTool {

    public void executeCommand(String command) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(process.getInputStream()));
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getCommandOutput(String command) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            ArrayList output = new ArrayList();
            String line;
            while ((line = reader.readLine()) != null) {
                output.add(line);
            }
            reader.close();
            return  String.join("\n", output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
