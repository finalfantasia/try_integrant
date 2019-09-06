(ns app.web.server
  (:require
    [aleph.http :as aleph]
    [integrant.core :as ig])
  (:import
    (java.io Closeable)))


;; useful for adding default configuration dynamically
(defmethod ig/prep-key ::instance
  [_ config]
  (merge {:port 8080}
         config))

(defmethod ig/init-key ::instance
  ^Closeable
  [_ {:keys [port handler]}]
  (aleph/start-server handler {:port port}))

(defmethod ig/halt-key! ::instance
  [_ ^Closeable server]
  (.close server))

;; if this is missing, Integrant falls back to defmethod for halt-key!
;; (defmethod ig/suspend-key! ::instance
;;   [_ ^Closeable server]
;;   (.close server))

(defmethod ig/resume-key ::instance
  [ig-key config _ _]
  (ig/init-key ig-key config))
