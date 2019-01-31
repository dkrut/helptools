package org.bitbucket.dkrut.Tools.Log;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by Denis Krutikov on 20.01.2019.
 */

public class LogTool {

    public String getLastLine(File logFile) {
        String result;
        try (RandomAccessFile raf = new RandomAccessFile(logFile, "r")) {
            result = null;
            long startIdx = logFile.length();
            while (result == null || result.length() == 0) {
                raf.seek(startIdx--);
                raf.readLine();
                result = raf.readLine();
            }
            return new String(result.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8); //convert result into utf-8
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boolean checkLastLineLogContainsBoolean(File logFile, String logContains) {
        while (!(getLastLine(logFile)).contains(logContains));
        return true;
    }

    public String checkLastLineLogContainsString(File LogFile, String logContains) {
        while (!(getLastLine(LogFile)).contains(logContains));
        return getLastLine(LogFile);
    }


    public long getLogFileCharCount(File logFile){
        return logFile.length();
    }

    //return string from whole file
    public String getLogFileString(File file) {
        try {
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            StringBuilder result = new StringBuilder();
            int b = raf.read();
            //read the chars bit by bit and add them to the string
            while (b != -1) {
                result.append((char) b);
                b = raf.read();
            }
            raf.close();
            return new String(result.toString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8); //convert result into utf-8
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;

        }

    //return string from file starts with a specific char
    public String getLogFileStringFrom(File logFile, long charNumber) {
        try {
            RandomAccessFile raf = new RandomAccessFile(logFile, "r");
            StringBuilder result = new StringBuilder();
            //put a pointer to the desired char
            raf.seek(charNumber);
            int b = raf.read();

            //read the chars bit by bit and add them to the string
            while (b != -1) {
                result.append((char) b);
                b = raf.read();
            }
            raf.close();
            return new String(result.toString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8); //convert result into utf-8
        } catch (IOException e){
            e.printStackTrace();
        }
        return  null;
    }

    //return true if string contains that you need
    public Boolean checkLogContainsBoolean(File logFile, long readFromChar, String logContains, int timeoutSeconds) throws TimeoutException {
        long stop = System.nanoTime()+ TimeUnit.SECONDS.toNanos(timeoutSeconds);
        while (!(getLogFileStringFrom(logFile, readFromChar)).contains(logContains) && stop > System.nanoTime());
        if ((getLogFileStringFrom(logFile, readFromChar)).contains(logContains)) return true;
        else throw new TimeoutException("Time out in " + timeoutSeconds + " seconds");
    }

    //return line of multiline string, that contains that you need
    public String checkLogContainsString(File logFile, long readFromChar, String logContains, int timeoutSeconds) throws TimeoutException {
        long stop = System.nanoTime()+ TimeUnit.SECONDS.toNanos(timeoutSeconds);
        while (!(getLogFileStringFrom(logFile, readFromChar)).contains(logContains) && stop > System.nanoTime());
        if ((getLogFileStringFrom(logFile, readFromChar)).contains(logContains)) {
            String[] linesArray = getLogFileStringFrom(logFile, readFromChar).split("\n");
            int i = 0;
            while (!(linesArray[i].contains(logContains))) {
                i++;
            }
            return linesArray[i];
        }
        else throw new TimeoutException("Time out in " + timeoutSeconds + " seconds");
    }

    //wait string, that you need, then parse it and return word
    public String checkLogContainsStringAndParseIt(File logFile, long readFromChar, String logContains, String separator, int wordNumber, int timeoutSeconds) throws TimeoutException {
        while (!checkLogContainsBoolean(logFile, readFromChar, logContains, timeoutSeconds));
        return stringParserFromLogFile(getLogFileStringFrom(logFile, readFromChar), logContains, separator, wordNumber);
    }

    public String stringParser(String string, String separator, int wordNumber){
        String[] stringArray = string.split(separator);
//        for (String retval : string.split(separator)){
//            System.out.println(retval);
//        }
        return stringArray[wordNumber];
    }

    //parsing line of multiline string and return word
    public String stringParserFromLogFile(String string, String logContains, String separator, int wordNumber){
        String[] linesArray = string.split("\n");
        int i = 0;
        while (!(linesArray[i].contains(logContains))){
            i++;
        }
        return stringParser(linesArray[i], separator, wordNumber);
    }
}