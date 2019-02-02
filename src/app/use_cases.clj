(ns app.use-cases)


(defn message
  [prefix suffix now]
  (str prefix (now) suffix))
