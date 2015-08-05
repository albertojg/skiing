#!/bin/bash

die () {
    echo >&2 "$@"
    exit 1
}

# 1 argument needed, for example: ./src/main/resources/input/1000by1000.txt
[ "$#" -eq 1 ] || die "Input file path argument is needed."

./gradlew main -PinputFilePath="$1"