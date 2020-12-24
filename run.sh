#!/bin/sh
cd "$(dirname "$0")"
java -jar ./target/NetscapeBookmarkPrettifier.jar $1  $2
