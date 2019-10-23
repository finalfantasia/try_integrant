(ns user
  (:require
    [integrant.repl :refer [go halt reset resume set-prep! suspend]]
    main))


(set-prep! main/load-config)

;; user=> (go)
;; => :initiated

;; user=> (suspend)
;; => :suspended

;; user=> (resume)
;; => :resumed

;; user=> (halt)
;; => :halted

;; user=> (reset)
;; =>
;; :reloading (domain.message web.handlers storage.date-time
;;             web.server main user)
;; :resumed

;; TODO make `integrant.repl/reset` work with aleph
;;   see https://github.com/ztellman/aleph/issues/365
;;       https://github.com/ztellman/aleph/pull/406
