(ns user
  (:require
    [integrant.repl :refer [go halt reset resume set-prep! suspend]]
    app.main))


(set-prep! app.main/load-config)

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
;; :reloading (app.domain.message app.web.handlers app.storage.date-time
;;             app.web.server app.main user)
;; :resumed

;; TODO make `integrant.repl/reset` work with aleph
;;   see https://github.com/ztellman/aleph/issues/365
;;       https://github.com/ztellman/aleph/pull/406
