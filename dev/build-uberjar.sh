#! /usr/bin/env bash

UBERJAR_NAME=try_integrant.jar

clojure -A:uberjar --main hf.depstar.uberjar ${UBERJAR_NAME}
