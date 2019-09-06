(ns app.main
  "A simple web service example"
  (:require [clojure.tools.logging :as log]
            [integrant.core :as ig]))


(set! *warn-on-reflection* true)

(defn -main [& _]
  (let [config (-> "config.edn"
                   (clojure.java.io/resource)
                   (slurp)
                   (ig/read-string))]
    (ig/load-namespaces config)
    (-> config
        (ig/prep)
        (ig/init))

    (log/info "try_integrant started.")))
