(ns app.web.handlers
  (:require
    [app.domain.message :as d]
    [integrant.core :as ig]
    [reitit.ring :as ring]))


(defmethod ig/init-key ::now
  [_ {:keys [prefix now]}]
  (fn [_]
    {:status 200
     :body (d/message prefix now)
     :headers {"Content-Type" "text/plain"}}))

(defmethod ig/init-key ::ping
  [_ {:keys [greeting now]}]
  (fn [_]
    {:status 200
     :body (d/message greeting now)
     :headers {"Content-Type" "text/plain"}}))

(defmethod ig/init-key ::ring-handler
  [_ {:keys [routes]}]
  (ring/ring-handler (ring/router routes)))
