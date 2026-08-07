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

   ;; Routing & OpenAPI
   [compojure "1.6.3"]
   [metosin/compojure-api "2.0.0-alpha30"]
   [metosin/ring-swagger-ui "5.32.11"]
   [metosin/muuntaja "0.6.8"]

   ;; YAML support
   [clj-commons/clj-yaml "0.7.107"]

   ;; JSON processing
   [com.fasterxml.jackson.core/jackson-core "2.15.2"]
   [com.fasterxml.jackson.core/jackson-databind "2.15.2"]
   [com.fasterxml.jackson.core/jackson-annotations "2.15.2"]]

  :plugins [[lein-ring "0.12.6"]]

  :test-paths ["test" "resources"]

  :main ^:skip-aot core
  :repl-options {:init-ns core})
