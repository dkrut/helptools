package com.github.dkrut.Tools.Log;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogTool {
    /**
     * @param logFile file to read
     * @return last line number
     */
    public long getLastLineNumber(File logFile) {
        long lastLine = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(logFile))) {
            while (br.readLine() != null) {
                lastLine++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastLine;
    }

    /**
     * @param logFile file to read
     * @return index of last char logFile
     */
    public long getLastIndex(File logFile) {
        long lastIndex = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(logFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                lastIndex = logFile.length() - line.getBytes().length - 2;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastIndex;
    }

    /**
     * @param logFile    file to read
     * @param lineNumber number of line
     * @return value of line number
     */
    public String getLine(File logFile, long lineNumber) {
        String value = null;
        try (BufferedReader br = new BufferedReader(new FileReader(logFile))) {
            int currentLineNumber = 1;
            while ((value = br.readLine()) != null) {
                if (currentLineNumber == lineNumber) {
                    break;
                }
                currentLineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * @param logFile file to read
     * @param index   char index to start read file
     * @return string from file starts with a specific index
     */
    public String getStringFromIndex(File logFile, long index) {
        StringBuilder result = new StringBuilder();
        try (RandomAccessFile raf = new RandomAccessFile(logFile, "r")) {
            raf.seek(index);
            int byteOfData = raf.read();
            while (byteOfData != -1) {
                result.append((char) byteOfData);
                byteOfData = raf.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    /**
     * @param logFile file to read
     * @param index   char index to start read file
     * @param value   find value in string
     * @return true if log from specific index contains value
     */
    public boolean assertContainsValue(File logFile, long index, String value) {
        return (getStringFromIndex(logFile, index)).contains(value);
    }

    /**
     * @param logFile file to read
     * @param index   char index to start read file
     * @param value   find value in line
     * @return first line of multiline string, that contains value that you need
     */
    public String lineWithValue(File logFile, long index, String value) {
        String log = getStringFromIndex(logFile, index);
        if (log.contains(value)) {
            String[] linesArray = log.split("\n");
            int i = 0;
            while (!(linesArray[i].contains(value))) {
                i++;
            }
            return linesArray[i];
        } else {
            return null;
        }
    }

    /**
     * @param line  line of logfile to parse
     * @param regex regular expression to parse line
     * @return result of parsing
     */
    public String parseLine(String line, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            return matcher.group();
        } else {
            return null;
        }
    }
}