package com.github.dkrut.Tools.CmdTool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Denis Krutikov on 21.01.2019.
 */

public class CmdTool {

    public static void executeFIle(String file) {
        try {
            Process process = Runtime.getRuntime().exec(file);
            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(process.getInputStream()));
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void executeCommand(String command) {
        try {
            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", command);
            builder.redirectErrorStream(true);
            Process process = builder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getCommandOutput(String command) {
        try {
            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", command);
            builder.redirectErrorStream(true);
            Process process = builder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
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

    public static String getFileOutput(String file) {
        try {
            Process process = Runtime.getRuntime().exec(file);
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
