package com.github.victormpcmun.netscapebookmarkprettifier;

import com.github.victormpcmun.netscapebookmarkprettifier.model.Parameters;
import com.github.victormpcmun.netscapebookmarkprettifier.services.FileService;
import com.github.victormpcmun.netscapebookmarkprettifier.services.BookmarkFolderExtractorService;
import com.github.victormpcmun.netscapebookmarkprettifier.services.HTMLPrettifierService;
import com.github.victormpcmun.netscapebookmarkprettifier.services.HTMLTemplateService;

import java.util.ArrayList;
import java.util.List;

public class NetscapeBookmarkPrettifier {

    private static final String SUCCESS_GENERATING_FILE = "File %s successfully generated.";
    private static final String USAGE_DOCUMENT = "usage.txt";
    private static final String TEMPLATE_PRETTY_HTML = "templatePretty.html";
    private static final String TEMPLATE_FOLDER_BOOKMARK_HTML = "templateFolderBookmark.html";

    private final BookmarkFolderExtractorService filterExtractor = new BookmarkFolderExtractorService();
    private final HTMLPrettifierService htmlPrettifierService = new HTMLPrettifierService();
    private final HTMLTemplateService htmlTemplateService = new HTMLTemplateService();
    private final FileService fileService = new FileService();

    public static void main(String[] args) {

        FileService fileService = new FileService();

        if (args.length == 0) {
            System.out.println(fileService.readFileFromResourcesAsString(USAGE_DOCUMENT));
            System.exit(0);
        }

        Parameters parameters = Parameters.build(args);

        if (!parameters.isValidParameters()) {
            System.out.println("Error: Parameters not valid");
            System.out.println(fileService.readFileFromResourcesAsString(USAGE_DOCUMENT));
            System.exit(-1);
        }

        NetscapeBookmarkPrettifier netscapeBookmarkPrettifier = new NetscapeBookmarkPrettifier();
        netscapeBookmarkPrettifier.go(parameters);
        System.out.println("Process successfully executed!");
    }


    private void go(Parameters parameters) {
        String inputBookmarkFilePath = parameters.getInputBookmarkFilePath();
        String folderNameToBeUsed = getFolderNameToBeUsed(parameters, inputBookmarkFilePath);
        List<String> lines = filterExtractor.getLinesForBookmarkFolder(inputBookmarkFilePath, folderNameToBeUsed);
        generatePrettyFile(parameters.getOutputPrettyBookmarkFilePath(), lines, parameters.getFolderName());
        generateFolderBookmarkFile(parameters.getOutputBookmarkFilePath(), lines);
    }


    private void generatePrettyFile(String fileName, List<String> lines, String folderName) {
        List<String> linesToBeRenderedAsHtml = new ArrayList<>();
        linesToBeRenderedAsHtml.add("<DT><H2>" + folderName + "</H3>");
        linesToBeRenderedAsHtml.addAll(lines.subList(1, lines.size()));
        List<String> prettifiedLines = htmlPrettifierService.prettifyLines(linesToBeRenderedAsHtml);
        List<String> prettyHTMLFile = htmlTemplateService.applyTemplate(TEMPLATE_PRETTY_HTML, prettifiedLines);
        fileService.writeFile(fileName, prettyHTMLFile);
        System.out.println(String.format(SUCCESS_GENERATING_FILE, fileName));
    }


    private void generateFolderBookmarkFile(String fileName, List<String> lines) {
        List<String> folderBookmarkHTMLFile = htmlTemplateService.applyTemplate(TEMPLATE_FOLDER_BOOKMARK_HTML, lines);
        fileService.writeFile(fileName, folderBookmarkHTMLFile);
        System.out.println(String.format(SUCCESS_GENERATING_FILE, fileName));
    }

    private String getFolderNameToBeUsed(Parameters parameters, String inputBookmarkFilePath) {
        String folderName;
        if (!parameters.existFolderName()) {
            folderName=filterExtractor.getNameOfRootFolder(inputBookmarkFilePath);
        } else {
            folderName= parameters.getFolderName();
        }
        return folderName;
    }
}
