package org.bitbucket.dkrut.Tools.Log;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Objects;

/**
 * Created by Denis Krutikov on 20.01.2019.
 */

public class LogTool {

    public String readLastLine(File file) {
        String result;
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            result = null;
            long startIdx = file.length();
            while (result == null || result.length() == 0) {
                raf.seek(startIdx--);
                raf.readLine();
                result = raf.readLine();
            }
            return new String(result.getBytes("ISO-8859-1"), "UTF-8"); //convert result into utf-8
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boolean checkLogContainsBoolean(File file, String logContains) {
        while (!(Objects.requireNonNull(readLastLine(file))).contains(logContains));
        return true;
    }

    public String checkLogContainsString(File file, String logContains) {
        while (!(Objects.requireNonNull(readLastLine(file))).contains(logContains));
        return readLastLine(file);
    }
}