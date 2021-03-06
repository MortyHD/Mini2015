package model;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

/**
 *
 * @author Jakob Ferdinandsen
 */
public class ReadFile {

    private String path;
    private int numberOfLines = 0;

    public ReadFile(String file_path) {
        path = file_path;
    }
    
    //--------------------------------------------------------------------------
    // Åbner en fil og bruger Readlines til at læse faktura nummeret
    //--------------------------------------------------------------------------
    public String[] openFile() throws IOException {
        FileReader fr = new FileReader(path);
        BufferedReader textReader = new BufferedReader(fr);
        int numberOfLines = readLines();
        String[] textData = new String[numberOfLines];
        for (int i = 0; i < numberOfLines; i++) {
            textData[i] = textReader.readLine();
        }
        textReader.close();
        return textData;
    }

    public int readLines() throws IOException {
        FileReader file_to_read = new FileReader(path);
        BufferedReader bf = new BufferedReader(file_to_read);

        String aLine;
        while ((aLine = bf.readLine()) != null) {
            numberOfLines++;
        }
        bf.close();
        return numberOfLines;
    }

}
