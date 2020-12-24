package com.github.victormpcmun.netscapebookmarkprettifier.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookmarkFolderExtractorService {

    private static final String CLOSE_H3="</H3>";

    public List<String> getLinesForBookmarkFolder(String path, String folderName) {

        List<String> result = new ArrayList<>();

        boolean foundFolder = false;
        int folderIndentationLevel = -1;

        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();

                if (!foundFolder) {
                    foundFolder = lineContainsFolderName(line, folderName);
                    if (foundFolder) {
                        folderIndentationLevel = calculateIndentation(line);
                    }
                }

                if (foundFolder) {
                    if (line.indexOf("</DL><p>") == folderIndentationLevel) {
                        result.add(line);
                        break;
                    }
                    result.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File Not Found:" + path);
        }
        return result;
    }


    public String getNameOfRootFolder(String path) {

        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.endsWith(CLOSE_H3)) {
                    int init = line.lastIndexOf(">");
                    int end = line.length()-CLOSE_H3.length();
                    return line.substring(init, end);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File Not Found:" + path);
        }
        throw new RuntimeException("Can not find root folder name");
    }




    private boolean lineContainsFolderName(String line, String folderName) {
        String folderNameAndH3Tag = folderName + CLOSE_H3;
        int expectedPositionForFolderNameAndH3Tag = line.length() - folderNameAndH3Tag.length();
        int actualPositionForFolderNameAndH3Tag = line.lastIndexOf(folderNameAndH3Tag);
        boolean matchPosition = expectedPositionForFolderNameAndH3Tag == actualPositionForFolderNameAndH3Tag;
        return (actualPositionForFolderNameAndH3Tag >= 0 && matchPosition);
    }

    private int calculateIndentation(String line) {
        return line.indexOf("<"); // all lines begin with <
    }
}
