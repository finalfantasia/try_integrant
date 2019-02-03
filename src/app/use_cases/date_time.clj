(ns app.use-cases.date-time)


(defn message
  [prefix suffix now]
  (str prefix (now) suffix))
