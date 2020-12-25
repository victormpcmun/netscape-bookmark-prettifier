package com.github.victormpcmun.netscapebookmarkprettifier.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileService {

    public void writeFile(String pathLocation, List<String> listOfLines) {
        try {
            Path path = Paths.get(pathLocation);
            Files.write(path, listOfLines, Charset.defaultCharset());
        } catch (IOException e) {
            throw new RuntimeException("Can not write file" + pathLocation, e);
        }
    }


    public List<String> readFileFromResourcesAsListOfLines(String fileName) {
        List<String> list = new ArrayList<>();
        try (InputStream inputStream = getClass().getResourceAsStream("/" + fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;

    }

    public String readFileFromResourcesAsString(String fileName) {
        StringBuilder sb = new StringBuilder();
        List<String> list = readFileFromResourcesAsListOfLines(fileName);

        for (String line : list) {
            sb.append(line).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
