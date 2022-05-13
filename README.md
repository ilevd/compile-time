# compile-time

A Clojure library designed to run your Clojure code in compile time.

## Rationale

Sometimes you need to run some code in compile time, for example to load some data, or read last GIT commit info.
Exactly for this purpose this library was designed.

## Installation 

[![Clojars Project](https://img.shields.io/clojars/v/org.clojars.ilevd/compile-time.svg)](https://clojars.org/org.clojars.ilevd/compile-time)

## Usage

Suppose you have function that reads last commit info:

```Clojure
(ns my-app.core
  (:require [clj-jgit.porcelain :as git]))


(defn current-commit
  "Get GIT commit info"
  []
  (let [repo        (git/load-repo (.getAbsolutePath (clojure.java.io/file "")))
        commit-data (first (git/git-log repo :max-count 1))]
    {:commit {:id     (.getName (:id commit-data))
              :date   (-> commit-data :committer :date)
              :author (-> commit-data :committer :name)
              :msg    (:msg commit-data)}}))

```

And you want run this function when jar is builting, so `.git` is available, and set this data to a variable, to be able to use it later in runtime.

So you need to require the lib and declare this data:

```Clojure
(ns my-app.core
  (:require [clj-jgit.porcelain :as git]
            [compile-time.core :ad ct]))

;; (defn current-commit ... )

;; declare data with `run-fn`
(def commit-info (ct/run-fn current-commit))

;; or you can use `run`
(def commit-info-2 (ct/run (current-commit)))

```



## License

ilevd © 2022