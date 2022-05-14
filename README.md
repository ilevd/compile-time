# compile-time

A Clojure library designed to run your Clojure code at compile time.

## Rationale

Sometimes you need to run some code in compile time, for example to load some data, or read last GIT commit info.
Exactly for this purpose this library was designed.

## Installation 

[![Clojars Project](https://img.shields.io/clojars/v/org.clojars.ilevd/compile-time.svg)](https://clojars.org/org.clojars.ilevd/compile-time)

## Usage

Require lib and use `run` or `run-fn` macros:

```Clojure
(ns my-app.core
  (:require [compile-time.core :ad ct]))

(def data (ct/run
            (println "Run in compile time")
            (+ 10 20)))

;; so it will expand to (def data 30) when the app will run
```

### API

#### run

`(compile-time.core/run [& forms])`

Pass body to `run` macros. All forms will be evaluated and the result of evaluating the last form will be returned.


#### run-fn

`(compile-time.core/run-fn [f & args])`

If you need to evaluate just one function, you may use `run-fn`.

### Real example

Suppose you need to get last GIT commit info from your project repository at compile time:

```Clojure
(ns my-app.core
  (:require [clj-jgit.porcelain :as git]
            [compile-time.core :ad ct]))

(defn current-commit
  "Get GIT commit info"
  []
  (let [repo        (git/load-repo (.getAbsolutePath (clojure.java.io/file "")))
        commit-data (first (git/git-log repo :max-count 1))]
    {:commit {:id     (.getName (:id commit-data))
              :date   (-> commit-data :committer :date)
              :author (-> commit-data :committer :name)
              :msg    (:msg commit-data)}}))

(def commit-info (ct/run (current-commit)))

:; or with run-fn
(def commit-info (ct/run-fn current-commit))
```


## License

ilevd © 2022