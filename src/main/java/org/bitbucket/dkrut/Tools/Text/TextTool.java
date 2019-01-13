package org.bitbucket.dkrut.Tools.Text;

import java.io.*;

/**
 * Created by Denis Krutikov on 13.01.2019.
 */

public class TextTool {

    public void changeLine(String filePath, int lineNumber, String newText) throws IOException {

        File editFile = new File(filePath);
        File temporaryFile = new File("temp");

        BufferedReader sFileReader = new BufferedReader(new FileReader(editFile));
        BufferedWriter sFileWriter = new BufferedWriter(new FileWriter(temporaryFile));

        int stringCounter = 1;
        String currentFileString;

        while ((currentFileString = sFileReader.readLine()) != null) {
            if (stringCounter == lineNumber) {
                sFileWriter.write(newText);
            } else {
                sFileWriter.write(currentFileString);
            }
            sFileWriter.newLine();
            stringCounter++;
        }

        sFileReader.close();
        sFileWriter.close();

        editFile.delete();
        temporaryFile.renameTo(editFile);
    }
}