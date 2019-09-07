(ns app.domain.message)


(defn message
  [prefix now]
  (str prefix ": " (now)))
