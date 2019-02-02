(ns app.services.date-time
  (:require [integrant.core :as ig])
  (:import (java.time Instant)))


;; a non-pure function provided by a stateful date-time service
(defmethod ig/init-key ::now
  [_ _]
  #(Instant/now))
