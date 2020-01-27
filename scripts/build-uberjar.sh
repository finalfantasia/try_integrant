#! /usr/bin/env sh

uberjar_name=target/try_integrant.jar

clojure -A:uberjar --target "${uberjar_name}"
