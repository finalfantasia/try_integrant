(ns app.wiring
  (:require
    [aleph.http :as aleph]
    [app.storage.now :as storage]
    [app.web.handlers :as handlers]
    [integrant.core :as ig]
    [reitit.ring :as ring])
  (:import
    (java.io Closeable)))


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
         (handlers/make-routes-options {:now-handler (ig/ref ::now-handler)
                                        :ping-handler (ig/ref ::ping-handler)
                                        :add-handler (ig/ref ::add-handler)})))

(defmethod ig/init-key ::ring-handler
  [_ {:keys [routes options]}]
  (ring/ring-handler (ring/router routes options)))

(defmethod ig/init-key ::server
  ^Closeable
  [_ {:keys [port handler]}]
  (aleph/start-server handler {:port port}))

(defmethod ig/halt-key! ::server
  [_ ^Closeable server]
  (.close server))

;; if this is missing, Integrant falls back to defmethod for halt-key!
;; (defmethod ig/suspend-key! ::instance
;;   [_ ^Closeable server]
;;   (.close server))

(defmethod ig/resume-key ::server
  [ig-key config _ _]
  (ig/init-key ig-key config))
