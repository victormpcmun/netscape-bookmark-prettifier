## NetscapeBookmarkPrettifier

Almost all browsers can export bookmarks following the NETSCAPE-Bookmark-file-1 html format, however the generated html
page does not look nice on a browser. Besides, it is not possible to export just only one folder from the bookmarks.

This utility solves these two drawbacks. It works as a command line process that receives the path of the
NETSCAPE-Bookmark-file-1 bookmark html page and the name of a folder.

It generates the following files:

- a nice looking html (pretty) file containing all the links and their children of the given bookmark folder
- a NETSCAPE-Bookmark-file-1 bookmark html with just the content of the given bookmark folder (and its children)

See `./resources/usage.txt` to deep deeper in the usage.    

To run it locally:

```
git clone https://github.com/victormpcmun/NetscapeBookmarkPrettifier
mvn clean package
java -jar target/NetscapeBookmarkPrettifier.jar <PATH_TO_HTML_BOOKMARK_FILE> <FOLDER_NAME>
```

In Linux:
```
./run.sh <PATH_TO_HTML_BOOKMARK_FILE> <FOLDER_NAME>
```

In windows:
```
./run.bat <PATH_TO_HTML_BOOKMARK_FILE> <FOLDER_NAME>
```