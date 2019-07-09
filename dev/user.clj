(ns user
  (:require
    [clojure.java.io :as io]
    [integrant.core :as ig]
    [integrant.repl :refer [go halt reset resume set-prep! suspend]]))


(defn load-config []
  (-> "config.edn"
      (io/resource)
      (slurp)
      (ig/read-string)))

(set-prep! #(doto (load-config)
              (ig/load-namespaces)
              (ig/prep)))


;; user=> (ig-repl/go)
;; :initiated

;; user=> (ig-repl/suspend)
;; :suspended

;; user=> (ig-repl/resume)
;; :resumed

;; user=> (ig-repl/halt)
;; :halted

;; TODO make `ig-repl/reset` work with aleph
;;   see https://github.com/ztellman/aleph/issues/365
;;       https://github.com/ztellman/aleph/pull/406
