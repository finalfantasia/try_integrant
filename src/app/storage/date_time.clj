(ns app.storage.date-time
  (:require [integrant.core :as ig])
  (:import (java.time Instant)))


(defmethod ig/init-key ::now
  [_ _]
  #(Instant/now))
