(ns domain.message)


(defn message
  [prefix now]
  (str prefix ": " (now)))
