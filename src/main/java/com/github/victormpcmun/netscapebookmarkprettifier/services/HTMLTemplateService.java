package com.github.victormpcmun.netscapebookmarkprettifier.services;

import java.util.ArrayList;
import java.util.List;

public class HTMLTemplateService {

    private final static String BOOKMARK_HTML = "BOOKMARK_HTML_BLOCK";
    final FileService fileService = new FileService();

    public List<String> applyTemplate(String templateName, List<String> lines) {
        List<String> result = new ArrayList<>();
        List<String> templateLines = fileService.readFileFromResourcesAsListOfLines(templateName);

        for (String lineOfTemplate : templateLines) {
            if (lineOfTemplate.trim().contains(BOOKMARK_HTML)) {
                result.addAll(lines);
            } else {
                result.add(lineOfTemplate);
            }
        }
        return result;
    }
}
