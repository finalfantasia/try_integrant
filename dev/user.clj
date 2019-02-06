(ns user
  (:require [integrant.core :as ig]
            [integrant.repl :as ig-repl]))


(defn load-config []
  (-> "resources/config.edn"
    (slurp)
    (ig/read-string)))

(ig-repl/set-prep! #(doto (load-config)
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
