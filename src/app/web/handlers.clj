(ns app.web.handlers
  (:require
    [app.domain.message :as d]
    [integrant.core :as ig]
    [reitit.ring :as ring]
    [reitit.ring.middleware.parameters :as parameters]))


(defmethod ig/init-key ::now
  [_ {:keys [prefix now]}]
  (fn [_]
    {:status 200
     :body (d/message prefix now)
     :headers {"Content-Type" "text/plain"}}))

(defmethod ig/init-key ::ping
  [_ {:keys [now]}]
  (fn [context]
    {:status 200
     :body (d/message (get-in context [:params "feedback"]) now)
     :headers {"Content-Type" "text/plain"}}))

(defmethod ig/init-key ::ring-handler
  [_ {:keys [routes options]}]
  (ring/ring-handler (ring/router routes options)))

(defmethod ig/prep-key ::ring-handler
  [_ config]
  (assoc-in config [:options :data :middleware] [parameters/parameters-middleware]))
