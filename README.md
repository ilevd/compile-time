# compile-time

A Clojure library designed to run your Clojure code in compile time, when the app starts.

## Rationale

Sometimes you need to run some code in compile time, for example load some data, or read last GIT commit info.
Exactly for this puprose this library was designed.


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

And you want this data to be available when you run your app with uberjar.

So you need to require the lib and declare this data:

```Clojure
(ns my-app.core
  (:require [clj-jgit.porcelain :as git]
            [compile-time.core :ad ct]))

;; (defn current-commit ... )

;; declare data with run-fn
(def commit-info (ct/run-fn current-commit))

;; or you can use run-body
(def commit-info-2 (ct/run-body (current-commit)))

```



## License

ilevd © 2022