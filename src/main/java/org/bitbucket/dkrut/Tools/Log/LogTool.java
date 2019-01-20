package org.bitbucket.dkrut.Tools.Log;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Objects;

/**
 * Created by Denis Krutikov on 20.01.2019.
 */

public class LogTool {

    private String tail(File file) {
        try(RandomAccessFile fileHandler = new RandomAccessFile(file, "r")) {
            long fileLength = fileHandler.length() - 1;
            StringBuilder sb = new StringBuilder();

            for (long filePointer = fileLength; filePointer != -1; filePointer--) {
                fileHandler.seek(filePointer);
                int readByte = fileHandler.readByte();

                if (readByte == 0xA) {
                    if (filePointer == fileLength) {
                        continue;
                    }
                    break;

                } else if (readByte == 0xD) {
                    if (filePointer == fileLength - 1) {
                        continue;
                    }
                    break;
                }

                sb.append((char) readByte);
            }

            return sb.reverse().toString();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Boolean checkLogContains(File file, String logContains) {
        while (!(Objects.requireNonNull(tail(file))).contains(logContains));
        return true;
    }
}