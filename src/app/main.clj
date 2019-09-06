(ns app.main
  "A simple web service example"
  (:require [clojure.java.io :as io]
            [clojure.tools.logging :as log]
            [integrant.core :as ig]))


(set! *warn-on-reflection* true)

(defn load-config []
  (-> "config.edn"
      (io/resource)
      (slurp)
      (ig/read-string)
      (doto (ig/load-namespaces))
      (ig/prep)))

(defn -main [& _]
    (ig/init (load-config))
    (log/info "try_integrant started."))
