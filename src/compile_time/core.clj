(ns compile-time.core)


(defmacro run-body
  "Run forms in compile time, return result of last forms"
  [& forms]
  (run! eval (butlast forms))
  (eval (last forms)))


(defmacro run-fn
  "Run function with args in compile time"
  [f & args]
  (if (symbol? f)
    (apply (resolve f) args)
    (apply (eval f) args)))
