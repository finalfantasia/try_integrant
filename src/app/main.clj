(ns app.main
  "A simple web service that returns a message with current date-time"
  (:require [integrant.core :as ig]))


(def config
  (ig/read-string (slurp "resources/config.edn")))

(ig/load-namespaces config)

(def system
  (-> config
    (ig/prep)
    (ig/init)))

;; (ig/halt! system)

;; (ig/suspend! system)

;; (def system (ig/resume config system))
