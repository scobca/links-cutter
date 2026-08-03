(defproject links-cutter
  (-> "VERSION"
      slurp
      .trim)

  :description "Clojure studying project"
  :url "https://github.com/scobca/links-cutter.git"

  :dependencies
  [[org.clojure/clojure "1.12.2"]

   ;; Ring & HTTP
   [ring/ring-core "1.9.6"]
   [ring/ring-jetty-adapter "1.9.6"]
   [ring/ring-json "0.5.1"]

   ;; Routing
   [compojure "1.6.3"]
   [metosin/compojure-api "2.0.0-alpha30"]

   ;; JSON processing
   [com.fasterxml.jackson.core/jackson-core "2.15.2"]
   [com.fasterxml.jackson.core/jackson-databind "2.15.2"]
   [com.fasterxml.jackson.core/jackson-annotations "2.15.2"]]

  :test-paths ["test" "resources"]

  :main ^:skip-aot core
  :repl-options {:init-ns core})
