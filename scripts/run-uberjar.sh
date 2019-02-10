#! /usr/bin/env bash

UBERJAR_NAME=try_integrant.jar
MAIN_NS=app.main

java -classpath ${UBERJAR_NAME} clojure.main --main ${MAIN_NS}
