(ns wiring
  (:require
    [storage.now :as storage]
    [web.handlers :as handlers]
    [integrant.core :as ig]
    [reitit.ring :as reitit]
    [ring.adapter.jetty :as jetty]))


(defmethod ig/init-key ::now-handler
  [_ {:keys [prefix now]}]
  (handlers/now-handler prefix now))

(defmethod ig/init-key ::ping-handler
  [_ {:keys [now]}]
  (handlers/ping-handler now))

(defmethod ig/init-key ::add-handler
  [_ {:keys [now]}]
  (handlers/add-handler now))

(defmethod ig/init-key ::now-storage
  [_ _]
  storage/now)

(defmethod ig/prep-key ::ring-handler
  [_ config]
  (merge config
         (handlers/make-routes-options
           {:now-handler (ig/ref ::now-handler)
            :ping-handler (ig/ref ::ping-handler)
            :add-handler (ig/ref ::add-handler)})))

(defmethod ig/init-key ::ring-handler
  [_ {:keys [routes options]}]
  (reitit/ring-handler (reitit/router routes options)))

(defmethod ig/init-key ::server
  [_ {:keys [handler] :as opts}]
  (jetty/run-jetty handler (-> opts
                               (dissoc :handler)
                               (assoc :join? false))))

(defmethod ig/halt-key! ::server
  [_ server]
  (.stop server))

(defmethod ig/resume-key ::server
  [ig-key config _ _]
  (ig/init-key ig-key config))

