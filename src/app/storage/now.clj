(ns app.storage.now
  (:import (java.time Instant)))


(def now #(Instant/now))
