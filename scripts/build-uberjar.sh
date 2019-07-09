#! /usr/bin/env bash

UBERJAR_NAME=target/try_integrant.jar

clojure -A:uberjar --target "${UBERJAR_NAME}"
