#! /usr/bin/env bash

UBERJAR_NAME=target/try_integrant.jar
MAIN_NAMESPACE=main

java --class-path ${UBERJAR_NAME} clojure.main --main ${MAIN_NAMESPACE}
