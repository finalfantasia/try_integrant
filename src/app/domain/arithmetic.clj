(ns app.domain.arithmetic)


(defn add
  [x y now]
  (str (now) ": " (+ x y)))
