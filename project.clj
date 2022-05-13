(defproject compile-time "1.0.0"
  :description "Run Clojure code in compile time"
  :url "https://github.com/ilevd/compile-time"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]]
  :repl-options {:init-ns compile-time.core})
