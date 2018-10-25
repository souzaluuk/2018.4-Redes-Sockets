#!/bin/bash
DIR_BASE=$(echo $0 | sed 's/start.sh//')
FILE="dist/Redes-Sockets.jar"

cd $DIR_BASE

java -jar $FILE $1 $2 $3
