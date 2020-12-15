package com.github.victormpcmun.netscapebookmarkprettifier.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTMLPrettifierService {

    private static final Pattern ANCHORED_LINE_PATTERN = Pattern.compile("(.+?)<DT><A HREF=\"(.+?)\" ADD_DATE=(.+?)>(.+?)</A>");
    private static final String BOLD_ITALIC_PATTERN = "\\*\\*\\*(.*?)\\*\\*\\*";
    private static final String ITALIC_PATTERN = "\\*\\*(.*?)\\*\\*";
    private static final String BOLD_PATTERN = "\\*(.*?)\\*";

    public List<String> prettifyLines(List<String> listOfLines) {
        List<String> resultList = new ArrayList<>();
        for (String s : listOfLines) {
            resultList.add(prettifyLine(s));
        }
        return resultList;
    }

    private String prettifyLine(String line) {

        Matcher m = ANCHORED_LINE_PATTERN.matcher(line);
        if (m.find()) {
            String processedLine = prettifyLinkText(m.group(4));
            return m.group(1) + "<DT><A HREF=\"" + m.group(2) + "\">" + processedLine + "</A>";
        }
        return line;
    }


    public String prettifyLinkText(String text) {
        List<String> splitList = Arrays.asList(text.split("-"));
        StringBuilder sb = new StringBuilder();

        sb.append(processForFormat(splitList.get(0)));

        if (splitList.size() > 1) {
            List<String> bulletedList = splitList.subList(1, splitList.size());
            sb.append(processBulletedList(bulletedList));
        }

        return sb.toString();
    }

    private String processForFormat(String line) {
        String resultLine = line.replaceAll(BOLD_ITALIC_PATTERN, "<i><b>$1</b></i>"); //bold && italic
        resultLine = resultLine.replaceAll(ITALIC_PATTERN, "<i>$1</i>"); //italic
        resultLine = resultLine.replaceAll(BOLD_PATTERN, "<b>$1</b>"); //italic
        return resultLine;
    }

    private String processBulletedList(List<String> bulletedList) {
        StringBuilder result = new StringBuilder("<ul>");
        for (String item : bulletedList) {
            result.append("<li>").append(processForFormat(item)).append("</li>");
        }

        result.append("</ul>");
        return result.toString();
    }


}
