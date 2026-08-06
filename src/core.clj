(ns core
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io]
            [core.time :refer [set-start-time!]]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.json :refer [wrap-json-params wrap-json-response]]
            [ring.middleware.params :refer [wrap-params]]
            [web.router :refer [app-routes]]))

(def config
  (delay
    (if-let [resource (io/resource "config.edn")]
      (-> resource
          slurp
          edn/read-string)
      (throw (Exception. "config.edn not found in resources")))))

(def app (-> app-routes
             wrap-params
             (wrap-json-params {:keywords? true})
             (wrap-json-response {:pretty true})))

(defn start-server []
  (let [port (-> @config :http-port)]
    (run-jetty app {:port port
                    :join? false})

    (set-start-time!)))

(defn -main []
  (start-server)
  (println "Server started")
  @(promise))