#!/bin/sh
while true
do
    curl --write-out '\n' http://localhost:8080/version
    sleep 1
done