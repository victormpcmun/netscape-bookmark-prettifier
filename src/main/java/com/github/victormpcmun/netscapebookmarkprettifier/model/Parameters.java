package com.github.victormpcmun.netscapebookmarkprettifier.model;

import java.io.File;

public class Parameters {

    private final static String DEFAULT_PRETTY_BOOKMARK_FILE_PATH = "prettyBookmarks";
    private final static String DEFAULT_BOOKMARK_FOLDER_FILE_PATH = "bookmarks";
    private final static String EXTENSION = ".html";

    private static final Parameters NOT_VALID = new Parameters(false, null, null, null, null);

    final boolean validParameters;

    final String filePath;
    final String filterFolderName;
    final String filteredPrettyBookmarkFilePath;
    final String filteredBookmarkFilePath;


    private Parameters(boolean validParameters, String bookmarkFilePath, String bookmarkFolderName, String prettyBoormarkFilePath, String boormarkFolderFilePath) {
        this.validParameters = validParameters;
        this.filePath = bookmarkFilePath;
        this.filterFolderName = bookmarkFolderName;
        this.filteredPrettyBookmarkFilePath = prettyBoormarkFilePath;
        this.filteredBookmarkFilePath = boormarkFolderFilePath;
    }

    public static Parameters build(String[] args) {

        if (args.length != 2 || args[0].trim().isEmpty() || args[1].trim().isEmpty()) {
            return Parameters.NOT_VALID;
        }

        String bookmarkFilePath = args[0];
        String folderName = args[1];

        String path = new File(bookmarkFilePath).getParentFile().getAbsolutePath();
        String prettyBookmarkFilePath = path + "\\" + DEFAULT_PRETTY_BOOKMARK_FILE_PATH + folderName + EXTENSION;
        String bookmarkFolderFilePath = path + "\\" + DEFAULT_BOOKMARK_FOLDER_FILE_PATH + folderName + EXTENSION;

        return new Parameters(true, bookmarkFilePath, folderName, prettyBookmarkFilePath, bookmarkFolderFilePath);

    }

    public boolean isValidParameters() {
        return validParameters;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFilteredPrettyBookmarkFilePath() {
        return filteredPrettyBookmarkFilePath;
    }

    public String getFilteredBookmarkFilePath() {
        return filteredBookmarkFilePath;
    }

    public String getFilterFolderName() {
        return filterFolderName;
    }

}
