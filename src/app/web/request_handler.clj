(ns app.web.request-handler
  (:require [integrant.core :as ig]
            [app.use-cases.date-time :as dt]))


(defmethod ig/init-key ::now
  [_ {:keys [prefix suffix now]}]
  (fn [_]
    {:status 200
     :body (dt/message prefix suffix now)
     :headers {"Content-Type" "text/plain"}}))

;; (ig/init-key ::now {:prefix "It's " :suffix "." now})
