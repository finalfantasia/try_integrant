#! /usr/bin/env sh

uberjar_name=target/try_integrant.jar
main_namespace=main

java --class-path ${uberjar_name} clojure.main --main ${main_namespace}
