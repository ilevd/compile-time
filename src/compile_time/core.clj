(ns compile-time.core)


(defmacro run
  "Pass body to evaluate it at compile time.
  All forms will be evaluated and the result of evaluating the last form will be returned."
  [& forms]
  (run! eval (butlast forms))
  (eval (last forms)))


(defmacro run-fn
  "Pass function and args to evaluate it at compile time."
  [f & args]
  (if (symbol? f)
    (apply (resolve f) args)
    (apply (eval f) args)))
