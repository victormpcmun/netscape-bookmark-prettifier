## NetscapeBookmarkPrettifier

I have many bookmarks of technical stuff I find interesting in which the description of them are my short notes, concepts or a summary about the bookmarked page. 

I thought it would be nice to be able to print a pretty version of these bookmarks (following the folder structure) because the exported html page directly from the browse is not mean to it, so I searched for a plugin or tool to do that and didn't find anything I liked it, so I wrote a small utility in Java to do that, very simple but effective. 

It works as a command line that receives a export bookmark file (all browser exports bookmarks in NETSCAPE-Bookmark-file-1 bookmark standard) and  generates the following files:

- a nice looking html (pretty) file containing all the links and their children of a given bookmark folder
- a NETSCAPE-Bookmark-file-1 bookmark html with just the content of the given bookmark folder (and its children)

See `./resources/usage.txt` to deep deeper in the usage.    

In Linux:
```
./run.sh <PATH_TO_HTML_BOOKMARK_FILE> <FOLDER_NAME>
```

In windows:
```
./run.bat <PATH_TO_HTML_BOOKMARK_FILE> <FOLDER_NAME>
```
