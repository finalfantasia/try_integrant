(ns app.main
  "A simple web service that returns a message with current date-time"
  (:require [clojure.tools.logging :as log]
            [integrant.core :as ig]))


(defn -main [& _]
  (let [config (-> "resources/config.edn"
                   (slurp)
                   (ig/read-string))]
    (ig/load-namespaces config)
    (-> config
        (ig/prep)
        (ig/init))

    (log/info "try_integrant started.")))
