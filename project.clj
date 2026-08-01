(defproject links-cutter
  (-> "VERSION"
      slurp
      .trim)
  :description "Clojure studying project"
  :url "https://github.com/scobca/links-cutter.git"
  :dependencies [[org.clojure/clojure "1.12.2"]]
  :test-paths ["test" "resources"]
  :main ^:skip-aot core
  :repl-options {:init-ns links-cutter.core})
