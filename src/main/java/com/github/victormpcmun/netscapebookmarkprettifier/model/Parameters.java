package com.github.victormpcmun.netscapebookmarkprettifier.model;

import java.io.File;

public class Parameters {

    private final static String DEFAULT_PREFIX_OUTPUT_PRETTY_BOOKMARK_FILE_PATH = "prettyBookmarks";
    private final static String DEFAULT_PREFIX_OUTPUT_BOOKMARK_FILE_PATH = "bookmarks";
    private final static String EXTENSION = ".html";

    private static final Parameters NOT_VALID = new Parameters(null, null, null, null);

    private final String inputBookmarkFilePath;
    private final String folderName;
    private final String outputPrettyBookmarkFilePath;
    private final String outputBookmarkFilePath;


    private Parameters(String inputBookmarkFilePath,String folderName, String outputPrettyBookmarkFilePath, String outputBookmarkFilePath) {
        this.inputBookmarkFilePath = inputBookmarkFilePath;
        this.folderName = folderName;
        this.outputPrettyBookmarkFilePath = outputPrettyBookmarkFilePath;
        this.outputBookmarkFilePath = outputBookmarkFilePath;
    }

    public static Parameters build(String[] args) {
        boolean valid1Parameter = args.length == 1 && !args[0].trim().isEmpty();
        boolean valid2Parameter = args.length == 2 && !args[0].trim().isEmpty() && !args[1].trim().isEmpty();

        if (valid1Parameter) {
            String inputBookmarkFilePath=args[0].trim();
            String filePrefix = "all";
            String outputPrettyBookmarkFilePath = getOutputPrettyBookmarkFilePath(inputBookmarkFilePath, filePrefix);
            String outputBookmarkFilePath =  getOutputBookmarkFilePath(inputBookmarkFilePath, filePrefix);
            return new Parameters(inputBookmarkFilePath, "Barra de marcadores", outputPrettyBookmarkFilePath, outputBookmarkFilePath);
        } else if (valid2Parameter) {
            String inputBookmarkFilePath=args[0].trim();
            String folderName=args[1].trim();
            String filePrefix = folderName;
            String outputPrettyBookmarkFilePath = getOutputPrettyBookmarkFilePath(inputBookmarkFilePath, filePrefix);
            String outputBookmarkFilePath =  getOutputBookmarkFilePath(inputBookmarkFilePath, filePrefix);
            return new Parameters( inputBookmarkFilePath, folderName, outputPrettyBookmarkFilePath, outputBookmarkFilePath);
        } else {
            return Parameters.NOT_VALID;
        }
    }


    private static String getOutputPrettyBookmarkFilePath(String bookmarkFilePath, String filePostfix) {
        String path = new File(bookmarkFilePath).getParentFile().getAbsolutePath();
        String prettyBookmarkFilePath = path + File.separator + DEFAULT_PREFIX_OUTPUT_PRETTY_BOOKMARK_FILE_PATH + filePostfix + EXTENSION;
        return prettyBookmarkFilePath;
    }

    private static String getOutputBookmarkFilePath(String bookmarkFilePath, String filePostfix) {
        String path = new File(bookmarkFilePath).getParentFile().getAbsolutePath();
        String prettyBookmarkFilePath = path + File.separator + DEFAULT_PREFIX_OUTPUT_BOOKMARK_FILE_PATH + filePostfix + EXTENSION;
        return prettyBookmarkFilePath;
    }


    public boolean isValidParameters() {
        return inputBookmarkFilePath!=null;
    }


    public String getInputBookmarkFilePath() {
        return inputBookmarkFilePath;
    }

    public String getFolderName() {
        return folderName;
    }

    public String getOutputPrettyBookmarkFilePath() {
        return outputPrettyBookmarkFilePath;
    }

    public String getOutputBookmarkFilePath() {
        return outputBookmarkFilePath;
    }

    public boolean existFolderName() {
        return folderName!=null;
    }
}
